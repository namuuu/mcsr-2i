package fr.namu.mcsr2i.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import fr.namu.mcsr2i.enumerator.GameStateEnum;
import fr.namu.mcsr2i.manager.TimerManager;
import fr.namu.mcsr2i.object.GameData;
import fr.namu.mcsr2i.util.TimerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardSR {

    private static final Map<UUID, FastBoard> boards = new HashMap<>();

    private static final String title = "§7• §6§lMCSR2I §7•";

    public static void addPlayer(Player player) {
        boards.put(player.getUniqueId(), new FastBoard(player));
    }

    public static void updateBoard() {
        GameData gameData = GameData.getInstance();

        for (FastBoard board : boards.values()) {
            board.updateTitle(title);

            if(gameData.isState(GameStateEnum.PREGAME))
                lobbyScoreboard(board);
            if(gameData.isState(GameStateEnum.INGAME))
                ingameScoreboard(board);
        }
    }

    private static void lobbyScoreboard(FastBoard board) {

        String[] lines = {
            "§7§m----------------------",
            "Joueurs: §c" + Bukkit.getOnlinePlayers().size(),
            " ",
            "§7§m----------------------"
        };

        board.updateLines(lines);
    }

    private static void ingameScoreboard(FastBoard board) {
        String[] lines = {
                "§7§m----------------------",
                "Timer: §c" + TimerManager.getTextTimer(),
                " ",
                "§7§m----------------------"
        };

        board.updateLines(lines);
    }
}
