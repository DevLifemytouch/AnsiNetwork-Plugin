package de.lifemytouch.ansi.admin.system.reload.service;

import de.lifemytouch.ansi.core.util.Log;
import org.bukkit.plugin.java.JavaPlugin;

public class AnsiReloadService {

    private final JavaPlugin javaPlugin;

    public AnsiReloadService(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    public void reloadPlugin() {
        javaPlugin.reloadConfig();

        Log.info("Config wurde neu geladen!");
    }

}
