package de.lifemytouch.ansi.admin.misc.heal;

import de.lifemytouch.ansi.core.command.BaseCommand;
import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealCommand extends BaseCommand {

    @Override
    public String getPermission() {
        return "ansi.commands.heal";
    }

    @Override
    public boolean onCommand(@NotNull Player player, @NotNull String @NotNull [] args) {

        player.setHealth(20);
        player.sendMessage(Messages.getPREFIX() + "§7Du wurdest §ageheilt§7.");

        return true;
    }
}