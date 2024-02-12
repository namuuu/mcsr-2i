package fr.namu.mcsr2i.listener;

import fr.namu.mcsr2i.MainSR;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinLeaveEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");

        MainSR main = MainSR.getInstance();
        main.joinManager.joinPlayer(event.getPlayer());
    }
}
