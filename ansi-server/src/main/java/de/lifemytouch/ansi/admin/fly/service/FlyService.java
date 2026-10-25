package de.lifemytouch.ansi.admin.fly.service;

import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FlyService {

    private List<Player> flyList = new ArrayList<>();

    public boolean sendHelp(Player player) {
        player.sendMessage("-------------------------------");
        player.sendMessage(Messages.getPREFIX() + "§7/fly §8- §7Setzt dich in den Flugmodus.");
        player.sendMessage("-------------------------------");
        return true;
    }

    public boolean handleFly(Player player) {

        if (!flyList.contains(player)) {

            player.setAllowFlight(true);
            player.setFlying(true);
            flyList.add(player);

            return true;
        }

        player.setFlying(false);
        player.setAllowFlight(false);
        flyList.remove(player);

        return true;
    }

}
