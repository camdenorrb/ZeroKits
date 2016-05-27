/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ZeroDown.ZeroKits.Commands;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.ZeroDown.ZeroKits.Managers.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author justinthekoolkid
 */
public class PersonalKitsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        File kits = new File("plugins/ZeroKits/privatekits/" + player.getUniqueId());
        if (!(sender instanceof Player)) {
            sender.sendMessage("[ZeroKits] This is only an in-game command");
        } else {
            if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.personalkit")) {
                if (args.length == 0) {
                    StringBuilder sb = new StringBuilder();
                    for (File files : kits.listFiles()) {
                        sb.append(files.getName().replace(".yml", "") + ", ");
                    }
                    String kitlist = sb.toString();
                    Pattern pattern = Pattern.compile(", $");
                    Matcher matcher = pattern.matcher(kitlist);
                    kitlist = matcher.replaceAll("");
                    player.sendMessage(ChatManager.prefix() + ChatColor.GOLD + " Personal Kits: " + ChatColor.WHITE + kitlist);
                } else if (args.length == 1) {
                    if (ConfigManager.getPrivateKitFile(args[0].toLowerCase(), player).exists()) {
                        if (ConfigManager.getMainConfig().getBoolean("Clear Inventory before Personal Kit") == true) {
                            player.getInventory().clear();
                        }
                        KitManager.recieveKit(args[0].toLowerCase(), "private", player);
                        player.sendMessage(ChatManager.format("personal-kit-recieved").replace("%e", args[0].toLowerCase()));
                    } else {
                        player.sendMessage(ChatManager.format("personal-kit-doesnt-exist").replace("%e", args[0].toLowerCase()));
                    }
                } else {
                    player.sendMessage(ChatManager.format("usage").replace("%e", "/pkits <name>"));
                }
            } else {
                player.sendMessage(ChatManager.format("no-permission"));
            }
        }
        return false;
    }

}
