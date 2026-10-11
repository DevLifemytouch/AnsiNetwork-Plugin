package de.lifemytouch.ansi;

import de.lifemytouch.ansi.bootstrap.ServerInitializer;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ansi extends JavaPlugin {
    private ServerInitializer serverInitializer;

    @Override
    public void onEnable() {
        getLogger().info("[Ansi-Network] \"ansi-server\" wurde aktiviert!");

        serverInitializer = new ServerInitializer(this);
        serverInitializer.initialize();
    }

    @Override
    public void onDisable() {
        if(serverInitializer != null) serverInitializer.shutdown();

        getLogger().info("[Ansi-Network] \"ansi-server\" wurde deaktiviert!");
    }
}
