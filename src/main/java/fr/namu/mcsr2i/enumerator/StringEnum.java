package fr.namu.mcsr2i.enumerator;

public enum StringEnum {
    MENU_TITLE_TEAM_SELECTION("§7Sélection de l'équipe"),
    MENU_TITLE_HOST("§eMenu de l'hôte"),
    MENU_TITLE_SCENARIO("§7Configuration des scénarios"),
    MENU_TITLE_TEAMCONFIG("§7Configuration des équipes"),
    MENU_ITEM_QUIT("§cFermer"),
    MENU_ITEM_START("§aDémarrer la partie"),
    MENU_ITEM_CONFIG("§eConfiguration des scénarios"),
    MENU_ITEM_TEAMCONFIG("§eConfiguration des équipes"),
    MENU_ITEM_QUIT_HOST("§cQuitter le statut d'hôte"),
    MENU_ITEM_BACK("§cRetour"),
    MENU_ITEM_JOINSPECTATOR("§eRejoindre les spectateurs"),
    ITEM_TEAM_SELECTION("§eChoisir une équipe"),
    ITEM_HOST_MENU("§eMenu de l'hôte"),
    ;

    private String value;

    StringEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
