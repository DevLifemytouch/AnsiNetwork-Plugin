package de.lifemytouch.ansi.misc.teleport.command;

import de.lifemytouch.ansi.core.text.Messages;
import de.lifemytouch.ansi.misc.teleport.service.TeleportService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportCommand implements CommandExecutor {

    private final TeleportService teleportService;

    public TeleportCommand(TeleportService teleportService) {
        this.teleportService = teleportService;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) return true;

        if (!player.hasPermission("ansi.commands.teleport")) {
            player.sendMessage(Messages.getNO_PERMS());
            return true;
        }

        if (args.length == 1) return teleportService.playerToTarget(player, args);

        if (args.length == 2) return teleportService.targetToPlayer(player, args);

        return teleportService.sendHelp(player);
    }
}
