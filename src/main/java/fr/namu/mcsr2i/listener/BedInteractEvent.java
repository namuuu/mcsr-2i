package fr.namu.mcsr2i.listener;

import fr.namu.mcsr2i.object.GameData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BedInteractEvent implements Listener {

    @EventHandler
    public void onBedInteract(PlayerInteractEvent event) {
        if(event.getClickedBlock() == null)
            return;
        if(!event.getClickedBlock().getType().toString().contains("BED"))
            return;

        GameData.getInstance().setLastBedUser(event.getPlayer());
    }
}
