package de.lifemytouch.ansi.admin.misc.freeze.command;

import de.lifemytouch.ansi.admin.misc.freeze.service.FreezeService;
import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FreezeCommand implements CommandExecutor {

    private final FreezeService freezeService;

    public FreezeCommand(FreezeService freezeService) {
        this.freezeService = freezeService;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) return true;

        if (!player.hasPermission("ansi.commands.freeze")) {
            player.sendMessage(Messages.getNO_PERMS());
            return true;
        }

        if (args.length == 1) return freezeService.handleTargetFreeze(player, args);

        return freezeService.sendHelp(player);
    }
}
