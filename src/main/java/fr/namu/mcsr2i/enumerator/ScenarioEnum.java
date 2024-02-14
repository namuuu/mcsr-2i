package fr.namu.mcsr2i.enumerator;

import org.bukkit.Material;

public enum ScenarioEnum {
    TIMBER("§eTimber", Material.IRON_AXE, false),
    CUT_CLEAN("§eCutClean", Material.IRON_INGOT, false),
    CAT_EYES("§eCatEyes", Material.ENDER_EYE, false),
    HASTEY_BOYS("§eHasteyBoys", Material.DIAMOND_PICKAXE, false),
    UHC("§eUHC", Material.GOLDEN_APPLE, false),
    KEEP_INVENTORY("§eKeepInventory", Material.CHEST, false),
    EXPLOSIONLESS("§eExplosionless", Material.TNT, false),
    NO_FALL("§eNoFall", Material.FEATHER, false),
    NO_PVE("§eNoPvE", Material.ROTTEN_FLESH, false),
    FIRELESS("§eFireless", Material.FLINT_AND_STEEL, false),
    RESPAWN_TIMER("§eRespawnTimer", Material.CLOCK, false),
    EASY_STRONGHOLD("§eEasyStronghold", Material.STONE_BRICKS, false),
    EASY_END_PORTAL("§eEasyEndPortal", Material.END_PORTAL_FRAME, false),
    ;

    private String name;
    private Material mat;
    private boolean enabled;

    ScenarioEnum(String name, Material mat, boolean enabled) {
        this.name = name;
        this.mat = mat;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public Material getMat() {
        return mat;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }
}
