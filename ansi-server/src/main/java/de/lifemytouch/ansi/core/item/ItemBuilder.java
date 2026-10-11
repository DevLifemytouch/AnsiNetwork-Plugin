package de.lifemytouch.ansi.core.item;

import de.lifemytouch.ansi.core.util.Log;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {

    private String name;
    private String lore;
    private Player player;
    private Material material;

    public static ItemBuilder buildItem() {
        return new ItemBuilder();
    }

    public ItemBuilder setItem(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setLore(String lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder setSkullOwner(Player player) {
        this.player = player;
        return this;
    }

    public ItemStack build() {
        if (material == null) {
            Log.error("Material wurde nicht gesetzt!");
            throw new IllegalStateException();
        }

        ItemStack item = new ItemStack(material);

        ItemMeta itemMeta = item.getItemMeta();

        if (name != null) itemMeta.displayName(Component.text(name));
        if (lore != null) itemMeta.lore(List.of(Component.text(lore)));
        if (player != null && itemMeta instanceof SkullMeta skullMeta) skullMeta.setOwningPlayer(player);

        item.setItemMeta(itemMeta);

        return item;
    }


    public static ItemStack createCustomHead(String value, String name, List<String> lore) {

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);

        SkullMeta meta = (SkullMeta) item.getItemMeta();

        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
        PlayerTextures textures = profile.getTextures();

        try {
            textures.setSkin(new URL(
                    "http://textures.minecraft.net/texture/" + value
            ));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        profile.setTextures(textures);
        meta.setOwnerProfile(profile);

        meta.setDisplayName(name);
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }
}