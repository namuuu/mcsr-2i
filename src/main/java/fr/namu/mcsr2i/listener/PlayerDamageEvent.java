package fr.namu.mcsr2i.listener;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GameStateEnum;
import fr.namu.mcsr2i.enumerator.ScenarioEnum;
import fr.namu.mcsr2i.object.GameData;
import fr.namu.mcsr2i.object.PlayerSR;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

        Player player = (Player) event.getEntity();
        PlayerSR psr = MainSR.getPlayer(player.getUniqueId());


        GameData gameData = GameData.getInstance();
        if(gameData.isState(GameStateEnum.PREGAME)
                || gameData.isState(GameStateEnum.POSTGAME)
                || gameData.isGracePeriod()
                || psr.isInvicible()) {
            event.setCancelled(true);
            return;
        }

        switch (event.getCause()) {
            case FIRE:
            case FIRE_TICK:
            case LAVA:
                if(ScenarioEnum.FIRELESS.isEnabled()) {
                    event.setCancelled(true);
                    player.setFireTicks(0);
                }
                break;
            case BLOCK_EXPLOSION:
            case ENTITY_EXPLOSION:
                if(ScenarioEnum.EXPLOSIONLESS.isEnabled()) {
                    event.setCancelled(true);
                }
                break;
            case FALL:
                if(ScenarioEnum.NO_FALL.isEnabled()) {
                    event.setCancelled(true);
                }
                break;
            case ENTITY_ATTACK:
            case ENTITY_SWEEP_ATTACK:
                // Cancel damage if the source is from a player
                if(event.getDamageSource().getCausingEntity() instanceof Player) {
                    event.setCancelled(true);
                } else {
                    if(ScenarioEnum.NO_PVE.isEnabled()) {
                        event.setCancelled(true);
                    }
                }
                break;
        }

        // Handle death
        if(event.getDamage() >= player.getHealth() && !event.isCancelled()) {
            Bukkit.broadcastMessage("§c" + player.getName() + " §7est mort.");
            player.setGameMode(GameMode.SPECTATOR);
            long respawnTimer = ScenarioEnum.RESPAWN_TIMER.isEnabled() ? 20L*10 : 5L;

            Bukkit.getScheduler().runTaskLater(MainSR.getInstance(), () -> {
                if(!gameData.isState(GameStateEnum.INGAME)) {
                    return;
                }
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage("§cVous bénéficiez de 5 secondes d'invincibilité.");
                player.teleport(psr.getTeam().getSpawn());
                player.setHealth(20.0D);
                player.setFoodLevel(20);
                player.setSaturation(20.0F);
                psr.setInvicible(true);

                Bukkit.getScheduler().runTaskLater(MainSR.getInstance(), () -> {
                    psr.setInvicible(false);
                }, 20L*5);
            }, respawnTimer);
        }
    }
}
