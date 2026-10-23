package de.lifemytouch.ansi.admin.location.repository;

import org.bukkit.Location;
import java.util.Optional;

public interface ILocationRepository {
    void saveLocation(String key, Location location);
    Optional<Location> loadLocation(String key);
    void deleteLocation(String key);
}
