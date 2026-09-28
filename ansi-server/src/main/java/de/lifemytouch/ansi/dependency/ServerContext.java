package de.lifemytouch.ansi.dependency;

import de.lifemytouch.ansi.fly.FlyService;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerContext {

    private final JavaPlugin javaPlugin;
    private final FlyService flyService;

    public ServerContext(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;

        this.flyService = new FlyService();
    }

    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    public FlyService getFlyService() {
        return flyService;
    }
}
