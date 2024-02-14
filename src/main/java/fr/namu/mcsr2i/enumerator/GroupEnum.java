package fr.namu.mcsr2i.enumerator;

public enum GroupEnum {
    PLAYER("Joueur", "§7"),
    SPECTATOR("Spectateur", "§7"),
    ;

    private String name;
    private String prefix;

    GroupEnum(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }
}
