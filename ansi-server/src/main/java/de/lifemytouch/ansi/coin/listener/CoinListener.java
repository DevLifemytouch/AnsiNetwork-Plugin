package de.lifemytouch.ansi.coin.listener;

import de.lifemytouch.ansi.coin.CoinService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CoinListener implements Listener {

    private final CoinService coinService;

    public CoinListener(CoinService coinService) {
        this.coinService = coinService;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        coinService.updatePlayerName(event.getPlayer().getUniqueId(), event.getPlayer().getName());
    }

}
