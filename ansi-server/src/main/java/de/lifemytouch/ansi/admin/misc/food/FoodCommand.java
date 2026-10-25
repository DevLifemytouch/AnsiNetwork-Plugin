package de.lifemytouch.ansi.admin.misc.food;

import de.lifemytouch.ansi.core.command.BaseCommand;
import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FoodCommand extends BaseCommand {

    @Override
    public String getPermission() {
        return "ansi.command.food";
    }

    @Override
    public boolean onCommand(@NotNull Player player, @NotNull String @NotNull [] args) {

        player.setSaturation(20);
        player.sendMessage(Messages.getPREFIX() + "§7Dein Hunger wurde gefüllt.");

        return true;
    }
}
