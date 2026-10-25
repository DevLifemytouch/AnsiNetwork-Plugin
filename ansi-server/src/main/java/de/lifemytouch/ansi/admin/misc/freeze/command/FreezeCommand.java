package de.lifemytouch.ansi.admin.misc.freeze.command;

import de.lifemytouch.ansi.admin.misc.freeze.service.FreezeService;
import de.lifemytouch.ansi.core.command.BaseCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FreezeCommand extends BaseCommand {

    private final FreezeService freezeService;

    public FreezeCommand(FreezeService freezeService) {
        this.freezeService = freezeService;
    }

    @Override
    public String getPermission() {
        return "ansi.commands.freeze";
    }

    @Override
    public boolean onCommand(@NotNull Player player, @NotNull String @NotNull [] args) {

        if (args.length == 1) return freezeService.handleTargetFreeze(player, args);

        return freezeService.sendHelp(player);
    }
}
