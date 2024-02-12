package fr.namu.mcsr2i.manager;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.TeamEnum;
import fr.namu.mcsr2i.object.PlayerSR;
import fr.namu.mcsr2i.object.TeamSR;
import fr.namu.mcsr2i.util.ItemUtil;
import org.bukkit.entity.Player;

public class TeamManager {

    public void joinTeam(Player player, TeamSR team) {
        MainSR main = MainSR.getInstance();
        PlayerSR psr = MainSR.getPlayer(player.getUniqueId());

        if(psr == null)
            return;

        // Remove player from previous team
        if(psr.getTeam() != null) {
            psr.getTeam().removePlayer(psr);
        }

        psr.setTeam(team);
        team.addPlayer(psr);

        // Update player's inventory
        ItemUtil.lobbyEquip(player);
    }
}
