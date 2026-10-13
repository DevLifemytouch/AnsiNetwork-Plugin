package de.lifemytouch.ansi.build.service;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildService {

    public List<Player> buildList = new ArrayList<>();

    public void setBuildMode(Player player, boolean buildMode) {
        if (!buildMode) {
            buildList.remove(player);
        } else {
            buildList.add(player);
        }
    }

}
