package fr.namu.mcsr2i.scoreboard;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GameStateEnum;
import fr.namu.mcsr2i.manager.TimerManager;
import fr.namu.mcsr2i.object.GameData;
import net.megavex.scoreboardlibrary.api.ScoreboardLibrary;
import net.megavex.scoreboardlibrary.api.exception.NoPacketAdapterAvailableException;
import net.megavex.scoreboardlibrary.api.noop.NoopScoreboardLibrary;
import net.megavex.scoreboardlibrary.api.sidebar.Sidebar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;

public class ScoreboardSR {

    public static ScoreboardLibrary lib;
    public static Sidebar sidebar;

    public void setup() {
        MainSR main = MainSR.getInstance();

        try {
            lib = ScoreboardLibrary.loadScoreboardLibrary(main);
        } catch (NoPacketAdapterAvailableException e) {
            // If no packet adapter was found, you can fallback to the no-op implementation:
            lib = new NoopScoreboardLibrary();
            main.getLogger().warning("No scoreboard packet adapter available!");
        }
        instantiateBoard();
    }

    public void addPlayer(Player player) {
        sidebar.addPlayer(player);
    }

    public void instantiateBoard() {
        sidebar = lib.createSidebar();

        for(Player player : Bukkit.getOnlinePlayers()) {
            sidebar.addPlayer(player);
        }
    }

    public static void updateBoard() {
        GameData gameData = GameData.getInstance();

        if(gameData.isState(GameStateEnum.PREGAME))
            lobbyScoreboard();
        if(gameData.isState(GameStateEnum.INGAME))
            ingameScoreboard();

    }

    private static void lobbyScoreboard() {
        String title = "§7• §6§lMCSR2I §7•";
        sidebar.title(Component.text(title));
        sidebar.line(1, Component.text("§7§m----------------------"));
        sidebar.line(2, Component.text("Joueurs: §c" + Bukkit.getOnlinePlayers().size()));
        sidebar.line(3, Component.empty());
        sidebar.line(4, Component.text("§7§m----------------------"));
    }

    private static void ingameScoreboard() {
        String title = "§7• §6§lMCSR2I §7•";
        sidebar.title(Component.text(title));
        sidebar.line(1, Component.text("§7§m----------------------"));
        sidebar.line(2, Component.text("Timer: §c" + TimerManager.getTextTimer()));
        sidebar.line(3, Component.empty());
        sidebar.line(4, Component.text("§7§m----------------------"));
    }
}
