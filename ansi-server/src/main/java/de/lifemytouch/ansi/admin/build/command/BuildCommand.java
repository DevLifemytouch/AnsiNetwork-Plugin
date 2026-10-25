package de.lifemytouch.ansi.admin.build.command;

import de.lifemytouch.ansi.admin.build.service.BuildService;
import de.lifemytouch.ansi.core.command.BaseCommand;
import de.lifemytouch.ansi.core.text.Messages;
import de.lifemytouch.ansi.world.inventories.LobbyHotbar;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BuildCommand extends BaseCommand {

    private final BuildService buildService;

    public BuildCommand(BuildService buildService) {
        this.buildService = buildService;
    }

    @Override
    public String getPermission() {
        return "ansi.commands.build";
    }

    @Override
    public boolean onCommand(@NotNull Player player, String @NotNull [] args) {

        if(args.length != 0) return buildService.sendHelp(player);

        return buildService.setBuildMode(player, !buildService.buildList.contains(player));
    }
}
