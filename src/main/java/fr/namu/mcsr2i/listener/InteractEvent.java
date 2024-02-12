package fr.namu.mcsr2i.listener;

import fr.namu.mcsr2i.enumerator.StringEnum;
import fr.namu.mcsr2i.menu.TeamSelectionMenu;
import fr.namu.mcsr2i.object.GameData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class InteractEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        GameData gameData = GameData.getInstance();

        ItemStack item = event.getItem();
        Player player = event.getPlayer();

        if(item == null || !item.hasItemMeta() || !Objects.requireNonNull(item.getItemMeta()).hasDisplayName())
            return;

        String itemName = item.getItemMeta().getDisplayName();
        if(itemName.equals(StringEnum.ITEM_TEAM_SELECTION.getValue())) {
            TeamSelectionMenu.getInstance().open(player);
        }
    }
}
