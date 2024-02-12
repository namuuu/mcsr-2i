package fr.namu.mcsr2i.manager;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.scoreboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinManager {

    public void joinPlayer(Player player) {
        MainSR main = MainSR.getInstance();

        Bukkit.broadcastMessage("§a+ §7» §e" + player.getName());

        main.setBoard(player.getUniqueId(), new FastBoard(player));
        main.scoreboard.updateBoard();

        MainSR.getPlayer(player.getUniqueId());

        // Clear Player
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
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0, false , false));
    }
}
