package de.lifemytouch.ansi.admin.fly.command;

import de.lifemytouch.ansi.core.command.BaseCommand;
import de.lifemytouch.ansi.admin.fly.service.FlyService;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyCommand extends BaseCommand {

    private final FlyService flyService;

    public FlyCommand(FlyService flyService) {
        this.flyService = flyService;
    }

    @Override
    public String getPermission() {
        return "ansi.commands.fly";
    }

    @Override
    public boolean onCommand(@NotNull Player player, String @NotNull [] args) {

        if (args.length == 0) return flyService.handleFly(player);

        return flyService.sendHelp(player);
    }
}
