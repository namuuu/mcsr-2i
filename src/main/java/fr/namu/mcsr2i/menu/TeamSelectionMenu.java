package fr.namu.mcsr2i.menu;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.StringEnum;
import fr.namu.mcsr2i.object.PlayerSR;
import fr.namu.mcsr2i.object.TeamSR;
import fr.namu.mcsr2i.util.ItemBuilder;
import fr.namu.mcsr2i.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TeamSelectionMenu extends MenuSR {

    private static TeamSelectionMenu instance;

    public TeamSelectionMenu() {
        this.title = StringEnum.MENU_TITLE_TEAM_SELECTION.getValue();
    }

    public static TeamSelectionMenu getInstance() {
        if(instance == null)
            instance = new TeamSelectionMenu();
        return instance;
    }

    @Override
    public void replaceInventory(Player player, Inventory inv) {
        int slot = 20;
        PlayerSR psr = MainSR.getPlayer(player.getUniqueId());
        for(TeamSR team : MainSR.getTeams()) {
            if(!team.isEnabled())
                continue;
            inv.setItem(slot, ItemBuilder.teamBannerEdit(team, psr));
            slot += 1;
            if((slot+2)%9 == 0)
                slot += 4;
        }

        inv.setItem(49, new ItemBuilder(Material.BARRIER, 1).setName(StringEnum.MENU_ITEM_QUIT.getValue()).toItemStack());

        int[] slotGray = {3, 4, 5, 11, 15, 19, 25, 27, 35, 36, 44, 46, 47, 48, 50, 51, 52};
        for (int slotGlass : slotGray)
            inv.setItem(slotGlass, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1).setName(" ").toItemStack());

        int[] slotWhite = {0, 1, 2, 6, 7, 8, 9, 10, 16, 17, 18, 26, 45, 53};
        for (int slotGlass : slotWhite)
            inv.setItem(slotGlass, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1).setName(" ").toItemStack());
    }

    @Override
    public void click(Player player, Material mat, String itemName) {
        if(itemName.equals(StringEnum.MENU_ITEM_QUIT.getValue())) {
            player.closeInventory();
        }

        PlayerSR psr = MainSR.getPlayer(player.getUniqueId());

        for(TeamSR team : MainSR.getTeams()) {
            if(team.getName().equals(itemName)) {
                if(!team.isEnabled()) {
                    player.sendMessage("§cCette équipe n'est pas activée !");
                    player.closeInventory();
                    return;
                }
                if(team.getPlayers().size() >= team.getSize()) {
                    player.sendMessage("§cCette équipe est pleine !");
                    player.closeInventory();
                    return;
                }

                if(team == psr.getTeam()) {
                    team.removePlayer(psr);
                } else {
                    team.addPlayer(psr);
                }

                ItemUtil.lobbyEquip(player);
                player.closeInventory();
                return;
            }
        }
    }
}
