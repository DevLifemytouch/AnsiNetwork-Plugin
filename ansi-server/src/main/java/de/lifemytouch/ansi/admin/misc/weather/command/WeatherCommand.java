package de.lifemytouch.ansi.admin.misc.weather.command;

import de.lifemytouch.ansi.admin.misc.weather.service.WeatherService;
import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WeatherCommand implements CommandExecutor {

    private final WeatherService weatherService;

    public WeatherCommand(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) return true;

        if (!player.hasPermission("ansi.commands.weather")) {
            player.sendMessage(Messages.getNO_PERMS());
            return true;
        }

        if (args.length == 1) return weatherService.handleWeather(player, args);

        return weatherService.sendHelp(player);
    }
}
