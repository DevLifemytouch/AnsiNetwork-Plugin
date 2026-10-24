package de.lifemytouch.ansi.admin.misc.freeze.service;

import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class FreezeService {

    private final Set<Player> freezeSet = new HashSet<>();

    public boolean handleTargetFreeze(Player player, String[] args) {

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) return true;

        if (freezeSet.contains(target)) {
            freezeSet.remove(target);
            player.sendMessage(Messages.getPREFIX() + "§7Du hast §6" + target.getName() + "§7 aufgetaut.");
            return true;
        }

        freezeSet.add(target);
        player.sendMessage(Messages.getPREFIX() + "§7Du hast §6" + target.getName() + "§7 gefreezed.");

        return true;
    }

    public boolean sendHelp(Player player) {
        player.sendMessage("-------------------------------");
        player.sendMessage(Messages.getPREFIX() + "§7/freeze §8- §7Zeigt diese Ansicht.");
        player.sendMessage(Messages.getPREFIX() + "§7/freeze <Player> §8- §7Friert den Spieler ein.");
        player.sendMessage("-------------------------------");
        return true;
    }

    public Set<Player> getFreezeSet() {
        return freezeSet;
    }
}
