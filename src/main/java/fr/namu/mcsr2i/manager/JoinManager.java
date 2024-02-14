package fr.namu.mcsr2i.manager;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.object.PlayerSR;
import fr.namu.mcsr2i.scoreboard.ScoreboardSR;
import fr.namu.mcsr2i.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;

public class JoinManager {

    private final String[] autoHostPlayers = {
            "NamuFoxo"
    };

    public void joinPlayer(Player player) {
        Bukkit.broadcastMessage("§a+ §7» §e" + player.getName());

        // Scoreboard setup
        ScoreboardSR.addPlayer(player);
        ScoreboardSR.updateBoard();

        MainSR.putPlayer(player);

        // if player is in autoHostPlayers
        for(String autoHostPlayer : autoHostPlayers) {
            if(player.getName().equals(autoHostPlayer)) {
                PlayerSR psr = MainSR.getPlayer(player.getUniqueId());
                psr.setHost(true);
            }
        }

        ItemUtil.lobbyEquip(player);

        System.out.println("Player " + player.getName() + " joined the game!");
        player.teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
    }
}
