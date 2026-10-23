package de.lifemytouch.ansi.admin.misc.uuid;

import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UUIDCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) return true;

        if (!player.hasPermission("ansi.commands.uuid")) {
            player.sendMessage(Messages.getNO_PERMS());
            return true;
        }

        if (args.length != 1) {
            sendHelp(player);
            return true;
        }

        handleUUID(player, args);

        return true;
    }

    private void sendHelp(Player player) {
        player.sendMessage(Messages.getPREFIX() + "§7Nutze: §6/uuid §7<Player>");
    }

    private void handleUUID(Player player, String[] args) {
        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        UUID targetUUID = target.getUniqueId();

        player.sendMessage(Messages.getPREFIX() + "§7UUID von §6" + target.getName() + "§7: §f" + targetUUID);
    }
}
