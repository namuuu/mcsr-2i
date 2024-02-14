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
        int slot = 10;

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
            if((slot+1) % 9  == 0)
                slot += 2;
        }
    }

    @Override
    public void click(Player player, Material mat, String itemName) {
        for(ScenarioEnum scenario : ScenarioEnum.values()) {
            if(scenario.getMat().equals(mat)) {
                scenario.toggle();
                open(player);
                return;
            }
        }

        if(mat.equals(Material.FEATHER))
            HostMainMenu.getInstance().open(player);
    }
}
