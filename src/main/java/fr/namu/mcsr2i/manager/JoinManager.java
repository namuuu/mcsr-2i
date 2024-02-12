package fr.namu.mcsr2i.manager;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.scoreboard.ScoreboardSR;
import fr.namu.mcsr2i.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class JoinManager {

    public void joinPlayer(Player player) {
        MainSR main = MainSR.getInstance();

        Bukkit.broadcastMessage("§a+ §7» §e" + player.getName());

//        main.setBoard(player.getUniqueId(), new FastBoard(player));
        ScoreboardSR sb = MainSR.getInstance().scoreboard;
        sb.addPlayer(player);

        MainSR.putPlayer(player);
        ItemUtil.lobbyEquip(player);

        // Give Saturation Potion Effect
        for(PotionEffect pe : player.getActivePotionEffects())
            player.removePotionEffect(pe.getType());
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0, false , false));

        player.teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
    }
}
