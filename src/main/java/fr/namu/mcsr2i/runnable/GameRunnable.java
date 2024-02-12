package fr.namu.mcsr2i.runnable;

import fr.namu.mcsr2i.manager.TimerManager;
import fr.namu.mcsr2i.scoreboard.ScoreboardSR;
import org.bukkit.scheduler.BukkitRunnable;

public class GameRunnable extends BukkitRunnable {

    @Override
    public void run() {
        TimerManager.addTimer();
        ScoreboardSR.updateBoard();
    }
}
