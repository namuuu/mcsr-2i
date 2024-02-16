package fr.namu.mcsr2i.runnable;

import fr.namu.mcsr2i.manager.TimerManager;
import fr.namu.mcsr2i.scoreboard.ScoreboardSR;

public class GameRunnable implements Runnable {

    @Override
    public void run() {
        TimerManager.addTimer();
        ScoreboardSR.updateBoard();
    }
}
