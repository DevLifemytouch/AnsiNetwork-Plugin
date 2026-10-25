package de.lifemytouch.ansi.core.command;

import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public abstract class BaseCommand implements CommandExecutor {

    public abstract boolean onCommand(Player player, String[] args);

    public abstract String getPermission();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, String @NonNull [] args) {
        if (!(sender instanceof Player player)) return true;

        if (!player.hasPermission(getPermission())) {
            player.sendMessage(Messages.getNO_PERMS());
            return true;
        }

        return onCommand(player, args);
    }
}
