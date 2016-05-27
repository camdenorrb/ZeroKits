package me.ZeroDown.ZeroKits.Managers;

import me.ZeroDown.ZeroKits.ZeroKits;
import org.anjocaido.groupmanager.GroupManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PermissionsManager {

    public static boolean ifPlayerHasPermission(Player p, String s) {
        if (ZeroKits.permissiontype == "Vault") {
           return ZeroKits.perms.playerHas(p, s);
        } else if (ZeroKits.permissiontype == "GroupManager") {
            GroupManager gmPlugin = (GroupManager) Bukkit.getPluginManager().getPlugin("GroupManager");
            return gmPlugin.getWorldsHolder().getWorldPermissions(p).has(p, s);
        } else if (ZeroKits.permissiontype == "PermissionsEx") {
            return PermissionsEx.getUser(p).has(s);
        } else if (ZeroKits.permissiontype == "null") {
            return true;
        }
        return false;
    }

    public static int getLimit(Player p) {
        if (ZeroKits.permissiontype == "null") {
            return 0;
        } else {
            for (short ctr = 0; ctr < 255; ctr = (short) (ctr + 1)) {
                if (ifPlayerHasPermission(p, "zerokits.limitkits." + ctr) == true) {
                    return ctr;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }
}
