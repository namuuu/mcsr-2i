package fr.namu.mcsr2i.listener;

import fr.namu.mcsr2i.enumerator.GameStateEnum;
import fr.namu.mcsr2i.object.GameData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class DragonKillEvent implements Listener {

        @EventHandler
        public void onDragonKill(EntityDeathEvent event) {
            if(!(event.getEntity() instanceof Chicken))
                return;

            GameData.getInstance().setGameState(GameStateEnum.POSTGAME);
            GameData.getInstance().setGracePeriod(true);

            Location deathLocation = event.getEntity().getLocation();

            Player killer = event.getEntity().getKiller();

            // Find the killer of the Dragon / Player who killed the Dragon
            if(event.getEntity().getLastDamageCause() instanceof EntityDamageByBlockEvent && killer == null) {
                killer = GameData.getInstance().getLastBedUser();
            }

            assert killer != null;
            killer.sendMessage("§aGG! Vous avez tué le Dragon!");

            for(Player player : Bukkit.getOnlinePlayers()) {
                if(player.getLocation().distance(deathLocation) > 75) {
                    player.teleport(deathLocation);
                }
                player.setGameMode(GameMode.ADVENTURE);
                player.setAllowFlight(true);
                player.setFlying(true);
            }
        }
}
