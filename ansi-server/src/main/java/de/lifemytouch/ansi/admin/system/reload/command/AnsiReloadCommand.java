package de.lifemytouch.ansi.admin.system.reload.command;

import de.lifemytouch.ansi.admin.system.reload.service.AnsiReloadService;
import de.lifemytouch.ansi.core.command.BaseCommand;
import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.entity.Player;

public class AnsiReloadCommand extends BaseCommand {

    private final AnsiReloadService reloadService;

    public AnsiReloadCommand(AnsiReloadService reloadService) {
        this.reloadService = reloadService;
    }

    @Override
    public String getPermission() {
        return "ansi.commands.reload";
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        reloadService.reloadPlugin();
        player.sendMessage(Messages.getPREFIX() + "§aDas Plugin wurde neu geladen.");

        return true;
    }

}
