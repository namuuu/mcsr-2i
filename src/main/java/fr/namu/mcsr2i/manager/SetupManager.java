package fr.namu.mcsr2i.manager;

import fr.namu.mcsr2i.MainSR;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SetupManager {

    public void setWorld() {
        for(World world : Bukkit.getWorlds()) {
            world.setDifficulty(Difficulty.PEACEFUL);
            world.setGameRuleValue("doDaylightCycle", "false");
            world.setGameRuleValue("doMobSpawning", "false");
            world.setTime(6000L);
        }


    }

    public void reloadPlayers() {
        MainSR main = MainSR.getInstance();
        JoinManager joinManager = main.joinManager;

        for(Player player : Bukkit.getOnlinePlayers()) {
            joinManager.joinPlayer(player);
        }
    }
}
