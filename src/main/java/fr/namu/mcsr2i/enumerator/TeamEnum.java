package fr.namu.mcsr2i.enumerator;

import fr.namu.mcsr2i.object.PlayerSR;
import org.bukkit.DyeColor;
import org.bukkit.Material;

import java.util.ArrayList;

public enum TeamEnum {

    RED("rouge", "§cÉquipe Rouge", "§7[§cR§7] §c", true, 9, Material.RED_BANNER),
    BLUE("bleu", "§9Équipe Bleue", "§7[§9B§7] §9", true, 9, Material.BLUE_BANNER),
    GREEN("vert", "§aÉquipe Verte", "§7[§aV§7] §a", true, 9, Material.GREEN_BANNER),
    YELLOW("jaune", "§eÉquipe Jaune", "§7[§eJ§7] §e", true, 9, Material.YELLOW_BANNER),
    PURPLE("violet", "§5Équipe Violette", "§7[§5V§7] §5", true, 9, Material.PURPLE_BANNER),
    ;

    private String id;
    private String name;
    private String prefix;
    private boolean isEnabled;
    private int size;
    private Material color;

    TeamEnum(String id, String name, String prefix, boolean isEnabled, int size, Material color) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.isEnabled = isEnabled;
        this.size = size;
        this.color = color;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public int getSize() {
        return this.size;
    }

    public Material getColor() {
        return this.color;
    }


    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
