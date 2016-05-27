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
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * @author justinthekoolkid
 */
public class KitsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        File kits = new File("plugins/ZeroKits/publickits");
        FileConfiguration config = null;
        if (!(sender instanceof Player)) {
            sender.sendMessage("[ZeroKits] This is only an in-game command");
        } else {
            if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.kit")) {
                if (args.length == 0) {
                    StringBuilder sb = new StringBuilder();
                    for (File files : kits.listFiles()) {
                            if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.kit." + files.getName().replace(".yml", ""))) {
                                if (CooldownManager.isInCooldown(player.getUniqueId(),  files.getName().replace(".yml", ""))) {
                                    String cooldown = ChatColor.STRIKETHROUGH + files.getName().replace(".yml", "") + ChatColor.RESET;
                                    sb.append(cooldown + ", ");
                                } else {
                                    sb.append(files.getName().replace(".yml", "") + ", ");
                                }
                            }
                    }
                    String kitlist = sb.toString();
                    Pattern pattern = Pattern.compile(", $");
                    Matcher matcher = pattern.matcher(kitlist);
                    kitlist = matcher.replaceAll("");
                    player.sendMessage(ChatManager.prefix() + ChatColor.GOLD + " Kits: " + ChatColor.WHITE + kitlist);
                } else if (args.length == 1) {
                    if (ConfigManager.getPublicKitFile(args[0].toLowerCase()).exists()) {
                        if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.kit." + args[0].toLowerCase())) {
                            if (CooldownManager.isInCooldown(player.getUniqueId(), args[0].toLowerCase())) {
                                player.sendMessage(ChatManager.format("still-cooldown").replace("%e", String.valueOf(CooldownManager.getTimeLeft(player.getUniqueId(), args[0].toLowerCase()))));
                            } else {
                                if (ConfigManager.getMainConfig().getBoolean("Clear Inventory before Kit") == true) {
                                    player.getInventory().clear();
                                }
                                KitManager.recieveKit(args[0].toLowerCase(), "public", player);
                                if (!PermissionsManager.ifPlayerHasPermission(player, "zerokits.bypasscooldown")) {
                                    CooldownManager c = new CooldownManager(player.getUniqueId(), args[0].toLowerCase(), ConfigManager.getPublicKitConfig(args[0].toLowerCase()).getInt("delay"));
                                    c.start();
                                }
                                player.sendMessage(ChatManager.format("kit-recieved").replace("%e", args[0].toLowerCase()));
                            }
                        } else {
                            player.sendMessage(ChatManager.format("no-permission"));
                        }
                    } else {
                        player.sendMessage(ChatManager.format("kit-doesnt-exist").replace("%e", args[0].toLowerCase()));
                    }
                } else if (args.length == 2) {
                    @SuppressWarnings("deprecation")
                    Player target = Bukkit.getPlayer(args[1]);
                    if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.kitothers" + args[0].toLowerCase())) {
                        if (ConfigManager.getPublicKitFile(args[0].toLowerCase()).exists()) {
                            if (target.isOnline()) {
                                    if (CooldownManager.isInCooldown(target.getUniqueId(), args[0].toLowerCase())) {
                                        player.sendMessage(ChatManager.format("target-still-cooldown").replace("%e", String.valueOf(CooldownManager.getTimeLeft(target.getUniqueId(), args[0].toLowerCase()))));
                                    } else {
                                        if (ConfigManager.getMainConfig().getBoolean("Clear Inventory before Kit") == true) {
                                            player.getInventory().clear();
                                        }
                                        KitManager.recieveKit(args[0].toLowerCase(), "public", target);
                                        if (!PermissionsManager.ifPlayerHasPermission(player, "zerokits.bypasscooldown")) {
                                            CooldownManager c = new CooldownManager(target.getUniqueId(), args[0].toLowerCase(), ConfigManager.getPublicKitConfig(args[0].toLowerCase()).getInt("delay"));
                                            c.start();
                                        }
                                        target.sendMessage(ChatManager.format("kit-recieved-player").replace("%e", args[0].toLowerCase()).replace("%player", player.getName()));
                                        player.sendMessage(ChatManager.format("sent-kit-to-player").replace("%e", args[0].toLowerCase()).replace("%player", target.getName()));
                                    }
                            } else {
                                player.sendMessage(ChatManager.format("player-not-online"));
                            }
                        } else {
                            player.sendMessage(ChatManager.format("kit-doesnt-exist").replace("%e", args[0].toLowerCase()));
                        }
                    } else {
                        player.sendMessage(ChatManager.format("no-permission-kit"));
                    }
                } else {
                    player.sendMessage(ChatManager.format("usage").replace("%e", "/kits <name> [player]"));
                }
            } else {
                player.sendMessage(ChatManager.format("no-permission"));
            }
        }
        return false;
    }
}