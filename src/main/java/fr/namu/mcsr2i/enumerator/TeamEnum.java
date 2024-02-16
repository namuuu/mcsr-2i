package fr.namu.mcsr2i.enumerator;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum TeamEnum {
    RED("rouge", "§cÉquipe Rouge", "§7[§cR§7] §c", true, 9, Material.RED_BANNER, ChatColor.RED, Material.RED_STAINED_GLASS),
    BLUE("bleu", "§9Équipe Bleue", "§7[§9B§7] §9", true, 9, Material.BLUE_BANNER, ChatColor.BLUE, Material.BLUE_STAINED_GLASS),
    GREEN("vert", "§aÉquipe Verte", "§7[§aV§7] §a", true, 9, Material.GREEN_BANNER, ChatColor.GREEN, Material.GREEN_STAINED_GLASS),
    YELLOW("jaune", "§eÉquipe Jaune", "§7[§eJ§7] §e", true, 9, Material.YELLOW_BANNER, ChatColor.YELLOW, Material.YELLOW_STAINED_GLASS),
    PURPLE("violet", "§5Équipe Violette", "§7[§5V§7] §5", true, 9, Material.PURPLE_BANNER, ChatColor.DARK_PURPLE, Material.PURPLE_STAINED_GLASS),
    ORANGE("orange", "§6Équipe Orange", "§7[§6O§7] §6", true, 9, Material.ORANGE_BANNER, ChatColor.GOLD, Material.ORANGE_STAINED_GLASS),
    PINK("rose", "§dÉquipe Rose", "§7[§dP§7] §d", true, 9, Material.PINK_BANNER, ChatColor.LIGHT_PURPLE, Material.PINK_STAINED_GLASS),
    WHITE("blanc", "§fÉquipe Blanche", "§7[§fB§7] §f", true, 9, Material.WHITE_BANNER, ChatColor.WHITE, Material.WHITE_STAINED_GLASS),
    AQUA("aqua", "§bÉquipe Aqua", "§7[§bA§7] §b", true, 9, Material.LIGHT_BLUE_BANNER, ChatColor.AQUA, Material.LIGHT_BLUE_STAINED_GLASS),
    GRAY("gris", "§8Équipe Grise", "§7[§8G§7] §8", true, 9, Material.GRAY_BANNER, ChatColor.GRAY, Material.GRAY_STAINED_GLASS),
    ;

    private final String id;
    private final String name;
    private final String prefix;
    private boolean isEnabled;
    private int size;
    private final Material color;
    private final ChatColor chatColor;
    private final Material glassColor;

    TeamEnum(String id, String name, String prefix, boolean isEnabled, int size, Material color, ChatColor chatColor, Material glassColor) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.isEnabled = isEnabled;
        this.size = size;
        this.color = color;
        this.chatColor = chatColor;
        this.glassColor = glassColor;
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

    public ChatColor getChatColor() {
        return this.chatColor;
    }

    public Material getGlassColor() {
        return this.glassColor;
    }


    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void toggle() {
        this.isEnabled = !this.isEnabled;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
