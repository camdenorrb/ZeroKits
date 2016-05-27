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
import me.ZeroDown.ZeroKits.UUIDFetcher;
import me.ZeroDown.ZeroKits.ZeroKits;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author justinthekoolkid
 */
public class ReloadPersonalKitCommand implements CommandExecutor {

    public Plugin plugin = ZeroKits.getPlugin();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!(sender instanceof Player)) {
            if (args.length < 2) {
                sender.sendMessage("[ZeroKits] " + ChatManager.format("usage").replace("%e", "/preloadkit <name> <player>"));
            } else if (args.length == 2) {
                File file = null;
                try {
                    file = new File("plugins/ZeroKits/privatekits/" + UUIDFetcher.getUUIDOf(args[1]) + "/", args[0].toLowerCase() + ".yml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (file.exists()) {
                    FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                    try {
                        config.load(file);
                    } catch (IOException e) {
                        plugin.getLogger().log(Level.SEVERE, ChatManager.format("error-saving-personal-kit").replace("%e", args[0].toLowerCase()), e);
                    } catch (InvalidConfigurationException e) {
                        plugin.getLogger().log(Level.SEVERE, ChatManager.format("invalid-configuration"), e);
                    }
                    sender.sendMessage("[ZeroKits] " + ChatManager.message("personal-kit-reloaded").replace("%e", args[0].toLowerCase()));
                }
            } else {

            }
        } else {
            if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.personalkit")) {
                if (args.length == 0) {
                    player.sendMessage(ChatManager.format("usage").replace("%e", "/preloadkit <name> [player]"));
                } else if (args.length == 1) {
                    String name = args[0].toLowerCase();
                    if (ConfigManager.getPrivateKitFile(name, player).exists()) {
                        ConfigManager.reloadPrivateKitConfig(name, player);
                        player.sendMessage(ChatManager.format("personal-kit-reloaded").replace("%e", name));
                    } else {
                        player.sendMessage(ChatManager.format("personal-kit-doesnt-exist").replace("%e", name));
                    }
                } else if (args.length == 2) {
                    String name = args[0].toLowerCase();
                    File file = null;
                    try {
                        file = new File("plugins/ZeroKits/privatekits/" + UUIDFetcher.getUUIDOf(args[1]) + "/", name + ".yml");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (file.exists()) {
                        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                        try {
                            config.load(file);
                        } catch (IOException e) {
                            plugin.getLogger().log(Level.SEVERE, ChatManager.format("error-saving-personal-kit").replace("%e", args[0].toLowerCase()), e);
                            player.sendMessage(ChatManager.format("error-saving-personal-kit").replace("%e", name));
                        } catch (InvalidConfigurationException e) {
                            plugin.getLogger().log(Level.SEVERE, ChatManager.format("invalid-configuration"), e);
                            player.sendMessage(ChatManager.format("invalid-configuration").replace("%e", name));
                        }
                        player.sendMessage(ChatManager.format("personal-kit-reloaded").replace("%e", name));
                    } else {
                        player.sendMessage(ChatManager.format("personal-kit-doesnt-exist").replace("%e", name));
                    }
                } else {
                    player.sendMessage(ChatManager.format("usage").replace("%e", "/preloadkit <name> [player]"));
                }
            } else {
                player.sendMessage(ChatManager.format("no-permission"));
            }
        }
        return false;
    }
}
