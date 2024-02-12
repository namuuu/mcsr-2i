package fr.namu.mcsr2i.manager;

import fr.namu.mcsr2i.util.TimerUtil;

public class TimerManager {

    public static int timer = 0;

    public static int getTimer() {
        return timer;
    }

    public static void addTimer() {
        timer++;
    }

    public static String getTextTimer() {
        return TimerUtil.conversion(timer);
    }
}
