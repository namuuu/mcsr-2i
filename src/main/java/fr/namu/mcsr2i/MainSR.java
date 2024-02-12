package fr.namu.mcsr2i;

import fr.namu.mcsr2i.manager.JoinManager;
import fr.namu.mcsr2i.manager.SetupManager;
import fr.namu.mcsr2i.manager.TeamManager;
import fr.namu.mcsr2i.object.GameData;
import fr.namu.mcsr2i.object.PlayerSR;
import fr.namu.mcsr2i.object.TeamSR;
import fr.namu.mcsr2i.scoreboard.ScoreboardSR;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainSR extends JavaPlugin {

    private static MainSR instance;

    private static final Map<UUID, PlayerSR> players = new HashMap<>();
    private static final ArrayList<TeamSR> teams = new ArrayList<>();

    private final GameData gameData = new GameData();

    public final SetupManager setupManager = new SetupManager();
    public final JoinManager joinManager = new JoinManager();
    public final TeamManager teamManager = new TeamManager();

    public final ScoreboardSR scoreboard = new ScoreboardSR();

    @Override
    public void onEnable() {
        instance = this;

        scoreboard.setup();

        registerEvents();
        registerCommands();

        setupManager.setWorld();
        setupManager.setupTeams();
        setupManager.reloadPlayers();
    }

    @Override
    public void onDisable() {
        ScoreboardSR.lib.close();
    }

    public static MainSR getInstance() {
        return instance;
    }

    public static Map<UUID, PlayerSR> getPlayers() {
        return players;
    }

    public static PlayerSR getPlayer(UUID uuid) {
        return players.get(uuid);
    }

    public static ArrayList<TeamSR> getTeams() {
        return teams;
    }

    public static TeamSR getTeam(String name) {
        for(TeamSR team : teams) {
            if(team.getName().equalsIgnoreCase(name))
                return team;
        }
        return null;
    }

    public static void putTeam(TeamSR team) {
        teams.add(team);
    }

    public static void putPlayer(Player player) {
        if(!players.containsKey(player.getUniqueId()))
            players.put(player.getUniqueId(), new PlayerSR(player));
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new fr.namu.mcsr2i.listener.JoinLeaveEvent(), this);
        getServer().getPluginManager().registerEvents(new fr.namu.mcsr2i.listener.PlayerDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new fr.namu.mcsr2i.listener.InteractEvent(), this);
        getServer().getPluginManager().registerEvents(new fr.namu.mcsr2i.listener.ClickEvent(), this);
    }

    private void registerCommands() {
        getCommand("start").setExecutor(new fr.namu.mcsr2i.commands.StartCommand());
    }
}
