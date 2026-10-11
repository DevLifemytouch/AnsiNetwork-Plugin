package de.lifemytouch.ansi.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager implements AutoCloseable {

    private final JavaPlugin javaPlugin;
    private final HikariDataSource dataSource;

    public DatabaseManager(JavaPlugin javaPlugin, DatabaseConfig databaseConfig) {
        this.javaPlugin = javaPlugin;

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:mariadb://"
                + databaseConfig.getHost()
                + ":"
                + databaseConfig.getPort()
                + "/"
                + databaseConfig.getDatabase()
        );

        hikariConfig.setUsername(databaseConfig.getUsername());
        hikariConfig.setPassword(databaseConfig.getPassword());

        hikariConfig.setPoolName("AnsiDatabasePool");

        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(2);

        hikariConfig.setConnectionTimeout(5000);
        hikariConfig.setIdleTimeout(600000);
        hikariConfig.setMaxLifetime(1800000);

        hikariConfig.setLeakDetectionThreshold(10000);

        this.dataSource = new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void close() {
        if(dataSource.isClosed()) return;

        dataSource.close();
        javaPlugin.getLogger().info("MariaDB-Connection geschlossen.");
    }
}
