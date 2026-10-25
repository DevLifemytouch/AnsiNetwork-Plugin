package de.lifemytouch.ansi.core.service;

import de.lifemytouch.ansi.core.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SystemService {

    private final JavaPlugin javaPlugin;

    public SystemService(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    public double getTPS() {
        return Bukkit.getServer().getTPS()[0];
    }

}
