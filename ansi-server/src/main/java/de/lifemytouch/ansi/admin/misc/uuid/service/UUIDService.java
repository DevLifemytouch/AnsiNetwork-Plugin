package de.lifemytouch.ansi.admin.misc.uuid.service;

import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class UUIDService {

    public boolean sendHelp(Player player) {
        player.sendMessage(Messages.getPREFIX() + "§7Nutze: §6/uuid §7<Player>");
        return true;
    }

    public boolean handleUUID(Player player, String[] args) {
        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        UUID targetUUID = target.getUniqueId();

        player.sendMessage(Messages.getPREFIX() + "§7UUID von §6" + target.getName() + "§7: §f" + targetUUID);

        return true;
    }

}
