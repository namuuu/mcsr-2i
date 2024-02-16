package fr.namu.mcsr2i.runnable;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.object.PlayerSR;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GlassColorRunnable implements Runnable {

    private final Material[] colors = new Material[] {
        Material.WHITE_STAINED_GLASS,
        Material.ORANGE_STAINED_GLASS,
        Material.MAGENTA_STAINED_GLASS,
        Material.LIGHT_BLUE_STAINED_GLASS,
        Material.YELLOW_STAINED_GLASS,
        Material.LIME_STAINED_GLASS,
        Material.PINK_STAINED_GLASS,
        Material.GRAY_STAINED_GLASS,
        Material.LIGHT_GRAY_STAINED_GLASS,
        Material.CYAN_STAINED_GLASS,
        Material.PURPLE_STAINED_GLASS,
        Material.BLUE_STAINED_GLASS,
        Material.BROWN_STAINED_GLASS,
        Material.GREEN_STAINED_GLASS,
        Material.RED_STAINED_GLASS,
        Material.BLACK_STAINED_GLASS,
        Material.BARRIER
    };

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            PlayerSR psr = MainSR.getPlayer(player.getUniqueId());
            Location loc = player.getWorld().getHighestBlockAt(player.getLocation()).getLocation();

            if(psr.getTeam() == null)
                continue;

            // if the block type is contained in the colors array
            for(Material mat : colors) {
                if(mat.equals(loc.getBlock().getType())) {
                    loc.getBlock().setType(psr.getTeam().getGlassColor());
                }
            }
        }
    }
}
