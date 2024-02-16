package fr.namu.mcsr2i.menu;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.StringEnum;
import fr.namu.mcsr2i.manager.StartManager;
import fr.namu.mcsr2i.object.PlayerSR;
import fr.namu.mcsr2i.util.ItemBuilder;
import fr.namu.mcsr2i.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class HostMainMenu extends MenuSR {

    private static HostMainMenu instance;

    public HostMainMenu() {
        this.title = StringEnum.MENU_TITLE_HOST.getValue();
    }

    public static HostMainMenu getInstance() {
        if(instance == null)
            instance = new HostMainMenu();
        return instance;
    }

    @Override
    public void replaceInventory(Player player, Inventory inventory) {

        inventory.setItem(21, new ItemBuilder(Material.EMERALD_BLOCK, 1).setName(StringEnum.MENU_ITEM_START.getValue()).toItemStack());
        inventory.setItem(23, new ItemBuilder(Material.COMPARATOR, 1).setName(StringEnum.MENU_ITEM_CONFIG.getValue()).toItemStack());
        inventory.setItem(31, new ItemBuilder(Material.WHITE_BANNER, 1).setName(StringEnum.MENU_ITEM_TEAMCONFIG.getValue()).toItemStack());
        inventory.setItem(49, new ItemBuilder(Material.BARRIER, 1).setName(StringEnum.MENU_ITEM_QUIT.getValue()).toItemStack());
        inventory.setItem(53, new ItemBuilder(Material.SKELETON_SKULL, 1).setName(StringEnum.MENU_ITEM_QUIT_HOST.getValue()).toItemStack());


        int[] slotWhite = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 52};
        for (int slotGlass : slotWhite)
            inventory.setItem(slotGlass, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1).setName(" ").toItemStack());
        int[] slotGray = { 10, 16, 37, 43, 47, 48, 50, 51};
        for (int slotGlass : slotGray)
            inventory.setItem(slotGlass, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1).setName(" ").toItemStack());
    }

    @Override
    public void click(Player player, Material mat, String itemName) {
        if(itemName.equals(StringEnum.MENU_ITEM_START.getValue())) {
            // Start the game
            StartManager.startGame();
            player.closeInventory();
        }
        if(itemName.equals(StringEnum.MENU_ITEM_CONFIG.getValue())) {
            // Open the config menu
            ScenarioMenu.getInstance().open(player);
        }
        if(itemName.equals(StringEnum.MENU_ITEM_QUIT.getValue())) {
            // Close the inventory
            player.closeInventory();
        }
        if(itemName.equals(StringEnum.MENU_ITEM_QUIT_HOST.getValue())) {
            // Quit the host status
            PlayerSR psr = MainSR.getPlayer(player.getUniqueId());
            psr.setHost(false);
            ItemUtil.lobbyEquip(player);
            player.closeInventory();
        }

        if(itemName.equals(StringEnum.MENU_ITEM_TEAMCONFIG.getValue())) {
            // Open the team config menu
            TeamEditionMenu.getInstance().open(player);
        }
    }
}
