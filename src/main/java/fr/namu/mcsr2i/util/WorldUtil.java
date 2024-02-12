package fr.namu.mcsr2i.util;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.object.PlayerSR;
import fr.namu.mcsr2i.object.TeamSR;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;

public class WorldUtil {

    public static void teleportPlayers() {
        int i = 0;

        for(TeamSR team : MainSR.getTeams()) {
            if(!team.isEnabled())
                continue;

            World world = Bukkit.getWorld("world");
            int worldSize = 1000;

            double a = 2 * Math.PI * i / MainSR.getTeams().size();
            int x = (int) Math.round((double) worldSize / 3 * Math.cos(a));
            int z = (int) Math.round((double) worldSize / 3 * Math.sin(a));
            assert world != null;
            int y = world.getHighestBlockYAt(x, z) + 30;
            Location loc = new Location(world, x, y, z);
            team.setSpawn(loc);
            i++;

            for(PlayerSR psr : team.getPlayers()) {
                psr.getPlayer().teleport(loc);
            }
        }


    }
}
