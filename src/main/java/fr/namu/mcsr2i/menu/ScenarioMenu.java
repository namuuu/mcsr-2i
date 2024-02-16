package fr.namu.mcsr2i.menu;

import fr.namu.mcsr2i.enumerator.ScenarioEnum;
import fr.namu.mcsr2i.enumerator.StringEnum;
import fr.namu.mcsr2i.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ScenarioMenu extends MenuSR {

    private static ScenarioMenu instance;

    public ScenarioMenu() {
        this.title = StringEnum.MENU_TITLE_SCENARIO.getValue();
    }

    public static ScenarioMenu getInstance() {
        if(instance == null)
            instance = new ScenarioMenu();
        return instance;
    }

    @Override
    public void replaceInventory(Player player, Inventory inventory) {
        int slot = 11;

        inventory.setItem(49, new ItemBuilder(Material.FEATHER, 1).setName(StringEnum.MENU_ITEM_BACK.getValue()).toItemStack());

        for(ScenarioEnum scenario : ScenarioEnum.values()) {
            ItemBuilder item = new ItemBuilder(scenario.getMat(), 1);
            item.setName(scenario.getName());
            if(scenario.isEnabled())
                item.setLore("§aActivé");
            else
                item.setLore("§cDésactivé");
            inventory.setItem(slot, item.toItemStack());
            slot += 1;
            if((slot+2) % 9  == 0)
                slot += 4;
        }

        int[] slotWhite = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 52};
        for (int slotGlass : slotWhite)
            inventory.setItem(slotGlass, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1).setName(" ").toItemStack());
        int[] slotGray = { 10, 16, 37, 43, 47, 48, 50, 51, 52};
        for (int slotGlass : slotGray)
            inventory.setItem(slotGlass, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1).setName(" ").toItemStack());
    }

    @Override
    public void click(Player player, Material mat, String itemName) {
        for(ScenarioEnum scenario : ScenarioEnum.values()) {
            if(scenario.getName().equals(itemName)) {
                scenario.toggle();
                open(player);
                return;
            }
        }

        if(mat.equals(Material.FEATHER)) {
            HostMainMenu.getInstance().open(player);
        }
    }
}
