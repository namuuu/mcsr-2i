package fr.namu.mcsr2i.scoreboard;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GameStateEnum;
import fr.namu.mcsr2i.object.GameData;
import org.bukkit.Bukkit;

public class ScoreboardSR {

    private String title = "§7• §6§lMCSR2I §7•";

    public void updateBoard() {
        GameData gameData = GameData.getInstance();

        String[] lines = getLines();

        for(FastBoard board : MainSR.getBoards().values()) {
            board.updateTitle(title);
            board.updateLines(lines);
        }
    }

    private String[] getLines() {
        GameData gameData = GameData.getInstance();

        if(gameData.isState(GameStateEnum.PREGAME)) {
            return new String[]{
                    "§7§m----------------------",
                    "Joueurs: §c" + Bukkit.getOnlinePlayers().size(),
                    " ",
                    "§7Ceci est un événement BDA !",
                    "§7§m----------------------"
            };
        }

        return new String[]{
            "§7 Il y a un problème avec la configuration du scoreboard."
        };
    }
}
