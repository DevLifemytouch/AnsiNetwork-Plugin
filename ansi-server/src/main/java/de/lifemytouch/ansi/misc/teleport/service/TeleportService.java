package de.lifemytouch.ansi.misc.teleport.service;

import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class TeleportService {

    public boolean sendHelp(Player player) {

        player.sendMessage("-------------------------------");
        player.sendMessage(Messages.getPREFIX() + "§7/teleport §8- §7Zeigt diese Ansicht.");
        player.sendMessage(Messages.getPREFIX() + "§7/teleport <Player> §8- §7Teleportiert dich zu einem Spieler.");
        player.sendMessage(Messages.getPREFIX() + "§7/teleport <Player> <Player> §8- §7Teleportiert den " +
                "ersten Spieler zu dem zweiten Spieler.");
        player.sendMessage("-------------------------------");

        return true;
    }

    public boolean playerToTarget(Player player, String[] args) {

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) return true;

        player.teleport(target.getLocation());
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

        player.sendMessage(Messages.getPREFIX() + "§7Du wurdest zu §6§l" + target.getName() + "§7 teleportiert.");

        return true;
    }

    public boolean targetToPlayer(Player player, String[] args) {

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) return true;

        target.teleport(player.getLocation());

        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        target.playSound(target.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

        player.sendMessage(Messages.getPREFIX() + "§7Du hast §6§l" + target.getName() + "§7 zu dir teleportiert.");
        target.sendMessage(Messages.getPREFIX() + "§7Du wurdest zu §6§l" + player.getName() + "§7 teleportiert.");

        return true;
    }

}
