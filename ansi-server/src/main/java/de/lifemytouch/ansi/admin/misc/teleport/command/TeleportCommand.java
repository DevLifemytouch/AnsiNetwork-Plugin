package de.lifemytouch.ansi.admin.misc.teleport.command;

import de.lifemytouch.ansi.core.command.BaseCommand;
import de.lifemytouch.ansi.admin.misc.teleport.service.TeleportService;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportCommand extends BaseCommand {

    private final TeleportService teleportService;

    public TeleportCommand(TeleportService teleportService) {
        this.teleportService = teleportService;
    }

    @Override
    public String getPermission() {
        return "ansi.commands.teleport";
    }

    @Override
    public boolean onCommand(@NotNull Player player, @NotNull String @NotNull [] args) {

        if (args.length == 1) return teleportService.playerToTarget(player, args);

        if (args.length == 2) return teleportService.targetToPlayer(player, args);

        return teleportService.sendHelp(player);
    }
}
