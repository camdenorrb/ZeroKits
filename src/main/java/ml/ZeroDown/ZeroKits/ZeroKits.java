/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.ZeroDown.ZeroKits;

import java.io.File;
import java.util.logging.Level;

import ml.ZeroDown.ZeroKits.Commands.*;
import ml.ZeroDown.ZeroKits.Events.InventoryClick;
import ml.ZeroDown.ZeroKits.Events.InventoryClose;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author justinthekoolkid
 */
public class ZeroKits extends JavaPlugin {
    
    public FileConfiguration config = getConfig();
    public static Plugin plugin;
    public static Economy eco;
    public static Permission perms;
    public static File langfile;
    public static FileConfiguration language;

    public static String permissiontype;

    @Override
    @SuppressWarnings({"deprecation", "deprecation"})
    public void onEnable() {
        plugin = this;
        File dir = new File("plugins/ZeroKits/publickits");
        File dir2 = new File("plugins/ZeroKits/privatekits");
        dir.mkdir();
        dir2.mkdir();
        createMainConfiguration();
        if (config.getBoolean("Vault Integration") == true) {
            setupEconomy();
            setupPermissions();
            permissiontype = "Vault";
        } else if (config.getBoolean("Vault Integration") == false) {
            plugin.getLogger().log(Level.INFO, "Vault: Disabled");
            setupAltPermissions();
        }
        plugin.saveResource("lang-en.yml", true);
        langfile = new File("plugins/ZeroKits", "lang-" + config.getString("Language") + ".yml");
        if (langfile.exists()) {
            language = YamlConfiguration.loadConfiguration(langfile);
            plugin.getLogger().log(Level.INFO, "Language: lang-" + config.getString("Language"));
        } else if (!langfile.exists()) {
            langfile = new File("plugins/ZeroKits", "lang-en.yml");
            language = YamlConfiguration.loadConfiguration(langfile);
            plugin.getLogger().log(Level.WARNING, "Language File doesn't exist! Switching to default English!");
            plugin.getLogger().log(Level.INFO, "Language: lang-" + config.getString("Language"));
        }
        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public void createMainConfiguration() {
        plugin.saveDefaultConfig();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            plugin.getLogger().log(Level.WARNING, "You do not have Vault installed in your server. I will be disabling economy features.");
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        eco = rsp.getProvider();
        return eco != null;

    }

    private boolean setupPermissions() {
        if (setupEconomy() != false) {
            RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
            perms = rsp.getProvider();
            plugin.getLogger().log(Level.INFO, "Permissions: " + rsp.getPlugin().getName());
            return perms != null;
        }
        return false;
    }

    private void setupAltPermissions() {
        plugin.getLogger().log(Level.WARNING, "Vault isn't enabled, I will be enabling the Alt Permissions API!");
        if (Bukkit.getPluginManager().getPlugin("GroupManager").isEnabled()) {
            plugin.getLogger().log(Level.INFO, "Permissions: GroupManager");
            permissiontype = "GroupManager";
        } else if (Bukkit.getPluginManager().getPlugin("PermissionsEx").isEnabled()) {
            plugin.getLogger().log(Level.INFO, "Permissions: PemrissionsEx");
            permissiontype = "PermissionsEx";
        } else {
            plugin.getLogger().log(Level.WARNING, "You have no permissions plugin! All permissions are removed! Use Vault to implement your permissions plugin!");
            permissiontype = "null";
        }
    }

    private void registerCommands() {
        plugin.getLogger().log(Level.INFO, "Registering Commands");
        getCommand("createkit").setExecutor(new CreateKitCommand());
        getCommand("deletekit").setExecutor(new DeleteKitCommand());
        getCommand("kits").setExecutor(new KitsCommand());
        getCommand("editkit").setExecutor(new EditKitCommand());
        getCommand("previewkit").setExecutor(new PreviewKitCommand());
        getCommand("reloadkit").setExecutor(new ReloadKitCommand());
        getCommand("zerokits").setExecutor(new ZeroKitsCommand());
        getCommand("reloadzerokits").setExecutor(new ReloadCommand());
        if (config.getBoolean("Enable Personal Kits") == true) {
            getCommand("pcreatekit").setExecutor(new CreatePersonalKitCommand());
            getCommand("pdeletekit").setExecutor(new DeletePersonalKitCommand());
            getCommand("ppreviewkit").setExecutor(new PreviewPersonalKitCommand());
            getCommand("preloadkit").setExecutor(new ReloadPersonalKitCommand());
            getCommand("peditkit").setExecutor(new EditPersonalKitCommand());
            getCommand("pkits").setExecutor(new PersonalKitsCommand());
        }
    }

    private void registerEvents() {
        plugin.getLogger().log(Level.INFO, "Registering Events");
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClose(), plugin);
    }

    public static Plugin getPlugin() {
        return plugin;
    }

}
