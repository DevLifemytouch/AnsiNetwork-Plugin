package de.lifemytouch.ansi.admin.location.command;

import de.lifemytouch.ansi.admin.location.service.LocationService;
import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LocationCommand implements CommandExecutor {

    private final LocationService locationService;

    public LocationCommand(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) return true;

        if (!player.hasPermission("ansi.commands.location")) {
            player.sendMessage(Messages.getNO_PERMS());
            return true;
        }

        if (args.length == 1) {
            return locationService.setLocation(player, args[0]);
        }

        return locationService.sendHelp(player);
    }

}
