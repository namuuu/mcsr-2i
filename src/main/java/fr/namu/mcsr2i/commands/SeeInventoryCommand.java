package fr.namu.mcsr2i.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SeeInventoryCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length == 0) {
            commandSender.sendMessage("§cVeuillez entrer un nom de joueur.");
            return true;
        }

        String playerName = strings[0];
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getName().equalsIgnoreCase(playerName)) {

                Inventory inv = Bukkit.createInventory(null, 6*9, "§7Inventaire de " + playerName);
                for(int i = 0; i < 36; i++) {
                    inv.setItem(i, player.getInventory().getItem(i));
                }
                ((Player) commandSender).openInventory(inv);
                return true;
            }
        }

        commandSender.sendMessage("§cLe joueur " + playerName + " n'est pas connecté.");
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
