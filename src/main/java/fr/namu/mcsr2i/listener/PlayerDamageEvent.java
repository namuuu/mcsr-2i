package fr.namu.mcsr2i.listener;

import fr.namu.mcsr2i.enumerator.GameStateEnum;
import fr.namu.mcsr2i.object.GameData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageEvent implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player)) {
            return;
        }

        GameData gameData = GameData.getInstance();
        if(gameData.isState(GameStateEnum.PREGAME) || gameData.isState(GameStateEnum.POSTGAME)) {
            event.setCancelled(true);
            return;
        }

        switch (event.getCause()) {
            case ENTITY_ATTACK:
            case ENTITY_SWEEP_ATTACK:
                // Cancel damage if the source is from a player
                if(event.getDamageSource().getCausingEntity() instanceof Player) {
                    event.setCancelled(true);
                }
        }
    }
}
