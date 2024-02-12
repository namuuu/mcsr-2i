package fr.namu.mcsr2i.manager;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GameStateEnum;
import fr.namu.mcsr2i.object.GameData;
import fr.namu.mcsr2i.object.PlayerSR;
import fr.namu.mcsr2i.runnable.GameRunnable;
import fr.namu.mcsr2i.util.ItemUtil;
import fr.namu.mcsr2i.util.TeamUtil;
import fr.namu.mcsr2i.util.WorldUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StartManager {

    public static void startGame() {
        System.out.println("Game is starting!");

        // Fill spectators
        TeamUtil.fillSpectator();

        // Teleport players
        WorldUtil.teleportPlayers();

        for(PlayerSR psr : MainSR.getPlayers().values()) {
            Player player = psr.getPlayer();
            ItemUtil.gameEquip(player);
        }

        GameData game = GameData.getInstance();
        game.setGameState(GameStateEnum.INGAME);

        // Start runnable
        Bukkit.getScheduler().runTaskTimer(MainSR.getInstance(), new GameRunnable(), 0L, 20L);
    }
}
