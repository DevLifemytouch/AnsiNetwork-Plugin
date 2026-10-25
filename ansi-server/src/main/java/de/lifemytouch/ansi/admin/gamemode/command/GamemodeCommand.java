package de.lifemytouch.ansi.admin.gamemode.command;

import de.lifemytouch.ansi.admin.gamemode.service.GamemodeService;
import de.lifemytouch.ansi.core.command.BaseCommand;
import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class GamemodeCommand extends BaseCommand {

    private final GamemodeService gamemodeService;

    public GamemodeCommand(GamemodeService gamemodeService) {
        this.gamemodeService = gamemodeService;
    }

    @Override
    public String getPermission() {
        return "ansi.commands.gm";
    }

    @Override
    public boolean onCommand(@NonNull Player player, String @NonNull [] args) {

        if (!player.hasPermission("ansi.commands.gm")) {
            player.sendMessage(Messages.getNO_PERMS());
            return true;
        }

        return true;
    }

}
