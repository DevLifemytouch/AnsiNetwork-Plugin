package de.lifemytouch.ansi.admin.build.service;

import de.lifemytouch.ansi.core.text.Messages;
import de.lifemytouch.ansi.world.inventories.LobbyHotbar;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildService {

    public List<Player> buildList = new ArrayList<>();

    public boolean sendHelp(Player player) {
        player.sendMessage("-------------------------------");
        player.sendMessage(Messages.getPREFIX() + "§7/build §8- §7Setzt dich in den Bau-Modus.");
        player.sendMessage("-------------------------------");

        return true;
    }

    public boolean setBuildMode(Player player, boolean buildMode) {
        if (!buildMode) {
            buildList.remove(player);
            player.setGameMode(GameMode.SURVIVAL);
            player.getInventory().clear();
            LobbyHotbar.setSpawnItems(player);
            player.sendMessage(Messages.getPREFIX() + "§7Du bist nun nicht mehr im Build-Modus!");

            return true;
        }

        buildList.add(player);
        player.setGameMode(GameMode.CREATIVE);
        player.getInventory().clear();
        player.sendMessage(Messages.getPREFIX() + "§7Du bist nun im Build-Modus!");

        return true;
    }

}
