package de.lifemytouch.ansi.rank.repository;

import de.lifemytouch.ansi.core.util.Log;
import de.lifemytouch.ansi.database.provider.ConnectionProvider;
import de.lifemytouch.ansi.rank.exception.RankStorageException;
import de.lifemytouch.ansi.rank.model.Rank;
import de.lifemytouch.ansi.rank.model.RankData;

import java.sql.*;
import java.util.*;

public class RankRepository {

    public record Change(RankData data, long updatedAtMillis) {
    }

    private static final String COLUMNS = "uuid, name, rank_name, previous_rank, expires_at";

    private final ConnectionProvider connectionProvider;

    public RankRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;

        createTable();
    }

    private void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS player_ranks (
                    uuid VARCHAR(36) NOT NULL,
                    name VARCHAR(16) NULL,
                    rank_name VARCHAR(16) NOT NULL DEFAULT 'DEFAULT',
                    previous_rank VARCHAR(16) NULL,
                    expires_at BIGINT NOT NULL DEFAULT 0,
                    updated_at TIMESTAMP(3) NOT NULL
                        DEFAULT CURRENT_TIMESTAMP(3)
                        ON UPDATE CURRENT_TIMESTAMP(3),
                    PRIMARY KEY (uuid),
                    KEY idx_player_ranks_name (name),
                    KEY idx_player_ranks_updated (updated_at)
                );
                """;

        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException exception) {
            throw fail("player_ranks konnte nicht erstellt werden", exception);
        }
    }

    public void ensurePlayer(UUID uuid, String name) {
        String sql = """
                INSERT INTO player_ranks (uuid, name) VALUES (?, ?)
                ON DUPLICATE KEY UPDATE name = VALUES(name)
                """;
        
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uuid.toString());
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw fail("Spieler konnte nicht angelegt werden", exception);
        }
    }

    public Optional<RankData> find(UUID uuid) {
        String sql = "SELECT " + COLUMNS + " FROM player_ranks WHERE uuid = ?";

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, uuid.toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Optional.of(map(resultSet)) : Optional.empty();
            }
        } catch (SQLException exception) {
            throw fail("Rang konnte nicht geladen werden", exception);
        }
    }

    public Optional<RankData> findByName(String name) {
        String sql = "SELECT " + COLUMNS + " FROM player_ranks WHERE name = ? " +
                "ORDER BY updated_at DESC LIMIT 1";

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Optional.of(map(resultSet)) : Optional.empty();
            }
        } catch (SQLException exception) {
            throw fail("Spieler konnte nicht gesucht werden", exception);
        }
    }

    public void setRank(UUID uuid, Rank rank) {
        String sql = """
                INSERT INTO player_ranks (uuid, rank_name, previous_rank, expires_at)
                VALUES (?, ?, NULL, 0)
                ON DUPLICATE KEY UPDATE
                    rank_name = VALUES(rank_name),
                    previous_rank = NULL,
                    expires_at = 0
                """;

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, rank.name());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw fail("Rang konnte nicht gesetzt werden", exception);
        }
    }

    public boolean setTemporaryRank(UUID uuid, Rank rank, long expiresAt) {
        String sql = """
                UPDATE player_ranks
                SET previous_rank = IF(expires_at > 0, previous_rank, rank_name),
                    rank_name = ?,
                    expires_at = ?
                WHERE uuid = ?
                """;

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, rank.name());
            preparedStatement.setLong(2, expiresAt);
            preparedStatement.setString(3, uuid.toString());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw fail("Temporärer Rang konnte nicht gesetzt werden", exception);
        }
    }

    public int expireDue(long now) {
        String sql = """
                UPDATE player_ranks
                SET rank_name = COALESCE(previous_rank, 'DEFAULT'),
                    previous_rank = NULL,
                    expires_at = 0
                WHERE expires_at > 0 AND expires_at <= ?
                """;

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, now);
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw fail("Abgelaufene Ränge konnten nicht bereinigt werden", exception);
        }
    }

    public long databaseTimeMillis() {
        String sql = "SELECT ROUND(UNIX_TIMESTAMP(NOW(3)) * 1000)";

        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException exception) {
            throw fail("Zeit konnte nicht gefunden werden", exception);
        }
    }

    public List<Change> findChangedSince(long sinceMillis) {
        String sql = "SELECT " + COLUMNS + ", ROUND(UNIX_TIMESTAMP(updated_at) * 1000) AS updated_ms " +
                "FROM player_ranks WHERE updated_as >= FROM_UNIXTIME(? / 1000)";

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, sinceMillis);

            List<Change> changes = new ArrayList<>();

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    changes.add(new Change(map(resultSet), resultSet.getLong("updated_ms")));
                }
            }

            return changes;
        } catch (SQLException exception) {
            throw fail("Änderungen konnten nicht gelesen werden", exception);
        }
    }

    public boolean importIfAbsent(RankData rankData) {
        String sql = """
                INSERT IGNORE INTO player_ranks (uuid, name, rank_name, previous_rank, expires_at)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, rankData.uuid().toString());
            statement.setString(2, rankData.name());
            statement.setString(3, rankData.previousRank().name());
            statement.setString(4, rankData.previousRank() == null ? null : rankData.previousRank().name());
            statement.setLong(5, rankData.expiresAt());

            return statement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw  fail("Import fehlgeschlagen", exception);
        }
    }

    private RankData map(ResultSet resultSet) throws SQLException {
        Rank rank = Rank.fromName(resultSet.getString("rank_name"));

        return new RankData(
                UUID.fromString(resultSet.getString("uuid")),
                resultSet.getString("name"),
                rank != null ? rank : Rank.DEFAULT,
                Rank.fromName(resultSet.getString("previous_rank")),
                resultSet.getLong("expires_at")
        );
    }

    private RankStorageException fail(String message, SQLException cause) {
        Log.error(message + ": " + cause.getMessage());
        return new RankStorageException(message, cause);
    }
}
