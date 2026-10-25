package de.lifemytouch.ansi.admin.misc.weather.service;

import de.lifemytouch.ansi.core.text.Messages;
import org.bukkit.entity.Player;

public class WeatherService {

    public boolean handleWeather(Player player, String[] args) {


        return true;
    }

    public boolean sendHelp(Player player) {
        player.sendMessage("-------------------------------");
        player.sendMessage(Messages.getPREFIX() + "§7/weather §8- §7Zeigt diese Ansicht.");
        player.sendMessage(Messages.getPREFIX() + "§7/weather <clear/rain/thunder> §8- §7Ändert das Wetter.");
        player.sendMessage("-------------------------------");
        return true;
    }

}
