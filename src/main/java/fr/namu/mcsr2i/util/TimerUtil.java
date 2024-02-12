package fr.namu.mcsr2i.util;

public class TimerUtil {

    public static String conversion(int timer) {
        String valeur;
        if (timer % 60 > 9) {
            valeur = timer % 60 + "s";
        } else {
            valeur = "0" + (timer % 60) + "s";
        }
        if (timer / 3600 > 0) {
            if (timer % 3600 / 60 > 9) {
                valeur = timer / 3600 + "h" + (timer % 3600 / 60) + "m" + valeur;
            } else {
                valeur = timer / 3600 + "h0" + (timer % 3600 / 60) + "m" + valeur;
            }
        } else if (timer / 60 > 0) {
            valeur = timer / 60 + "m" + valeur;
        }
        return valeur;
    }
}
