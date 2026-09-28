package de.lifemytouch.ansi.gamemode;

import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(
            @NonNull CommandSender sender,
            @NonNull Command command,
            @NonNull String label,
            String @NonNull [] args
    ) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (!player.hasPermission("ansi.commands.gm")) {
            player.sendMessage(Messages.getNO_PERMS());
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(
                    Messages.getPREFIX()
                            + "§7Dein Spielmodus: §6§l"
                            + player.getGameMode()
            );
            return true;
        }

        GameMode gameMode = parseGameMode(args[0]);

        if (gameMode == null) {
            player.sendMessage(
                    Messages.getPREFIX()
                            + "§7Ungültiger Spielmodus. Verwende §60§7, §61§7, §62 §7oder §63§7."
            );
            return false;
        }

        if (args.length == 1) {
            setPlayerGameMode(player, player, gameMode);
            return true;
        }

        if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                player.sendMessage(
                        Messages.getPREFIX()
                                + "§7Spieler nicht online!"
                );
                return false;
            }

            setPlayerGameMode(player, target, gameMode);
            return true;
        }

        return false;
    }

    private void setPlayerGameMode(
            Player sender,
            Player target,
            GameMode gameMode
    ) {
        target.setGameMode(gameMode);

        target.sendMessage(
                Messages.getPREFIX()
                        + "§7Dein Spielmodus wurde auf §6§l"
                        + gameMode
                        + " §7gesetzt."
        );

        if (sender != target) {
            sender.sendMessage(
                    Messages.getPREFIX()
                            + "§7Du hast den Spielmodus von §c"
                            + target.getName()
                            + "§7 auf §6§l"
                            + gameMode
                            + " §7gesetzt."
            );
        }
    }

    private GameMode parseGameMode(String input) {
        return switch (input.toLowerCase()) {
            case "0", "survival" -> GameMode.SURVIVAL;
            case "1", "creative" -> GameMode.CREATIVE;
            case "2", "adventure" -> GameMode.ADVENTURE;
            case "3", "spectator" -> GameMode.SPECTATOR;
            default -> null;
        };
    }

}
