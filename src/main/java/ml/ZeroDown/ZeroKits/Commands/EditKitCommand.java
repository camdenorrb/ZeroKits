package ml.ZeroDown.ZeroKits.Commands;

import ml.ZeroDown.ZeroKits.Managers.ChatManager;
import ml.ZeroDown.ZeroKits.Managers.KitManager;
import ml.ZeroDown.ZeroKits.Managers.PermissionsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Admin on 5/25/16.
 */
public class EditKitCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!(sender instanceof Player)) {
            sender.sendMessage("[ZeroKits] This is only an in-game command");
        } else {
            if (PermissionsManager.ifPlayerHasPermission(player, "zerokits.editkit")) {
                if (args.length == 0) {
                    player.sendMessage(ChatManager.format("usage").replace("%e", "/editkit <name>"));
                } else if (args.length == 1) {
                        KitManager.editingkit.put(player.getUniqueId(), args[0].toLowerCase());
                        KitManager.editKit(args[0].toLowerCase(), "public", player);
                } else if (args.length > 1) {
                    player.sendMessage(ChatManager.format("usage").replace("%e", "/peditkit <name>"));
                }
            } else {
                player.sendMessage(ChatManager.format("no-permission"));
            }
        }
        return false;

    }

}
