package de.lifemytouch.ansi.admin.location.command;

import de.lifemytouch.ansi.admin.location.service.LocationService;
import de.lifemytouch.ansi.core.command.BaseCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LocationCommand extends BaseCommand {

    private final LocationService locationService;

    public LocationCommand(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public String getPermission() {
        return "ansi.commands.location";
    }

    @Override
    public boolean onCommand(@NotNull Player player, @NotNull String @NotNull [] args) {

        if (args.length == 1) return locationService.setLocation(player, args[0]);

        return locationService.sendHelp(player);
    }

}
