package de.lifemytouch.ansi.coin;

import de.lifemytouch.ansi.database.DatabaseManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CoinRepository {

    private final JavaPlugin javaPlugin;
    private final DatabaseManager databaseManager;

    public CoinRepository(JavaPlugin javaPlugin, DatabaseManager databaseManager) {
        this.javaPlugin = javaPlugin;
        this.databaseManager = databaseManager;

        createTable();
    }

    private void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS player_coins (
                    uuid VARCHAR(36) NOT NULL,
                    name VARCHAR(16) NOT NULL,
                    coins BIGINT NOT NULL DEFAUlT 0,
                    hidden BOOLEAN NOT NULL DEFAULT FALSE,
                    PRIMARY KEY (uuid),
                    UNIQUE KEY unique_player_name (name)
                )
               """;

        try (
                Connection connection = databaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.executeUpdate();
        } catch (SQLException exception) {
            javaPlugin.getLogger().severe("Coin-Table konnte nicht erstellt werden: " + exception.getMessage());
            throw new IllegalStateException("Coin-Table konnte nicht initialisiert werden: " + exception);
        }
    }

    public long getCoins(UUID uuid) {

        String sql = """
                SELECT coins FROM player_coins WHERE uuid = ?
                """;

        try (
                Connection connection = databaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, uuid.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return 0L;
                }

                return resultSet.getLong("coins");
            }
        } catch (SQLException exception) {
            javaPlugin.getLogger().severe("Coins nicht gefunden: " + exception.getMessage());

            return 0L;
        }
    }

    public void setCoins(UUID uuid, long amount) {
        String sql = """
            UPDATE player_coins
            SET coins = ?
            WHERE uuid = ?
            """;

        try (
                Connection connection = databaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, amount);
            statement.setString(2, uuid.toString());

            statement.executeUpdate();

        } catch (SQLException exception) {
            javaPlugin.getLogger().severe(
                    "Konnte Coins nicht speichern: "
                            + exception.getMessage()
            );
        }
    }

    public boolean isHidden(UUID uuid) {
        String sql = """
                SELECT hidden
                FROM player_coins
                WHERE uuid = ?
                """;

        try (
                Connection connection = databaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, uuid.toString());

            try (ResultSet resultSet = statement.executeQuery()) {

                if (!resultSet.next()) {
                    return false;
                }

                return resultSet.getBoolean("hidden");
            }

        } catch (SQLException exception) {
            javaPlugin.getLogger().severe(
                    "Konnte Coin-Sichtbarkeit nicht abrufen: "
                            + exception.getMessage()
            );

            return false;
        }
    }

    public void setHidden(UUID uuid, boolean hidden) {
        String sql = """
            UPDATE player_coins
            SET hidden = ?
            WHERE uuid = ?
            """;

        try (
                Connection connection = databaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setBoolean(1, hidden);
            statement.setString(2, uuid.toString());

            statement.executeUpdate();

        } catch (SQLException exception) {
            javaPlugin.getLogger().severe(
                    "Konnte Coin-Sichtbarkeit nicht speichern: "
                            + exception.getMessage()
            );
        }
    }

    public UUID findUuidByName(String name) {
        String sql = """
            SELECT uuid
            FROM player_coins
            WHERE name = ?
            """;

        try (
                Connection connection = databaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }

                return UUID.fromString(resultSet.getString("uuid"));
            }

        } catch (SQLException exception) {
            javaPlugin.getLogger().severe(
                    "Konnte Spieler nicht anhand des Namens finden: "
                            + exception.getMessage()
            );

            return null;
        }
    }

    public void updatePlayerName(UUID uuid, String name) {
        String sql = """
            INSERT INTO player_coins (uuid, name)
            VALUES (?, ?)
            ON DUPLICATE KEY UPDATE name = VALUES(name)
            """;

        try (
                Connection connection = databaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, uuid.toString());
            statement.setString(2, name);

            statement.executeUpdate();

        } catch (SQLException exception) {
            javaPlugin.getLogger().severe(
                    "Konnte Spielernamen nicht speichern: "
                            + exception.getMessage()
            );
        }
    }

}
