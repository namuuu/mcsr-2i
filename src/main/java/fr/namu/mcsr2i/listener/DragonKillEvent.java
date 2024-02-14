package fr.namu.mcsr2i.listener;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GameStateEnum;
import fr.namu.mcsr2i.object.GameData;
import fr.namu.mcsr2i.object.PlayerSR;
import fr.namu.mcsr2i.object.TeamSR;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class DragonKillEvent implements Listener {

        @EventHandler
        public void onDragonKill(EntityDeathEvent event) {
            if(!(event.getEntity() instanceof Chicken))
                return;

            GameData.getInstance().setGameState(GameStateEnum.POSTGAME);
            GameData.getInstance().setGracePeriod(true);

            Location deathLocation = event.getEntity().getLocation();

            Player killer = event.getEntity().getKiller();
            World world = event.getEntity().getWorld();


            // Find the killer of the Dragon / Player who killed the Dragon
            if(event.getEntity().getLastDamageCause() instanceof EntityDamageByBlockEvent && killer == null) {
                killer = GameData.getInstance().getLastBedUser();
            }

            assert killer != null;
            // Obtain winning team
            PlayerSR psr = MainSR.getPlayer(killer.getUniqueId());
            TeamSR team = psr.getTeam();

            // Broadcast the winning team
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage("§k!il!i §r§a§l" + team.getName() + " §6a gagné la partie ! §r§k!il!i ");
            Bukkit.broadcastMessage(" ");

            // Spawn Firework on death location
            Firework fw = (Firework) world.spawnEntity(deathLocation, EntityType.FIREWORK);
            FireworkMeta fwm = fw.getFireworkMeta();

            fwm.setPower(2);
            fwm.addEffect(FireworkEffect.builder().withColor(Color.PURPLE).flicker(true).build());

            fw.setFireworkMeta(fwm);
            fw.detonate();

            for(Player player : Bukkit.getOnlinePlayers()) {
                if(player.getLocation().distance(deathLocation) > 75) {
                    player.teleport(deathLocation);
                }
                player.setGameMode(GameMode.ADVENTURE);
                player.setAllowFlight(true);
                player.setFlying(true);
                player.playSound(player, Sound.UI_TOAST_CHALLENGE_COMPLETE, 0.4F, 1.0F);
            }
        }
}
