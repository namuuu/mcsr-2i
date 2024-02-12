package fr.namu.mcsr2i.enumerator;

public enum StringEnum {
    MENU_TEAM_SELECTION("§7Sélection de l'équipe"),
    MENU_ITEM_QUIT("§cFermer"),
    ITEM_TEAM_SELECTION("§eChoisir une équipe"),
    ;

    private String value;

    StringEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
