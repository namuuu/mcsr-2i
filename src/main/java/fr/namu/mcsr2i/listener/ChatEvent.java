package fr.namu.mcsr2i.listener;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GroupEnum;
import fr.namu.mcsr2i.object.PlayerSR;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        PlayerSR psr = MainSR.getPlayer(event.getPlayer().getUniqueId());

        if(psr.getGroup().equals(GroupEnum.SPECTATOR)) {
            event.setFormat("§7[Spec] " + event.getPlayer().getName() + " » §f" + event.getMessage());
            return;
        }

        if(psr.getTeam() == null) {
            event.setFormat("§f" + event.getPlayer().getName() + " §7» §f" + event.getMessage());
        } else {
            event.setFormat(psr.getTeam().getPrefix() + event.getPlayer().getName() + " §7» §f" + event.getMessage());
        }
    }
}
