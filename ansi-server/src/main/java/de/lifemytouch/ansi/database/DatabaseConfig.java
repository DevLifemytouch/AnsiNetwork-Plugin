package de.lifemytouch.ansi.database;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class DatabaseConfig {

    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    public DatabaseConfig(JavaPlugin javaPlugin) {
        File file = new File(javaPlugin.getDataFolder(), "database.yml");

        if(!file.exists()) {
            javaPlugin.saveResource("database.yml", false);
        }

        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        this.host = yamlConfiguration.getString("host", "localhost");
        this.port = yamlConfiguration.getInt("port", 3306);
        this.database = yamlConfiguration.getString("database", "ansi");
        this.username = yamlConfiguration.getString("username", "root");
        this.password = yamlConfiguration.getString("password", "");
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
