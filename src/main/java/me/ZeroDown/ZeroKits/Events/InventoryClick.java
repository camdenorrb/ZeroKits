package me.ZeroDown.ZeroKits.Events;

import me.ZeroDown.ZeroKits.Managers.ChatManager;
import me.ZeroDown.ZeroKits.Managers.ConfigManager;
import me.ZeroDown.ZeroKits.Managers.KitManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;

/**
 * Created by Admin on 5/17/16.
 */
public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().startsWith("Creating Kit:") || e.getInventory().getTitle().startsWith("Creating Personal Kit:") || e.getInventory().getTitle().startsWith("Editing Kit:")  || e.getInventory().getTitle().startsWith("Editing Personal Kit:")) {
            if (KitManager.editingkit.containsKey(e.getWhoClicked().getUniqueId())) {
                if (e.getClickedInventory().equals(e.getView().getTopInventory())) {
                    if (e.getSlot() == 52) {
                        e.setCancelled(true);
                        if (e.getInventory().getTitle().startsWith("Creating Kit:")) {
                            File file = new File("plugins/ZeroKits/publickits/" + KitManager.editingkit.get(e.getWhoClicked().getUniqueId()) + ".yml");
                            file.delete();
                            for (int i = 0; i < 40; i++) {
                                if (e.getInventory().getItem(i) != null) {
                                    e.getWhoClicked().getInventory().addItem(e.getInventory().getItem(i));
                                }
                            }
                        } else if (e.getInventory().getTitle().startsWith("Creating Personal Kit:")) {
                            File file = new File("plugins/ZeroKits/privatekits/" + KitManager.editingkit.get(e.getWhoClicked().getUniqueId()) + ".yml");
                            file.delete();
                            for (int i = 0; i < 40; i++) {
                                if (e.getInventory().getItem(i) != null) {
                                    e.getWhoClicked().getInventory().addItem(e.getInventory().getItem(i));
                                }
                            }
                        }
                        KitManager.editingkit.remove(e.getWhoClicked().getUniqueId());
                        e.getWhoClicked().closeInventory();
                        e.getWhoClicked().sendMessage(ChatManager.format("kit-canceled"));
                    } else if (e.getSlot() == 53) {
                        e.setCancelled(true);
                        e.getWhoClicked().closeInventory();
                    } else if (e.getCurrentItem().equals(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14)) && e.getSlot() > 39) {
                            e.setCancelled(true);
                    } else if (e.getSlot() < 40) {
                        e.setCancelled(false);
                    }
                } else if (e.getClickedInventory().equals(e.getView().getBottomInventory())) {
                    e.setCancelled(false);
                }
            }
        } else if (e.getInventory().getTitle().startsWith("Previewing Kit:")) {
            e.setCancelled(true);
        }
    }

}
