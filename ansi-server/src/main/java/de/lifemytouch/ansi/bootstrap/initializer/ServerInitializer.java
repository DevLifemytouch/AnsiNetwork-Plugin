package de.lifemytouch.ansi.bootstrap.initializer;

import de.lifemytouch.ansi.admin.misc.freeze.listener.FreezeListener;
import de.lifemytouch.ansi.bootstrap.registry.CommandRegistry;
import de.lifemytouch.ansi.coin.listener.CoinListener;
import de.lifemytouch.ansi.dependency.ServerContext;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerInitializer {

    private final JavaPlugin javaPlugin;
    private ServerContext serverContext;
    private CommandRegistry commandRegistry;

    public ServerInitializer(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    public void initialize() {
        serverContext = new ServerContext(javaPlugin);

        commandRegistry = new CommandRegistry(javaPlugin, serverContext);
        commandRegistry.register();

        registerListeners();
    }

    public void registerListeners() {
        PluginManager pluginManager = javaPlugin.getServer().getPluginManager();

        pluginManager.registerEvents(new CoinListener(serverContext.getCoinService()), javaPlugin);
        pluginManager.registerEvents(new FreezeListener(serverContext.getFreezeService()), javaPlugin);
    }

    public void shutdown() {
        if(serverContext != null) serverContext.getDatabaseManager().close();
    }

    public ServerContext getServerContext() {
        return serverContext;
    }
}
