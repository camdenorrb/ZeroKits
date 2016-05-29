/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.ZeroDown.ZeroKits.Commands;

import ml.ZeroDown.ZeroKits.Managers.ChatManager;
import ml.ZeroDown.ZeroKits.Managers.ConfigManager;
import ml.ZeroDown.ZeroKits.Managers.KitManager;
import ml.ZeroDown.ZeroKits.Managers.PermissionsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

/**
 *
 * @author justinthekoolkid
 */
public class CreateKitCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            Player player = (Player) sender;
            if (!(sender instanceof Player)) {
                sender.sendMessage("[ZeroKits] This is only an in-game command");
            } else {
                if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.createkit")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatManager.format("usage").replace("%e", "/createkit <name> [delay]"));
                    } else if (args.length == 1) {
                        if (!ConfigManager.getPublicKitFile(args[0].toLowerCase()).exists()) {
                            KitManager.editingkit.put(player.getUniqueId(), args[0].toLowerCase());
                            KitManager.createKit(args[0].toLowerCase(), "public", player);
                            ConfigManager.getPublicKitConfig(args[0].toLowerCase()).set("delay", 0);
                            try {
                                ConfigManager.getPublicKitConfig(args[0].toLowerCase()).save(ConfigManager.getPublicKitFile(args[0]));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            player.sendMessage(ChatManager.format("kit-exists").replace("%e", args[0].toLowerCase()));
                        }
                    } else if (args.length == 2) {
                        if (!ConfigManager.getPublicKitFile(args[0].toLowerCase()).exists()) {
                            int delay = Integer.valueOf(args[1]);
                            KitManager.editingkit.put(player.getUniqueId(), args[0].toLowerCase());
                            KitManager.createKit(args[0].toLowerCase(), "public", player);
                            ConfigManager.getPublicKitConfig(args[0].toLowerCase()).set("delay", delay);
                            try {
                                ConfigManager.getPublicKitConfig(args[0].toLowerCase()).save(ConfigManager.getPublicKitFile(args[0]));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            player.sendMessage(ChatManager.format("kit-exists").replace("%e", args[0].toLowerCase()));
                        }
                    }
                } else {
                    player.sendMessage(ChatManager.format("no-permission"));
                }
            }
        return false;

    }
}
