/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.ZeroDown.ZeroKits.Managers;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author justinthekoolkid
 */
public class ChatManager {

    public static String prefix() {
        FileConfiguration config = ConfigManager.getLanguageConfig();
        return ChatColor.translateAlternateColorCodes('&', config.getString("prefix"));
    }

    public static String message(String s) {
        FileConfiguration config = ConfigManager.getLanguageConfig();
        return ChatColor.translateAlternateColorCodes('&', config.getString(s));
    }

    public static String format(String s) {
        FileConfiguration config = ConfigManager.getLanguageConfig();
        return ChatColor.translateAlternateColorCodes('&', config.getString("prefix") + " " + config.getString(s));
    }
}
