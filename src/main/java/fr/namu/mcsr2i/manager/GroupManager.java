package fr.namu.mcsr2i.manager;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GroupEnum;
import fr.namu.mcsr2i.object.PlayerSR;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;

public class GroupManager {

    public static void changeGroup(Player player, GroupEnum group) {
        PlayerSR psr = MainSR.getPlayer(player.getUniqueId());

        if(psr == null)
            return;

        psr.setGroup(group);

        if(group != GroupEnum.PLAYER) {
            // Remove player from previous team
            Objects.requireNonNull(Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard().getTeam(group.getName())).addEntry(player.getName());
            player.setPlayerListName(group.getPrefix() + player.getName());
        }
    }

    public static void removePlayer(Player player) {
        PlayerSR psr = MainSR.getPlayer(player.getUniqueId());

        if(psr == null)
            return;

        if(psr.getGroup() != GroupEnum.PLAYER) {
            // Remove player from previous team
            Objects.requireNonNull(Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard().getTeam(psr.getGroup().getName())).removeEntry(player.getName());
            player.setPlayerListName(player.getName());
        }

        if(psr.isHost()) {
            psr.setHost(false);
        }

        psr.setGroup(GroupEnum.PLAYER);
    }
}
