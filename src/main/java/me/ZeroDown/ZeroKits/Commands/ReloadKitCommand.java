/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ZeroDown.ZeroKits.Commands;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import me.ZeroDown.ZeroKits.Managers.ChatManager;
import me.ZeroDown.ZeroKits.Managers.ConfigManager;
import me.ZeroDown.ZeroKits.Managers.PermissionsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

/**
 *
 * @author justinthekoolkid
 */
public class ReloadKitCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!(sender instanceof Player)) {
            if (args[0].equalsIgnoreCase("all")) {
                for (File file : new File("plugins/ZeroKits/publickits").listFiles()) {
                    ConfigManager.reloadPublicKitConfig(file.getName().replace(".yml", ""));
                }
                sender.sendMessage("[ZeroKits] " + ChatManager.message("reloaded-all-kits"));
            } else {
                if (ConfigManager.getPublicKitFile(args[0].toLowerCase()).exists()) {
                    ConfigManager.reloadPublicKitConfig(args[0].toLowerCase());
                    sender.sendMessage("[ZeroKits] " + ChatManager.message("kit-reloaded"));
                }
            }
        } else {
            if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.reloadkit")) {
                if (args.length == 0) {
                    player.sendMessage(ChatManager.format("usage").replace("%e", "/reloadkit <name>"));
                } else if (args.length == 1) {
                    String name = args[0].toLowerCase();
                    if (args[0].equalsIgnoreCase("all")) {
                        for (File file : new File("plugins/ZeroKits/publickits").listFiles()) {
                            ConfigManager.reloadPublicKitConfig(file.getName().replace(".yml", ""));
                        }
                        player.sendMessage(ChatManager.format("reloaded-all-kits"));
                    } else {
                        if (ConfigManager.getPublicKitFile(name).exists()) {
                            ConfigManager.reloadPublicKitConfig(name);
                            player.sendMessage(ChatManager.format("kit-reloaded").replace("%e", args[0].toLowerCase()));
                        } else {
                            player.sendMessage(ChatManager.format("kit-doesnt-exist").replace("%e", args[0].toLowerCase()));
                        }
                    }
                } else if (args.length > 1) {
                    player.sendMessage(ChatManager.format("usage").replace("%e", "/reloadkit <name>"));
                }
            } else {
                player.sendMessage(ChatManager.format("no-permission"));
            }
        }
        return false;
    }
    
}
