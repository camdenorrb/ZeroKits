/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.ZeroDown.ZeroKits.Commands;

import ml.ZeroDown.ZeroKits.Managers.ChatManager;
import ml.ZeroDown.ZeroKits.Managers.ConfigManager;
import ml.ZeroDown.ZeroKits.Managers.PermissionsManager;
import ml.ZeroDown.ZeroKits.ZeroKits;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author justinthekoolkid
 */
public class ReloadCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!(sender instanceof Player)) {
            ZeroKits.plugin.reloadConfig();
            ConfigManager.reloadLanguageConfig();
            sender.sendMessage("[ZeroKits] " + ChatManager.message("reloaded-config"));
        } else {
            if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.reload")) {
                ZeroKits.plugin.reloadConfig();
                ConfigManager.reloadLanguageConfig();
                player.sendMessage(ChatManager.format("reloaded-config"));
            } else {
                player.sendMessage(ChatManager.format("no-permission"));
            }
        }
        return false;
    }
    
}
