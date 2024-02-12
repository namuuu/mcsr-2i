package fr.namu.mcsr2i.util;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GroupEnum;
import fr.namu.mcsr2i.object.PlayerSR;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ItemUtil {

    public static void lobbyEquip(Player player) {
        PlayerSR psr = MainSR.getPlayer(player.getUniqueId());

        if(psr == null)
            return;

        Inventory inv = player.getInventory();
        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setExp(0.0F);
        player.setLevel(0);
        player.setFireTicks(0);
        player.setSaturation(20.0F);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setGameMode(GameMode.ADVENTURE);

        inv.setItem(4, ItemBuilder.teamBannerHotbar(psr.getTeam()));

        if(psr.getGroup() == GroupEnum.HOST)
            inv.setItem(8, new ItemBuilder(Material.NETHER_STAR, 1)
                    .setName("§eMenu du Host ")
                    .toItemStack()
            );
    }
}
