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

import java.io.File;

/**
 *
 * @author justinthekoolkid
 */
public class CreatePersonalKitCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            Player player = (Player) sender;
            if (!(sender instanceof Player)) {
                sender.sendMessage("[ZeroKits] This is only an in-game command");
            } else {
                if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.createpersonalkit")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatManager.format("usage").replace("%e", "/pcreatekit <name>"));
                    } else if (args.length == 1) {
                        File file = new File("plugins/ZeroKits/privatekits/" + player.getUniqueId() + "/");
                        if (!ConfigManager.getPrivateKitFile(args[0].toLowerCase(), player).exists()) {
                            if (PermissionsManager.getLimit(player) == 0 || PermissionsManager.getLimit(player) > file.listFiles().length) {
                                KitManager.editingkit.put(player.getUniqueId(), args[0].toLowerCase());
                                KitManager.createKit(args[0].toLowerCase(), "private", player);
                            } else if (PermissionsManager.getLimit(player) == file.listFiles().length) {
                                player.sendMessage(ChatManager.format("limit-kit-error").replace("%e", String.valueOf(PermissionsManager.getLimit(player))));
                            }
                        } else {
                            player.sendMessage(ChatManager.format("personal-kit-exists").replace("%e", args[0].toLowerCase()));
                        }
                    } else if (args.length > 1) {
                        player.sendMessage(ChatManager.format("usage").replace("%e", "/pcreatekit <name>"));
                    }
                } else {
                    player.sendMessage(ChatManager.format("no-permission"));
                }
            }
        return false;
    }
}
