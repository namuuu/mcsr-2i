package fr.namu.mcsr2i.manager;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GameStateEnum;
import fr.namu.mcsr2i.enumerator.ScenarioEnum;
import fr.namu.mcsr2i.object.GameData;
import fr.namu.mcsr2i.object.PlayerSR;
import fr.namu.mcsr2i.runnable.GameRunnable;
import fr.namu.mcsr2i.scoreboard.ScoreboardSR;
import fr.namu.mcsr2i.util.ItemUtil;
import fr.namu.mcsr2i.util.TeamUtil;
import fr.namu.mcsr2i.util.WorldUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class StartManager {
    static int countdown;

    public static void startGame() {
        countdown = 3;

        // Loop 5 times every 20 ticks
        Bukkit.getScheduler().runTaskTimer(MainSR.getInstance(), () -> {
            GameData game = GameData.getInstance();

            for(PlayerSR psr : MainSR.getPlayers().values()) {
                Player player = psr.getPlayer();
                player.sendTitle("§e" + countdown, "§7La partie commence dans", 0, 20, 0);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1.0F, (float) countdown /2);
            }

            if(countdown == 0) {
                Bukkit.broadcastMessage("§aLa partie commence !");
                Bukkit.getScheduler().cancelTasks(MainSR.getInstance());

                // UHC Scenario
                World world = Bukkit.getWorld("world");
                assert world != null;
                if(ScenarioEnum.UHC.isEnabled()) {
                    world.setGameRule(GameRule.NATURAL_REGENERATION, false);
                } else {
                    world.setGameRule(GameRule.NATURAL_REGENERATION, true);
                }

                // Fill spectators
                TeamUtil.fillSpectator();

                // Teleport players
                WorldUtil.teleportPlayers();

                for(PlayerSR psr : MainSR.getPlayers().values()) {
                    Player player = psr.getPlayer();
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);
                    ItemUtil.gameEquip(player);
                }

                game.setGameState(GameStateEnum.INGAME);

                Bukkit.getScheduler().runTaskLater(MainSR.getInstance(), () -> {
                    game.setGracePeriod(false);
                    Bukkit.broadcastMessage("§aLa période de grâce est terminée !");
                    for(PlayerSR psr : MainSR.getPlayers().values()) {
                        Player player = psr.getPlayer();
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 0.2F, 1F);
                    }
                }, 600L);
                Bukkit.getScheduler().runTaskTimer(MainSR.getInstance(), new GameRunnable(), 0L, 20L);
            }
            countdown--;
        }, 0L, 20L);
    }
}
