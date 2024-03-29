package fr.namu.mcsr2i.object;

import fr.namu.mcsr2i.enumerator.GroupEnum;
import fr.namu.mcsr2i.enumerator.TeamEnum;
import org.bukkit.entity.Player;

public class PlayerSR {

    private GroupEnum group = GroupEnum.PLAYER;
    private boolean hostPrivilege = false;
    private TeamSR team = null;

    private final Player player;
    private final String name;

    // Invicibility used for the respawn scenario
    private boolean invicibility = false;

    public PlayerSR(Player player) {
        this.player = player;
        this.name = player.getName();
    }

    public GroupEnum getGroup() {
        return group;
    }
    public void setGroup(GroupEnum group) {
        this.group = group;
    }

    public TeamSR getTeam() {
        return team;
    }
    public void setTeam(TeamSR team) {
        this.team = team;
    }

    public boolean isHost() {
        return hostPrivilege;
    }
    public void setHost(boolean state) {
        this.hostPrivilege = state;
    }

    public boolean isInvicible() {
        return invicibility;
    }
    public void setInvicible(boolean state) {
        this.invicibility = state;
    }

    public Player getPlayer() {
        return player;
    }
    public String getName() {
        return name;
    }
}
