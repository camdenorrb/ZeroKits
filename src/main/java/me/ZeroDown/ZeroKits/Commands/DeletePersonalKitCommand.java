/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ZeroDown.ZeroKits.Commands;

import me.ZeroDown.ZeroKits.Managers.ChatManager;
import me.ZeroDown.ZeroKits.Managers.ConfigManager;
import me.ZeroDown.ZeroKits.Managers.KitManager;
import me.ZeroDown.ZeroKits.Managers.PermissionsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author justinthekoolkid
 */
public class DeletePersonalKitCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            Player player = (Player) sender;
            if (!(sender instanceof Player)) {
                sender.sendMessage("[ZeroKits] This is only an in-game command");
            } else {
                if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.deletepersonalkit")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatManager.format("usage").replace("%e", "/pdeletekit <name>"));
                    } else if (args.length == 1) {
                        if (ConfigManager.getPrivateKitFile(args[0], player).exists()) {
                            KitManager.deleteKit(args[0].toLowerCase(), "private", player);
                            player.sendMessage(ChatManager.format("personal-kit-deleted").replace("%e", args[0].toLowerCase()));
                        } else {
                            player.sendMessage(ChatManager.format("personal-kit-doesnt-exist").replace("%e", args[0].toLowerCase()));
                        }
                    } else if (args.length > 1) {
                        player.sendMessage(ChatManager.format("usage").replace("%e", "/pdeletekit <name>"));
                    }
                } else {
                    player.sendMessage(ChatManager.format("no-permission"));
                }
            }
        return false;
    }
}
