package fr.namu.mcsr2i.listener;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GameStateEnum;
import fr.namu.mcsr2i.enumerator.StringEnum;
import fr.namu.mcsr2i.menu.HostMainMenu;
import fr.namu.mcsr2i.menu.ScenarioMenu;
import fr.namu.mcsr2i.menu.TeamSelectionMenu;
import fr.namu.mcsr2i.object.GameData;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ClickEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        MainSR main = MainSR.getInstance();
        GameData gameData = GameData.getInstance();

        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();

        if(player.getGameMode() == GameMode.CREATIVE)
            return;

        if(gameData.isState(GameStateEnum.PREGAME))
            event.setCancelled(true);

        if(current == null || !current.hasItemMeta() || !Objects.requireNonNull(current.getItemMeta()).hasDisplayName())
            return;

        String inventoryName = event.getView().getTitle();
        Material mat = current.getType();
        String itemName = current.getItemMeta().getDisplayName();
        ClickType click = event.getClick();

        if(inventoryName.equals(StringEnum.MENU_TITLE_TEAM_SELECTION.getValue())) {
            event.setCancelled(true);
            TeamSelectionMenu menu = new TeamSelectionMenu();
            menu.click(player, mat, itemName);
        }
        if(inventoryName.equals(StringEnum.MENU_TITLE_HOST.getValue())) {
            event.setCancelled(true);
            HostMainMenu menu = new HostMainMenu();
            menu.click(player, mat, itemName);
        }
        if(inventoryName.equals(StringEnum.MENU_TITLE_SCENARIO.getValue())) {
            event.setCancelled(true);
            ScenarioMenu menu = new ScenarioMenu();
            menu.click(player, mat, itemName);
        }
    }
}
