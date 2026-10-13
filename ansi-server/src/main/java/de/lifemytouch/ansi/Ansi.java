package de.lifemytouch.ansi;

import de.lifemytouch.ansi.bootstrap.initializer.ServerInitializer;
import de.lifemytouch.ansi.core.util.Log;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ansi extends JavaPlugin {
    private ServerInitializer serverInitializer;

    @Override
    public void onEnable() {
        Log.info("[Ansi-Network] \"ansi-server\" wurde aktiviert!");

        serverInitializer = new ServerInitializer(this);
        serverInitializer.initialize();
    }

    @Override
    public void onDisable() {
        if(serverInitializer != null) serverInitializer.shutdown();

        Log.info("[Ansi-Network] \"ansi-server\" wurde deaktiviert!");
    }
}
