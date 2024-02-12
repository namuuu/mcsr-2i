package fr.namu.mcsr2i;

import fr.namu.mcsr2i.manager.JoinManager;
import fr.namu.mcsr2i.manager.SetupManager;
import fr.namu.mcsr2i.object.GameData;
import fr.namu.mcsr2i.object.PlayerSR;
import fr.namu.mcsr2i.scoreboard.FastBoard;
import fr.namu.mcsr2i.scoreboard.ScoreboardSR;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainSR extends JavaPlugin {

    private static MainSR instance;

    private static final Map<UUID, FastBoard> boards = new HashMap<>();
    private static final Map<UUID, PlayerSR> players = new HashMap<>();

    private final GameData gameData = new GameData();
    public final SetupManager setupManager = new SetupManager();
    public final JoinManager joinManager = new JoinManager();
    public final ScoreboardSR scoreboard = new ScoreboardSR();

    public static MainSR getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        registerEvents();
        setupManager.setWorld();
        setupManager.reloadPlayers();
    }

    public static Map<UUID, FastBoard> getBoards() {
        return boards;
    }

    public static FastBoard getBoard(UUID uuid) {
        return boards.get(uuid);
    }

    public void setBoard(UUID uuid, FastBoard board) {
        boards.put(uuid, board);
    }

    public static Map<UUID, PlayerSR> getPlayers() {
        return players;
    }

    public static PlayerSR getPlayer(UUID uuid) {
        return players.get(uuid);
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new fr.namu.mcsr2i.listener.JoinLeaveEvent(), this);
        getServer().getPluginManager().registerEvents(new fr.namu.mcsr2i.listener.PlayerDamageEvent(), this);
    }
}
