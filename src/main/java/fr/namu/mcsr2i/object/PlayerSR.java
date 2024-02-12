package fr.namu.mcsr2i.object;

import fr.namu.mcsr2i.enumerator.GroupEnum;
import fr.namu.mcsr2i.enumerator.TeamEnum;
import org.bukkit.entity.Player;

public class PlayerSR {

    private GroupEnum group = GroupEnum.NOTEAM;
    private TeamSR team;

    private Player player;
    private String name;

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

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }
}
