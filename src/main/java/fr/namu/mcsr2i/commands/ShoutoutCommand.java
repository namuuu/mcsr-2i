package fr.namu.mcsr2i.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShoutoutCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // Combine every arg
        String message = String.join(" ", strings);
        // Broadcast the message
        Bukkit.broadcastMessage("§6§lSHOUTOUT §8» §f" + message);

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), "entity.experience_orb.pickup", 1.0F, 1.0F);
        }

        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
