package ml.ZeroDown.ZeroKits.Events;

import ml.ZeroDown.ZeroKits.Managers.ChatManager;
import ml.ZeroDown.ZeroKits.Managers.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.io.File;

/**
 * Created by Admin on 5/31/16.
 */
public class SignChange implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        Player player = event.getPlayer();
        if (event.getLine(0).equals("zerosell")) {
            if (player.hasPermission("zerokits.createsign")) {
                String type = event.getLine(2).toLowerCase();
                if (event.getLine(1) == "" || event.getLine(2) == "") {
                    event.getBlock().breakNaturally();
                    player.sendMessage(ChatManager.format("kit-doesnt-exist"));
                } else {
                    if (type.equalsIgnoreCase("get")) {
                        File kitfile = new File("plugins/ZeroKits/publickits/" + event.getLine(1).toLowerCase() + ".yml");
                        if (!kitfile.exists()) {
                            event.getBlock().breakNaturally();
                            player.sendMessage(ChatManager.format("kit-doesnt-exist").replace("%e", event.getLine(1).toLowerCase()));
                        } else {
                            event.setLine(0, ChatColor.translateAlternateColorCodes('&', ChatManager.message("kit-sign-prefix")));
                            event.setLine(1, ChatColor.translateAlternateColorCodes('&', event.getLine(1).toLowerCase()));
                        }
                    } else if (type.equalsIgnoreCase("preview")) {
                        File kitfile = new File("plugins/ZeroKits/publickits/" + event.getLine(1) + ".yml");
                        if (!kitfile.exists()) {
                            event.getBlock().breakNaturally();
                            player.sendMessage(ChatManager.format("kit-doesnt-exist"));
                        } else {
                            event.setLine(0, ChatColor.translateAlternateColorCodes('&', ChatManager.message("preview-sign-prefix")));
                            event.setLine(1, ChatColor.translateAlternateColorCodes('&', event.getLine(1).toLowerCase()));
                        }
                    }
                }
            } else {
                player.sendMessage(ChatManager.format("no-permission"));
            }
        } else {
            event.getBlock().breakNaturally();
            player.sendMessage(ChatManager.format("no-permission"));
        }
    }

}
