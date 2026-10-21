package de.lifemytouch.ansi.coin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BaltopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) return true;

        if (args.length == 0) return handleDefaultTop(args, player);

        return true;
    }

    private boolean handleDefaultTop(String[] args, Player player) {


        return true;
    }
}
