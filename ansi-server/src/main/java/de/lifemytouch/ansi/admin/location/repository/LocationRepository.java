package de.lifemytouch.ansi.admin.location.repository;

import de.lifemytouch.ansi.core.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class LocationRepository implements ILocationRepository {

    private final File file;
    private FileConfiguration fileConfiguration;
    private final JavaPlugin javaPlugin;

    public LocationRepository(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.file = new File(javaPlugin.getDataFolder(), "locations.yml");
        loadConfig();
    }

    private void loadConfig() {
        if (!file.exists()) {
            javaPlugin.saveResource("locations.yml", false);
        }
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    private void save() {
        try {
            fileConfiguration.save(file);
        } catch (IOException exception) {
            Log.error("Konnte locations.yml nicht speichern");
        }
    }

    @Override
    public void saveLocation(String key, Location location) {
        String path = "locations." + key;
        fileConfiguration.set(path + ".world", location.getWorld().getName());
        fileConfiguration.set(path + ".x", location.getX());
        fileConfiguration.set(path + ".y", location.getY());
        fileConfiguration.set(path + ".z", location.getZ());
        fileConfiguration.set(path + ".yaw", location.getYaw());
        fileConfiguration.set(path + ".pitch", location.getPitch());

        save();
    }

    @Override
    public Optional<Location> loadLocation(String key) {
        String path = "locations." + key;

        if (!fileConfiguration.contains(path)) {
            return Optional.empty();
        }

        String worldName = fileConfiguration.getString(path + ".world");
        double x = fileConfiguration.getDouble(path + ".x");
        double y = fileConfiguration.getDouble(path + ".y");
        double z = fileConfiguration.getDouble(path + ".z");
        float yaw = (float) fileConfiguration.getDouble(path + ".yaw");
        float pitch = (float) fileConfiguration.getDouble(path + ".pitch");

        if (worldName == null) return Optional.empty();

        return Optional.of(new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch));
    }

    @Override
    public void deleteLocation(String key) {
        fileConfiguration.set("locations." + key, null);
        save();
    }

}
