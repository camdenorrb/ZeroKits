package ml.ZeroDown.ZeroKits.Events;

import ml.ZeroDown.ZeroKits.Managers.*;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Admin on 5/31/16.
 */
public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock().getType() == Material.WALL_SIGN || e.getClickedBlock().getType() == Material.SIGN_POST) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Sign sign = (Sign) e.getClickedBlock().getState();
                if (sign.getLine(0).equalsIgnoreCase(ChatManager.message("kit-sign-prefix"))) {
                    if (PermissionsManager.ifPlayerHasPermission(e.getPlayer(), "zerokits.kit." + sign.getLine(1))) {
                        if (ConfigManager.getPublicKitFile(sign.getLine(1)).exists()) {
                            if (CooldownManager.isInCooldown(e.getPlayer().getUniqueId(), sign.getLine(1).toLowerCase())) {
                                e.getPlayer().sendMessage(ChatManager.format("still-cooldown").replace("%e", String.valueOf(CooldownManager.getTimeLeft(e.getPlayer().getUniqueId(), sign.getLine(1).toLowerCase()))));
                            } else {
                                if (ConfigManager.getMainConfig().getBoolean("Clear Inventory before Kit") == true) {
                                    e.getPlayer().getInventory().clear();
                                }
                                KitManager.recieveKit(sign.getLine(1), "public", e.getPlayer());
                                if (!PermissionsManager.ifPlayerHasPermission(e.getPlayer(), "zerokits.bypasscooldown")) {
                                    CooldownManager c = new CooldownManager(e.getPlayer().getUniqueId(), sign.getLine(1).toLowerCase(), ConfigManager.getPublicKitConfig(sign.getLine(1).toLowerCase()).getInt("delay"));
                                    c.start();
                                }
                                e.getPlayer().sendMessage(ChatManager.format("kit-recieved").replace("%e", sign.getLine(1).toLowerCase()));
                            }
                        } else {
                            e.getPlayer().sendMessage(ChatManager.format("kit-doesnt-exist").replace("%e", sign.getLine(1).toLowerCase()));
                        }
                    }
                } else if (sign.getLine(0).equalsIgnoreCase(ChatManager.message("preview-sign-prefix"))) {
                    if (PermissionsManager.ifPlayerHasPermission(e.getPlayer(), "zerokits.previewkit." + sign.getLine(1).toLowerCase())) {
                        if (ConfigManager.getPublicKitFile(sign.getLine(1).toLowerCase()).exists()) {
                            KitManager.previewKit(sign.getLine(1).toLowerCase(), "public", e.getPlayer());
                        } else {
                            e.getPlayer().sendMessage(ChatManager.format("kit-doesnt-exist").replace("%e", sign.getLine(1).toLowerCase()));
                        }
                    } else {
                        e.getPlayer().sendMessage(ChatManager.format("no-permission"));
                    }
                }
            }
        }
    }
}
