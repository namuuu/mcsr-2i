package fr.namu.mcsr2i.commands;

import fr.namu.mcsr2i.MainSR;
import fr.namu.mcsr2i.enumerator.GameStateEnum;
import fr.namu.mcsr2i.object.GameData;
import fr.namu.mcsr2i.object.PlayerSR;
import fr.namu.mcsr2i.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public class HostCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length < 1) {
            sender.sendMessage("§cUtilisation: /host <add|remove|get|list|help> [joueur]");
            return true;
        }

        GameData gameData = GameData.getInstance();

        switch (strings[0]) {
            case "add":
                Player player = Bukkit.getPlayer(strings[1]);
                if(player == null) {
                    sender.sendMessage("§cLe joueur n'est pas connecté.");
                    return true;
                }
                PlayerSR psr = MainSR.getPlayer(player.getUniqueId());
                if(psr == null) {
                    sender.sendMessage("§cUne erreur est survenue. (NO_PSR_DATA)");
                    return true;
                }
                if(psr.isHost()) {
                    sender.sendMessage("§cLe joueur est déjà hôte.");
                    return true;
                }
                psr.setHost(true);

                if(gameData.isState(GameStateEnum.PREGAME)) {
                    ItemUtil.lobbyEquip(player);
                }
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                player.sendMessage("§aVous êtes hôte.");
                break;
            case "remove":
                Player player2 = Bukkit.getPlayer(strings[1]);
                if(player2 == null) {
                    sender.sendMessage("§cLe joueur n'est pas connecté.");
                    return true;
                }
                PlayerSR psr2 = MainSR.getPlayer(player2.getUniqueId());
                if(psr2 == null) {
                    sender.sendMessage("§cUne erreur est survenue. (NO_PSR_DATA)");
                    return true;
                }
                if(!psr2.isHost()) {
                    sender.sendMessage("§cLe joueur n'est pas hôte.");
                    return true;
                }
                psr2.setHost(false);
                if(gameData.isState(GameStateEnum.PREGAME)) {
                    ItemUtil.lobbyEquip(player2);
                }
                player2.playSound(player2.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                player2.sendMessage("§cVous n'êtes plus hôte.");
                break;
            case "get":
                Player player3 = (Player) sender;
                PlayerSR psr3 = MainSR.getPlayer(player3.getUniqueId());
                if(psr3 == null) {
                    sender.sendMessage("§cUne erreur est survenue. (NO_PSR_DATA)");
                    return true;
                }
                if(psr3.isHost()) {
                    sender.sendMessage("§aVous êtes hôte.");
                    return true;
                }
                psr3.setHost(true);
                if(gameData.isState(GameStateEnum.PREGAME)) {
                    ItemUtil.lobbyEquip(player3);
                }
                player3.playSound(player3, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                break;
            case "list":
                int count = 0;
                sender.sendMessage("§eListe des hôtes:");
                for(PlayerSR psrList : MainSR.getPlayers().values()) {
                    if(psrList.isHost()) {
                        sender.sendMessage("§e- " + psrList.getName());
                        count++;
                    }
                }
                if(count == 0) {
                    sender.sendMessage("§cAucun hôte enregistré.");
                }
                break;
            case "help":
                sender.sendMessage("§e/host add <joueur> §7- §aAjouter un hôte");
                sender.sendMessage("§e/host remove <joueur> §7- §cRetirer un hôte");
                sender.sendMessage("§e/host get §7- §aVoir si vous êtes hôte");
                sender.sendMessage("§e/host list §7- §aVoir la liste des hôtes");
                break;
            default:
                sender.sendMessage("§cSous-commande inconnue.");
                break;
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String[] subcommands = {"list", "add", "remove", "get", "help"};

        if(strings.length == 1) {
            return List.of(subcommands);
        }

        switch (strings[0]) {
            case "add":
            case "remove":
                return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
            case "get":
            case "list":
                return null;
            default:
                return List.of(subcommands);
        }
    }
}
