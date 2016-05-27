/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.ZeroDown.ZeroKits.Managers;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import me.ZeroDown.ZeroKits.ZeroKits;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author justinthekoolkid
 */
public class ConfigManager {

    private static Plugin plugin = ZeroKits.getPlugin();

    public static FileConfiguration getPublicKitConfig(String s) {
        File file = new File("plugins/ZeroKits/publickits", s + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config;
    }

    public static FileConfiguration getMainConfig() {
        return ZeroKits.plugin.getConfig();
    }

    public static void createPublicKitConfig(String s) {
        File file = new File("plugins/ZeroKits/publickits", s + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            config.save(file);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, ChatManager.format("error-saving-kit"), ex);
        }
    }

    public static void reloadPublicKitConfig(String s) {
        File file = new File("plugins/ZeroKits/publickits", s + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            config.load(file);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, ChatManager.format("error-reloading-kit"), ex);
        } catch (InvalidConfigurationException ex) {
            plugin.getLogger().log(Level.SEVERE, ChatManager.format("invalid-configuration"), ex);
        }
    }

    public static FileConfiguration getPrivateKitConfig(String s, Player p) {
        File file = new File("plugins/ZeroKits/privatekits/" + p.getUniqueId() + "/", s + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config;
    }

    public static void createPrivateKitConfig(String s, Player p) {
        File file = new File("plugins/ZeroKits/privatekits/" + p.getUniqueId() + "/", s + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            config.save(file);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, ChatManager.format("error-saving-private-kit"), ex);
        }
    }

    public static void reloadPrivateKitConfig(String s, Player p) {
        File file = new File("plugins/ZeroKits/privatekits/" + p.getUniqueId() + "/", s + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            config.load(file);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, ChatManager.format("error-reloading-config"), ex);
        } catch (InvalidConfigurationException ex) {
            plugin.getLogger().log(Level.SEVERE, ChatManager.format("invalid-configuration"), ex);
        }
    }

    public static File getLanguageFile() {
        return ZeroKits.langfile;
    }

    public static FileConfiguration getLanguageConfig() {
        return ZeroKits.language;
    }

    public static void reloadLanguageConfig() {
        try {
            getLanguageConfig().load(getLanguageFile());
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, ChatManager.format("error-reloading-config"), ex);
        } catch (InvalidConfigurationException ex) {
            plugin.getLogger().log(Level.SEVERE, ChatManager.format("invalid-configuration"), ex);
        }
    }

    public static File getPublicKitFile(String s) {
        File file = new File("plugins/ZeroKits/publickits", s + ".yml");
        return file;
    }

    public static File getPrivateKitFile(String s, Player p) {
        File file = new File("plugins/ZeroKits/privatekits/" + p.getUniqueId() + "/", s + ".yml");
        return file;
    }
}
