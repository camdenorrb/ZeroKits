package ml.ZeroDown.ZeroKits.Events;

import ml.ZeroDown.ZeroKits.Managers.ChatManager;
import ml.ZeroDown.ZeroKits.Managers.ConfigManager;
import ml.ZeroDown.ZeroKits.Managers.KitManager;
import ml.ZeroDown.ZeroKits.ZeroKits;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by Admin on 5/17/16.
 */
public class InventoryClose implements Listener {

    public Plugin plugin = ZeroKits.getPlugin();

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (KitManager.editingkit.containsKey(e.getPlayer().getUniqueId())) {
            if (e.getInventory().getTitle().startsWith("Creating Kit: ")) {
                FileConfiguration config1 = ConfigManager.getPublicKitConfig(e.getInventory().getTitle().replace("Creating Kit: ", ""));
                for (int i = 0; i < 36; i++) {
                    if (e.getInventory().getItem(i) != null) {
                        config1.set("Inventory Contents." + i, e.getInventory().getItem(i));
                    }
                }
                for (int i = 36; i < 40; i++) {
                    if (e.getInventory().getItem(i) != null) {
                        if (i == 36){
                            config1.set("Armor Contents." + 0, e.getInventory().getItem(i));
                        } else if (i == 37) {
                            config1.set("Armor Contents." + 1, e.getInventory().getItem(i));
                        } else if (i == 38) {
                            config1.set("Armor Contents." + 2, e.getInventory().getItem(i));
                        } else if (i == 39) {
                            config1.set("Armor Contents." + 3, e.getInventory().getItem(i));
                        }
                    }
                }
                try {
                    e.getPlayer().sendMessage(ChatManager.format("kit-created").replace("%e", KitManager.editingkit.get(e.getPlayer().getUniqueId())));
                    config1.save(ConfigManager.getPublicKitFile(e.getInventory().getTitle().replace("Creating Kit: ", "")));
                    KitManager.editingkit.remove(e.getPlayer().getUniqueId());
                } catch (IOException e1) {
                    plugin.getLogger().log(Level.SEVERE, ChatManager.format("error-saving-personal-kit").replace("%e", config1.getName()), e1);
                    e.getPlayer().sendMessage(ChatManager.format("error-saving-personal-kit").replace("%e", config1.getName()));
                }
            } else if (e.getInventory().getTitle().startsWith("Creating Personal Kit: ")) {
                FileConfiguration config1 = ConfigManager.getPrivateKitConfig(e.getInventory().getTitle().replace("Creating Personal Kit: ", ""), (Player) e.getPlayer());
                for (int i = 0; i < 36; i++) {
                    if (e.getInventory().getItem(i) != null) {
                        config1.set("Inventory Contents." + i, e.getInventory().getItem(i));
                    }
                }
                for (int i = 36; i < 40; i++) {
                    if (e.getInventory().getItem(i) != null) {
                        if (i == 36){
                            config1.set("Armor Contents." + 0, e.getInventory().getItem(i));
                        } else if (i == 37) {
                            config1.set("Armor Contents." + 1, e.getInventory().getItem(i));
                        } else if (i == 38) {
                            config1.set("Armor Contents." + 2, e.getInventory().getItem(i));
                        } else if (i == 39) {
                            config1.set("Armor Contents." + 3, e.getInventory().getItem(i));
                        }
                    }
                }
                try {
                    e.getPlayer().sendMessage(ChatManager.format("personal-kit-created").replace("%e", KitManager.editingkit.get(e.getPlayer().getUniqueId())));
                    config1.save(ConfigManager.getPrivateKitFile(e.getInventory().getTitle().replace("Creating Personal Kit: ", ""), (Player) e.getPlayer()));
                    KitManager.editingkit.remove(e.getPlayer().getUniqueId());
                } catch (IOException e1) {
                    plugin.getLogger().log(Level.SEVERE, ChatManager.format("error-saving-personal-kit").replace("%e", config1.getName()), e1);
                    e.getPlayer().sendMessage(ChatManager.format("error-saving-personal-kit").replace("%e", config1.getName()));
                }
            }
            if (e.getInventory().getTitle().startsWith("Editing Kit: ")) {
                FileConfiguration config1 = ConfigManager.getPublicKitConfig(e.getInventory().getTitle().replace("Editing Kit: ", ""));
                config1.set("Inventory Contents", null);
                config1.set("Armor Contents", null);
                for (int i = 0; i < 36; i++) {
                    if (e.getInventory().getItem(i) != null) {
                        config1.set("Inventory Contents." + i, e.getInventory().getItem(i));
                    }
                }
                for (int i = 36; i < 40; i++) {
                    if (e.getInventory().getItem(i) != null) {
                        if (i == 36){
                            config1.set("Armor Contents." + 0, e.getInventory().getItem(i));
                        } else if (i == 37) {
                            config1.set("Armor Contents." + 1, e.getInventory().getItem(i));
                        } else if (i == 38) {
                            config1.set("Armor Contents." + 2, e.getInventory().getItem(i));
                        } else if (i == 39) {
                            config1.set("Armor Contents." + 3, e.getInventory().getItem(i));
                        }
                    }
                }
                try {
                    e.getPlayer().sendMessage(ChatManager.format("kit-created").replace("%e", KitManager.editingkit.get(e.getPlayer().getUniqueId())));
                    config1.save(ConfigManager.getPublicKitFile(e.getInventory().getTitle().replace("Editing Kit: ", "")));
                    KitManager.editingkit.remove(e.getPlayer().getUniqueId());
                } catch (IOException e1) {
                    plugin.getLogger().log(Level.SEVERE, ChatManager.format("error-saving-personal-kit").replace("%e", config1.getName()), e1);
                    e.getPlayer().sendMessage(ChatManager.format("error-saving-personal-kit").replace("%e", config1.getName()));
                }
            } else if (e.getInventory().getTitle().startsWith("Editing Personal Kit: ")) {
                FileConfiguration config1 = ConfigManager.getPrivateKitConfig(e.getInventory().getTitle().replace("Editing Personal Kit: ", ""), (Player) e.getPlayer());
                config1.set("Inventory Contents", null);
                config1.set("Armor Contents", null);
                for (int i = 0; i < 36; i++) {
                    if (e.getInventory().getItem(i) != null) {
                        config1.set("Inventory Contents." + i, e.getInventory().getItem(i));
                    }
                }
                for (int i = 36; i < 40; i++) {
                    if (e.getInventory().getItem(i) != null) {
                        if (i == 36){
                            config1.set("Armor Contents." + 0, e.getInventory().getItem(i));
                        } else if (i == 37) {
                            config1.set("Armor Contents." + 1, e.getInventory().getItem(i));
                        } else if (i == 38) {
                            config1.set("Armor Contents." + 2, e.getInventory().getItem(i));
                        } else if (i == 39) {
                            config1.set("Armor Contents." + 3, e.getInventory().getItem(i));
                        }
                    }
                }
                try {
                    e.getPlayer().sendMessage(ChatManager.format("personal-kit-created").replace("%e", KitManager.editingkit.get(e.getPlayer().getUniqueId())));
                    config1.save(ConfigManager.getPrivateKitFile(e.getInventory().getTitle().replace("Editing Personal Kit: ", ""), (Player) e.getPlayer()));
                    KitManager.editingkit.remove(e.getPlayer().getUniqueId());
                } catch (IOException e1) {
                    plugin.getLogger().log(Level.SEVERE, ChatManager.format("error-saving-personal-kit").replace("%e", config1.getName()), e1);
                    e.getPlayer().sendMessage(ChatManager.format("error-saving-personal-kit").replace("%e", config1.getName()));
                }
            }
        }
    }
}
