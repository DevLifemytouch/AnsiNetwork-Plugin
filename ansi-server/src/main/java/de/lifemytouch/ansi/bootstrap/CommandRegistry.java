package de.lifemytouch.ansi.bootstrap;

import de.lifemytouch.ansi.build.BuildCommand;
import de.lifemytouch.ansi.coin.commands.CoinsCommand;
import de.lifemytouch.ansi.coin.completer.CoinsCompleter;
import de.lifemytouch.ansi.dependency.ServerContext;
import de.lifemytouch.ansi.fly.FlyCommand;
import de.lifemytouch.ansi.gamemode.GamemodeCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandRegistry {

    private final JavaPlugin javaPlugin;
    private final ServerContext serverContext;

    public CommandRegistry(JavaPlugin javaPlugin, ServerContext serverContext) {
        this.javaPlugin = javaPlugin;
        this.serverContext = serverContext;
    }

    public void register() {
        register("gm", new GamemodeCommand());
        register("fly", new FlyCommand(serverContext.getFlyService()));
        register("build", new BuildCommand(serverContext.getBuildService()));
        register("coins", new CoinsCommand(serverContext.getCoinService()), new CoinsCompleter());
    }

    private void register(String name, CommandExecutor commandExecutor) {
        register(name, commandExecutor, null);
    }

    private void register(
            String name,
            CommandExecutor executor,
            TabCompleter completer
    ) {
        PluginCommand command = javaPlugin.getCommand(name);

        if (command == null) {
            throw new IllegalStateException(
                    "Command '" + name + "' ist nicht in plugin.yml definiert."
            );
        }

        command.setExecutor(executor);

        if (completer != null) {
            command.setTabCompleter(completer);
        }
    }
}
