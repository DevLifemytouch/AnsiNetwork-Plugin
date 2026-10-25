package de.lifemytouch.ansi.admin.misc.uuid.command;

import de.lifemytouch.ansi.admin.misc.uuid.service.UUIDService;
import de.lifemytouch.ansi.core.command.BaseCommand;
import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UUIDCommand extends BaseCommand {

    private final UUIDService uuidService;

    public UUIDCommand(UUIDService uuidService) {
        this.uuidService = uuidService;
    }

    @Override
    public String getPermission() {
        return "ansi.commands.uuid";
    }

    @Override
    public boolean onCommand(@NotNull Player player, @NotNull String @NotNull [] args) {

        if (args.length == 1) return uuidService.handleUUID(player, args);

        return uuidService.sendHelp(player);
    }

}
