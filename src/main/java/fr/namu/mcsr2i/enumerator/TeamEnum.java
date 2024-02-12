package fr.namu.mcsr2i.enumerator;

import org.bukkit.DyeColor;

import java.util.ArrayList;

public enum TeamEnum {

    RED("rouge", "§cÉquipe Rouge", "§7[§cR§7] §c", true, 9, DyeColor.RED, new ArrayList<>()),
    BLUE("bleu", "§9Équipe Bleue", "§7[§9B§7] §9", true, 9, DyeColor.BLUE, new ArrayList<>()),
    ;

    private String id;
    private String name;
    private String prefix;
    private boolean isEnabled;
    private int size;
    private DyeColor color;
    private ArrayList<String> players;

    TeamEnum(String id, String name, String prefix, boolean isEnabled, int size, DyeColor color, ArrayList<String> players) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.isEnabled = isEnabled;
        this.size = size;
        this.color = color;
        this.players = players;
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

    public DyeColor getColor() {
        return this.color;
    }

    public ArrayList<String> getPlayers() {
        return this.players;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addPlayer(String player) {
        this.players.add(player);
    }

    public void removePlayer(String player) {
        this.players.remove(player);
    }

}
