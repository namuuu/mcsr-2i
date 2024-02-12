package fr.namu.mcsr2i.util;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GroupEnum;
import fr.namu.mcsr2i.manager.GroupManager;
import fr.namu.mcsr2i.object.PlayerSR;
import org.bukkit.entity.Player;

public class TeamUtil {

    /**
     * Place every non-team player as spectator
     */
    public static void fillSpectator() {
        System.out.println("Filling spectators");

        for(PlayerSR psr : MainSR.getPlayers().values()) {
            Player player = psr.getPlayer();

            System.out.println("Player: " + psr.getName() + " | Group: " + psr.getGroup() + " | Team: " + psr.getTeam());
            if(psr.getTeam() == null && psr.getGroup() == GroupEnum.PLAYER) {
                GroupManager.changeGroup(player, GroupEnum.SPECTATOR);
            }
        }
    }
}
