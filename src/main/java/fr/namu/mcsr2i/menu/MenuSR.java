package fr.namu.mcsr2i.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class MenuSR {

    protected String title;

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 6*9, title);

        replaceInventory(player, inv);

        player.openInventory(inv);
    }

    public abstract void replaceInventory(Player player, Inventory inventory);

    public abstract void click(Player player, Material mat, String itemName);

    public String getTitle() {
        return title;
    }
}
