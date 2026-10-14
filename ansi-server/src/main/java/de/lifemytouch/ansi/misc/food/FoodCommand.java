package de.lifemytouch.ansi.misc.food;

import de.lifemytouch.ansi.core.text.Messages;
import de.lifemytouch.ansi.core.util.Log;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FoodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) {
            Log.warn("Führe es als Spieler aus");
            return true;
        }

        player.setSaturation(20);
        player.sendMessage(Messages.getPREFIX() + "§7Du wurdest §agefüttert§7.");

        return true;
    }
}
