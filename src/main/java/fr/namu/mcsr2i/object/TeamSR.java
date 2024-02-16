package fr.namu.mcsr2i.object;

import fr.namu.mcsr2i.enumerator.GameStateEnum;
import fr.namu.mcsr2i.enumerator.GroupEnum;
import fr.namu.mcsr2i.enumerator.TeamEnum;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Objects;

public class TeamSR {

    private TeamEnum teamEnum;
    private final ArrayList<PlayerSR> players = new ArrayList<>();

    private Location spawn;

    public TeamSR(TeamEnum team) {
        this.teamEnum = team;
    }

    public String getName() {
        return teamEnum.getName();
    }

    public String getPrefix() {
        return teamEnum.getPrefix();
    }

    public boolean isEnabled() {
        return teamEnum.isEnabled();
    }

    public int getSize() {
        return teamEnum.getSize();
    }

    public Material getBanner() {
        return teamEnum.getColor();
    }

    public void setEnabled(boolean isEnabled) {
        teamEnum.setEnabled(isEnabled);
    }

    public void toggle() {
        teamEnum.toggle();
    }

    public void setSize(int size) {
        teamEnum.setSize(size);
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location loc) {
        this.spawn = loc;
    }

    public Material getGlassColor() {
        return teamEnum.getGlassColor();
    }

    public void addPlayer(PlayerSR psr) {
        if(psr.getTeam() != null) {
            psr.getTeam().removePlayer(psr);
            Objects.requireNonNull(Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard().getTeam(teamEnum.getName())).removeEntry(psr.getName());
        }

        players.add(psr);
        psr.setTeam(this);
        psr.setGroup(GroupEnum.PLAYER);

        // Change player hover name
        Objects.requireNonNull(Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard().getTeam(teamEnum.getName())).addEntry(psr.getName());
        // Change player list name
        psr.getPlayer().setPlayerListName(teamEnum.getPrefix() + psr.getName());

        if(GameData.getInstance().isState(GameStateEnum.PREGAME))
            psr.getPlayer().sendMessage("§eVous avez rejoint l'équipe " + this.getName());
    }

    public void removePlayer(PlayerSR player) {
        players.remove(player);
        player.setTeam(null);

        // Change player hover name
        Objects.requireNonNull(Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard().getTeam(teamEnum.getName())).removeEntry(player.getName());
        // Change player list name
        player.getPlayer().setPlayerListName(player.getName());
    }

    public ArrayList<PlayerSR> getPlayers() {
        return players;
    }
}
