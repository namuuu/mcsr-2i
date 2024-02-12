package fr.namu.mcsr2i.util;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GroupEnum;
import fr.namu.mcsr2i.object.PlayerSR;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

        // Give Saturation Potion Effect
        for(PotionEffect pe : player.getActivePotionEffects())
            player.removePotionEffect(pe.getType());
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0, false , false));

        inv.setItem(4, ItemBuilder.teamBannerHotbar(psr.getTeam()));

        if(psr.getGroup() == GroupEnum.HOST)
            inv.setItem(8, new ItemBuilder(Material.NETHER_STAR, 1)
                    .setName("§eMenu du Host ")
                    .toItemStack()
            );
    }

    public static void gameEquip(Player player) {
        PlayerSR psr = MainSR.getPlayer(player.getUniqueId());
        Inventory inv = player.getInventory();
        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setExp(0.0F);
        player.setLevel(0);
        player.setFireTicks(0);
        player.setSaturation(20.0F);

        if(psr == null)
            return;

        switch (psr.getGroup())  {
            case PLAYER:
                player.setGameMode(GameMode.SURVIVAL);
                break;
            case SPECTATOR:
            case HOST:
            default:
                player.setGameMode(GameMode.SPECTATOR);
                break;
        }

        for(PotionEffect pe : player.getActivePotionEffects())
            player.removePotionEffect(pe.getType());
    }
}
