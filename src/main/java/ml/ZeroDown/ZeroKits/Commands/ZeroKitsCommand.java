/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.ZeroDown.ZeroKits.Commands;

import ml.ZeroDown.ZeroKits.Managers.ChatManager;
import ml.ZeroDown.ZeroKits.Managers.PermissionsManager;
import ml.ZeroDown.ZeroKits.ZeroKits;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author justinthekoolkid
 */
public class ZeroKitsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!(sender instanceof Player)) {
            sender.sendMessage("--- ZeroKits Console Commands ---");
            sender.sendMessage("/reload");
            sender.sendMessage("/reloadkit");
            sender.sendMessage("/preloadkit <name> <player>");
        } else {
            if (args.length == 0) {
                helpMenu(player);
            } else {
                helpMenu(player);
            }
        }
        return false;
        
    }
    
    public void helpMenu(Player player) {
        player.sendMessage(ChatManager.prefix() + " " + ChatColor.BLUE + ChatColor.BOLD + "Zero" + ChatColor.DARK_GREEN + ChatColor.BOLD + "Kits " + ChatColor.YELLOW + "Version " + ZeroKits.getPlugin().getDescription().getVersion());
        player.sendMessage(ChatColor.BLUE + " --- " + ChatColor.DARK_GREEN + ChatColor.BOLD + "Public Kits Commands" + ChatColor.BLUE + " --- ");
        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.createkit")) {
            player.sendMessage(ChatColor.YELLOW + " /createkit <name> - " + ChatManager.message("help-createkit"));
        }
        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.deletekit")) {
            player.sendMessage(ChatColor.YELLOW + " /deletekit <name> - " + ChatManager.message("help-deletekit"));
        }
        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.kit")) {
            player.sendMessage(ChatColor.YELLOW + " /kits <name> [player] - " + ChatManager.message("help-kits"));
        }
        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.editkit")) {
            player.sendMessage(ChatColor.YELLOW + " /editkit <name> - " + ChatManager.message("help-editkit"));
        }
        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.previewkit")) {
            player.sendMessage(ChatColor.YELLOW + " /previewkit <name> - " + ChatManager.message("help-previewkit"));
        }
        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.reloadkit")) {
            player.sendMessage(ChatColor.YELLOW + " /reloadkit <name> - " + ChatManager.message("help-reloadkit"));
        }
        player.sendMessage(ChatColor.BLUE + " --- " + ChatColor.DARK_GREEN + ChatColor.BOLD + "Personal Kits Commands" + ChatColor.BLUE + " --- ");
        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.pcreatekit")) {
            player.sendMessage(ChatColor.YELLOW + " /pcreatekit <name> - " + ChatManager.message("help-pcreatekit"));
        }
        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.pdeletekit")) {
            player.sendMessage(ChatColor.YELLOW + " /pdeletekit <name> - " + ChatManager.message("help-pdeletekit"));
        }
        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.ppreviewkit")) {
            player.sendMessage(ChatColor.YELLOW + " /ppreviewkit <name> - " + ChatManager.message("help-ppreviewkit"));
        }
        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.pkits")) {
            player.sendMessage(ChatColor.YELLOW + " /pkits <name> - " + ChatManager.message("help-pkits"));
        }
        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.peditkit")) {
            player.sendMessage(ChatColor.YELLOW + " /peditkit <name> - " + ChatManager.message("help-peditkit"));
        }
        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.preloadkit")) {
            player.sendMessage(ChatColor.YELLOW + " /preloadkit <name> [player] - " + ChatManager.message("help-preloadkit"));
        }
        player.sendMessage(ChatColor.BLUE + " --- " + ChatColor.DARK_GREEN + ChatColor.BOLD + "Other Commands" + ChatColor.BLUE + " --- ");
        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.reload")) {
            player.sendMessage(ChatColor.YELLOW + " /reloadzerokits - " + ChatManager.message("help-reload"));
        }
    }
    
}
