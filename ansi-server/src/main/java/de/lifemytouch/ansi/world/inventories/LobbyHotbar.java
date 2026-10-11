package de.lifemytouch.ansi.world.inventories;

import de.lifemytouch.ansi.core.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class LobbyHotbar {

    public static void setSpawnItems(Player player) {

        Inventory inventory = player.getInventory();

        inventory.setItem(0, ItemBuilder.createCustomHead(
                "cc1b2f592cfc8d372dcf5fd44eed69dddc64601d7846d72619f70511d8043a89",
                "§6§lCosmetics",
                List.of("§7Öffnet die Cosmetics")
        ));

        inventory.setItem(4,
                ItemBuilder.buildItem().setItem(Material.COMPASS).setLore("§7Öffnet dir den Navigator!").build()
        );

        inventory.setItem(7,
                ItemBuilder.buildItem().setItem(Material.NETHER_STAR).setLore("§7Öffnet die Serverauswahl").build()
        );

        inventory.setItem(8,
                ItemBuilder.buildItem().setItem(Material.PLAYER_HEAD).setName("§9§lProfil")
                        .setLore("§7Öffnet dein Profil!").setSkullOwner(player).build()
        );

    }

}
