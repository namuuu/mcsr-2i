package fr.namu.mcsr2i.listener;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.ScenarioEnum;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakingEvent implements Listener {

    private static final BlockFace[] blockFaces = {BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
    private static final Material[] logTypes = {Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG};

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(!event.isDropItems())
            return;

        switch (event.getBlock().getType()) {
            case GOLD_ORE:
                if(ScenarioEnum.CUT_CLEAN.isEnabled()) {
                    event.setDropItems(false);
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, 1));
                }
                break;
            case IRON_ORE:
                if(ScenarioEnum.CUT_CLEAN.isEnabled()) {
                    event.setDropItems(false);
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, 1));
                }
                break;
            case OAK_LOG:
            case SPRUCE_LOG:
            case BIRCH_LOG:
            case JUNGLE_LOG:
            case ACACIA_LOG:
            case DARK_OAK_LOG:
                System.out.println("Wood");
                if(ScenarioEnum.TIMBER.isEnabled()) {
                    Timber(event.getBlock());
                }
                break;
        }
    }

    private void Timber(Block block) {
        for(BlockFace face : blockFaces) {
            for(Material logType : logTypes) {
                if(block.getRelative(face).getType() == logType) {
                    System.out.println("Timber!");
                    block.getRelative(face).breakNaturally();
                    block.getWorld().playSound(block.getLocation(), Sound.BLOCK_WOOD_BREAK, 0.8F, 1.0F);
                    // Timber in 0.1s
                    Bukkit.getScheduler().runTaskLater(MainSR.getInstance(), () -> Timber(block.getRelative(face)), 2L);
                }
            }
        }
    }
}
