package de.lifemytouch.ansi.bootstrap.registry;

import de.lifemytouch.ansi.admin.build.command.BuildCommand;
import de.lifemytouch.ansi.admin.location.command.LocationCommand;
import de.lifemytouch.ansi.admin.misc.freeze.command.FreezeCommand;
import de.lifemytouch.ansi.admin.system.reload.command.AnsiReloadCommand;
import de.lifemytouch.ansi.coin.command.CoinsCommand;
import de.lifemytouch.ansi.coin.completer.CoinsCompleter;
import de.lifemytouch.ansi.dependency.ServerContext;
import de.lifemytouch.ansi.admin.fly.command.FlyCommand;
import de.lifemytouch.ansi.admin.gamemode.command.GamemodeCommand;
import de.lifemytouch.ansi.misc.discord.DiscordCommand;
import de.lifemytouch.ansi.admin.misc.food.FoodCommand;
import de.lifemytouch.ansi.admin.misc.heal.HealCommand;
import de.lifemytouch.ansi.admin.misc.teleport.command.TeleportCommand;
import de.lifemytouch.ansi.admin.misc.uuid.command.UUIDCommand;
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
        register("gm", new GamemodeCommand(serverContext.getGamemodeService()));
        register("fly", new FlyCommand(serverContext.getFlyService()));
        register("build", new BuildCommand(serverContext.getBuildService()));
        register("coins", new CoinsCommand(serverContext.getCoinService()), new CoinsCompleter());
        register("discord", new DiscordCommand());
        register("uuid", new UUIDCommand());
        register("heal", new HealCommand());
        register("food", new FoodCommand());
        register("teleport", new TeleportCommand(serverContext.getTeleportService()));
        register("location", new LocationCommand(serverContext.getLocationService()));
        register("freeze", new FreezeCommand(serverContext.getFreezeService()));
        register("ansi-reload", new AnsiReloadCommand(serverContext.getAnsiReloadService()));
    }

    private void register(String name, CommandExecutor commandExecutor) {
        register(name, commandExecutor, null);
    }

    private void register(String name, CommandExecutor executor, TabCompleter completer) {
        PluginCommand command = javaPlugin.getCommand(name);

        if (command == null) {
            throw new IllegalStateException(
                    "Command '" + name + "' ist nicht in plugin.yml definiert."
            );
        }

        command.setExecutor(executor);

        if (completer != null) command.setTabCompleter(completer);
    }
}
