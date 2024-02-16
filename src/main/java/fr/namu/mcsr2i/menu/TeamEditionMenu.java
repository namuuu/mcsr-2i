package fr.namu.mcsr2i.menu;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.StringEnum;
import fr.namu.mcsr2i.object.PlayerSR;
import fr.namu.mcsr2i.object.TeamSR;
import fr.namu.mcsr2i.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class TeamEditionMenu extends MenuSR {

    private static TeamEditionMenu instance;

    public TeamEditionMenu() {
        this.title = StringEnum.MENU_TITLE_TEAMCONFIG.getValue();
    }

    public static TeamEditionMenu getInstance() {
        if(instance == null)
            instance = new TeamEditionMenu();
        return instance;
    }

    @Override
    public void replaceInventory(Player player, Inventory inventory) {
        int slot = 20;

        for (TeamSR teamSR : MainSR.getTeams()) {
            ItemBuilder item = new ItemBuilder(teamSR.getBanner(), 1);
            item.setName(teamSR.getName());
            List<String> lore = new ArrayList<>();
            for(PlayerSR teamPlayerSR : teamSR.getPlayers()) {
                lore.add("§7 - " + teamPlayerSR.getPlayer().getName());
            }
            lore.add(" ");
            if(teamSR.isEnabled()) {
                lore.add("§aActivé");
                lore.add("§eCliquer pour désactiver");
            } else {
                lore.add("§cDésactivé");
                lore.add("§eCliquer pour activer");
            }
            item.setLore(lore);
            inventory.setItem(slot, item.toItemStack());
            slot += 1;
            if((slot+2) % 9  == 0)
                slot += 4;


        }

        int[] slotWhite = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 52};
        for (int slotGlass : slotWhite)
            inventory.setItem(slotGlass, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1).setName(" ").toItemStack());
        int[] slotGray = { 10, 16, 37, 43, 47, 48, 50, 51, 52, 53};
        for (int slotGlass : slotGray)
            inventory.setItem(slotGlass, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1).setName(" ").toItemStack());
    }

    @Override
    public void click(Player player, Material mat, String itemName) {
        for(TeamSR team : MainSR.getTeams()) {
            if(team.getName().equals(itemName)) {
                team.toggle();
                ArrayList<PlayerSR> players = new ArrayList<>(team.getPlayers());
                for(PlayerSR playerSR : players) {
                    team.removePlayer(playerSR);
                    playerSR.getPlayer().sendMessage("§eVotre équipe a été désactivée !");
                    player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 0.5F, 1.0F);
                }
                open(player);
                return;
            }
        }
    }
}
