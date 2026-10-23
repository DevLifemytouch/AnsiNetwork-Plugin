package de.lifemytouch.ansi.admin.location.service;

import de.lifemytouch.ansi.admin.location.repository.ILocationRepository;
import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.entity.Player;

public class LocationService {

    private final ILocationRepository locationRepository;

    public LocationService(ILocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public boolean setLocation(Player player, String key) {
        locationRepository.saveLocation(key, player.getLocation());

        player.sendMessage(Messages.getPREFIX() + "§7Die Location für §6" + key + "§7 wurde §aerfolgreich §7gesetzt!");

        return true;
    }

    public boolean sendHelp(Player player) {
        player.sendMessage("-------------------------------");
        player.sendMessage(Messages.getPREFIX() + "§7/location §8- §7Zeigt diese Ansicht.");
        player.sendMessage(Messages.getPREFIX() + "§7/location <Location> §8- §7Setzt die ausgewählte Location auf " +
                "§7deine aktuelle Position.");
        player.sendMessage("-------------------------------");
        return true;
    }

}
