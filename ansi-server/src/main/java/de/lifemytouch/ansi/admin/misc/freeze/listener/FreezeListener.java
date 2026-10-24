package de.lifemytouch.ansi.admin.misc.freeze.listener;

import de.lifemytouch.ansi.admin.misc.freeze.service.FreezeService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeListener implements Listener {

    private final FreezeService freezeService;

    public FreezeListener(FreezeService freezeService) {
        this.freezeService = freezeService;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (freezeService.getFreezeSet().contains(player)) {
            event.setCancelled(true);
        }
    }

}
