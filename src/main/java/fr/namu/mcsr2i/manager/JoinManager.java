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

        // Scoreboard setup
        ScoreboardSR.sidebar.addPlayer(player);
        ScoreboardSR.updateBoard();

        MainSR.putPlayer(player);
        ItemUtil.lobbyEquip(player);

        player.teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
    }
}
