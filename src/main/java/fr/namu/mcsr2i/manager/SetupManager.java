package fr.namu.mcsr2i.manager;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GroupEnum;
import fr.namu.mcsr2i.enumerator.TeamEnum;
import fr.namu.mcsr2i.object.TeamSR;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class SetupManager {

    public void setWorld() {
        for(World world : Bukkit.getWorlds()) {
            world.setDifficulty(Difficulty.PEACEFUL);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            world.setTime(6000L);
        }

        World world = Objects.requireNonNull(Bukkit.getWorld("world"));
        int x = 0;
        int z = 0;
        int y = 169;

        world.setSpawnLocation(x, y+1, z); // 184
        for (int i = -16; i <= 16; i++) {
            for (int j = -16; j <= 16; j++) {
                (new Location(world, (i + x), y-1, (j + z))).getBlock().setType(Material.BARRIER);
            }
            (new Location(world, (i + x), y  , (z - 16))).getBlock().setType(Material.BARRIER);
            (new Location(world, (i + x), y+1, (z - 16))).getBlock().setType(Material.BARRIER);
            (new Location(world, (i + x), y+3, (z - 16))).getBlock().setType(Material.BARRIER);
            (new Location(world, (i + x), y  , (z + 16))).getBlock().setType(Material.BARRIER);
            (new Location(world, (i + x), y+1, (z + 16))).getBlock().setType(Material.BARRIER);
            (new Location(world, (i + x), y+2, (z + 16))).getBlock().setType(Material.BARRIER);
            (new Location(world, (x - 16), y  , (i + z))).getBlock().setType(Material.BARRIER);
            (new Location(world, (x - 16), y+1, (i + z))).getBlock().setType(Material.BARRIER);
            (new Location(world, (x - 16), y+2, (i + z))).getBlock().setType(Material.BARRIER);
            (new Location(world, (x + 16), y  , (i + z))).getBlock().setType(Material.BARRIER);
            (new Location(world, (x + 16), y+1, (i + z))).getBlock().setType(Material.BARRIER);
            (new Location(world, (x + 16), y+2, (i + z))).getBlock().setType(Material.BARRIER);
        }
    }

    public void reloadPlayers() {
        MainSR main = MainSR.getInstance();
        JoinManager joinManager = main.joinManager;

        for(Player player : Bukkit.getOnlinePlayers()) {
            joinManager.joinPlayer(player);
        }
    }

    public void setupTeams() {
        Scoreboard sb = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();

        for(Team team : sb.getTeams())
            team.unregister();

        for(TeamEnum team : TeamEnum.values()) {
            MainSR.putTeam(new TeamSR(team));
            sb.registerNewTeam(team.getName()).setPrefix(team.getPrefix());
        }

        sb.registerNewTeam(GroupEnum.SPECTATOR.getName()).setPrefix(GroupEnum.SPECTATOR.getPrefix());
        sb.registerNewTeam(GroupEnum.HOST.getName()).setPrefix(GroupEnum.HOST.getPrefix());
    }
}
