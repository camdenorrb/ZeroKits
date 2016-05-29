/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.ZeroDown.ZeroKits.Managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author justinthekoolkid
 */
public class KitManager {

    public static Map<UUID, String> editingkit = new HashMap();

    public static void createKit(String name, String type, Player player) {
        String name1 = name.toLowerCase();
        if (type.equalsIgnoreCase("public")) {
            ItemStack cancelpane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
            ItemStack savepane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
            ItemMeta cancelmeta = cancelpane.getItemMeta();
            ItemMeta savemeta = savepane.getItemMeta();
                Inventory kitinv = Bukkit.createInventory(null, 54, "Creating Kit: " + name1);
                for (int i = 40; i < 52; i++) {
                    kitinv.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
                }
                cancelmeta.setDisplayName(ChatColor.RED + "CANCEL");
                savemeta.setDisplayName(ChatColor.GREEN + "SAVE");
                cancelpane.setItemMeta(cancelmeta);
                savepane.setItemMeta(savemeta);
                kitinv.setItem(52, cancelpane);
                kitinv.setItem(53, savepane);
                player.openInventory(kitinv);
        } else if (type.equalsIgnoreCase("private")) {
            ItemStack cancelpane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
            ItemStack savepane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
            ItemMeta cancelmeta = cancelpane.getItemMeta();
            ItemMeta savemeta = savepane.getItemMeta();
                Inventory kitinv = Bukkit.createInventory(null, 54, "Creating Personal Kit: " + name1);
                for (int i = 40; i < 52; i++) {
                    kitinv.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
                }
                cancelmeta.setDisplayName(ChatColor.RED + "CANCEL");
                savemeta.setDisplayName(ChatColor.GREEN + "SAVE");
                cancelpane.setItemMeta(cancelmeta);
                savepane.setItemMeta(savemeta);
                kitinv.setItem(52, cancelpane);
                kitinv.setItem(53, savepane);
                player.openInventory(kitinv);
        }
    }

    public static void deleteKit(String name, String type, Player player) {
        String name1 = name.toLowerCase();
        if (type.equalsIgnoreCase("public")) {
                ConfigManager.getPublicKitFile(name1).delete();
        } else if (type.equalsIgnoreCase("private")) {
                ConfigManager.getPrivateKitFile(name1, player).delete();
        }
    }

    public static void editKit(String name, String type, Player player) {
        String name1 = name.toLowerCase();
        if (type.equalsIgnoreCase("public")) {
            ItemStack cancelpane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
            ItemStack savepane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
            ItemMeta cancelmeta = cancelpane.getItemMeta();
            ItemMeta savemeta = savepane.getItemMeta();
                Inventory inv = Bukkit.createInventory(player, 54, "Editing Kit: " + name1);
                ItemStack[] invContents = new ItemStack[36];
                for (int i = 0; i < 36; i++) {
                    invContents[i] = ConfigManager.getPublicKitConfig(name1).getItemStack("Inventory Contents." + i);
                    inv.setContents(invContents);
                }
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        inv.setItem(36, ConfigManager.getPublicKitConfig(name1).getItemStack("Armor Contents." + 0));
                    } else if (i == 1) {
                        inv.setItem(37, ConfigManager.getPublicKitConfig(name1).getItemStack("Armor Contents." + 1));
                    } else if (i == 2) {
                        inv.setItem(38, ConfigManager.getPublicKitConfig(name1).getItemStack("Armor Contents." + 2));
                    } else if (i == 3) {
                        inv.setItem(49, ConfigManager.getPublicKitConfig(name1).getItemStack("Armor Contents." + 3));
                    }
                }
                for (int i = 40; i < 52; i++) {
                    inv.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
                }
                cancelmeta.setDisplayName(ChatColor.RED + "CANCEL");
                savemeta.setDisplayName(ChatColor.GREEN + "SAVE");
                cancelpane.setItemMeta(cancelmeta);
                savepane.setItemMeta(savemeta);
                inv.setItem(52, cancelpane);
                inv.setItem(53, savepane);
                player.openInventory(inv);
        } else if (type.equalsIgnoreCase("private")) {
            ItemStack cancelpane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
            ItemStack savepane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
            ItemMeta cancelmeta = cancelpane.getItemMeta();
            ItemMeta savemeta = savepane.getItemMeta();
                Inventory inv = Bukkit.createInventory(player, 54, "Editing Personal Kit: " + name1);
                ItemStack[] invContents = new ItemStack[36];
                for (int i = 0; i < 36; i++) {
                    invContents[i] = ConfigManager.getPrivateKitConfig(name1, player).getItemStack("Inventory Contents." + i);
                    inv.setContents(invContents);
                }
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        inv.setItem(36, ConfigManager.getPrivateKitConfig(name1, player).getItemStack("Armor Contents." + 0));
                    } else if (i == 1) {
                        inv.setItem(37, ConfigManager.getPrivateKitConfig(name1, player).getItemStack("Armor Contents." + 1));
                    } else if (i == 2) {
                        inv.setItem(38, ConfigManager.getPrivateKitConfig(name1, player).getItemStack("Armor Contents." + 2));
                    } else if (i == 3) {
                        inv.setItem(39, ConfigManager.getPrivateKitConfig(name1, player).getItemStack("Armor Contents." + 3));
                    }
                }
                for (int i = 40; i < 52; i++) {
                    inv.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
                }
                cancelmeta.setDisplayName(ChatColor.RED + "CANCEL");
                savemeta.setDisplayName(ChatColor.GREEN + "SAVE");
                cancelpane.setItemMeta(cancelmeta);
                savepane.setItemMeta(savemeta);
                inv.setItem(52, cancelpane);
                inv.setItem(53, savepane);
                player.openInventory(inv);
        }
    }

    @SuppressWarnings("deprecation")
    public static void previewKit(String name, String type, Player player) {
        String name1 = name.toLowerCase();
        if (type.equalsIgnoreCase("public")) {
                Inventory inv = Bukkit.createInventory(player, 54, "Previewing Kit: " + name1);
                ItemStack[] invContents = new ItemStack[36];
                for (int i = 0; i < 36; i++) {
                    invContents[i] = ConfigManager.getPublicKitConfig(name1).getItemStack("Inventory Contents." + i);
                    inv.setContents(invContents);
                }
                for (int i = 40; i < 54; i++) {
                    inv.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
                }
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        inv.setItem(36, ConfigManager.getPublicKitConfig(name1).getItemStack("Armor Contents." + 0));
                    } else if (i == 1) {
                        inv.setItem(37, ConfigManager.getPublicKitConfig(name1).getItemStack("Armor Contents." + 1));
                    } else if (i == 2) {
                        inv.setItem(38, ConfigManager.getPublicKitConfig(name1).getItemStack("Armor Contents." + 2));
                    } else if (i == 3) {
                        inv.setItem(39, ConfigManager.getPublicKitConfig(name1).getItemStack("Armor Contents." + 3));
                    }
                }
            player.openInventory(inv);
        } else if (type.equalsIgnoreCase("private")) {
                Inventory inv = Bukkit.createInventory(player, 54, "Previewing Kit: " + name1);
                ItemStack[] invContents = new ItemStack[36];
                for (int i = 0; i < 36; i++) {
                    invContents[i] = ConfigManager.getPrivateKitConfig(name1, player).getItemStack("Inventory Contents." + i);
                    inv.setContents(invContents);
                }
                for (int i = 40; i < 54; i++) {
                    inv.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
                }
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        inv.setItem(36, ConfigManager.getPrivateKitConfig(name1, player).getItemStack("Armor Contents." + 0));
                    } else if (i == 1) {
                        inv.setItem(37, ConfigManager.getPrivateKitConfig(name1, player).getItemStack("Armor Contents." + 1));
                    } else if (i == 2) {
                        inv.setItem(38, ConfigManager.getPrivateKitConfig(name1, player).getItemStack("Armor Contents." + 2));
                    } else if (i == 3) {
                        inv.setItem(39, ConfigManager.getPrivateKitConfig(name1, player).getItemStack("Armor Contents." + 3));
                    }
                }
            player.openInventory(inv);
        }
    }

    @SuppressWarnings("deprecation")
    public static void recieveKit(String name, String type, Player player) {
        String name1 = name.toLowerCase();
        if (type.equalsIgnoreCase("public")) {
                PlayerInventory inv = player.getInventory();
                ItemStack[] invContents = new ItemStack[36];
                ItemStack[] armorContents = new ItemStack[4];
                for (int i = 0; i < 36; i++) {
                    invContents[i] = ConfigManager.getPublicKitConfig(name1).getItemStack("Inventory Contents." + i);
                    if (invContents[i] != null) {
                        inv.addItem(invContents[i]);
                    }
                }
                for (int i = 0; i < 4; i++) {
                    armorContents[i] = ConfigManager.getPublicKitConfig(name1).getItemStack("Armor Contents." + i);
                    inv.setArmorContents(armorContents);
                }
        } else if (type.equalsIgnoreCase("private")) {
                PlayerInventory inv = player.getInventory();
                ItemStack[] invContents = new ItemStack[36];
                ItemStack[] armorContents = new ItemStack[4];
                for (int i = 0; i < 36; i++) {
                    invContents[i] = ConfigManager.getPrivateKitConfig(name1, player).getItemStack("Inventory Contents." + i);
                    inv.setContents(invContents);
                }
                for (int i = 0; i < 4; i++) {
                    armorContents[i] = ConfigManager.getPrivateKitConfig(name1, player).getItemStack("Armor Contents." + i);
                    inv.setArmorContents(armorContents);
                }
        }
    }
}
