package nathan.staffchat.commands;

import nathan.staffchat.StaffChat;
import nathan.staffchat.StaffChatData;
import nathan.staffchat.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StaffChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if ((command.getName().equalsIgnoreCase("staffchat") || command.getName().equalsIgnoreCase("sc")) && sender.hasPermission("sc.use")) {
            if (args.length > 0) {
                String displayName = sender.getName();

                if (sender instanceof Player) {
                    displayName = ((Player) sender).getDisplayName();
                }

                StaffChat.sendStaffMessage(displayName, Utils.msgBuilder(args));
                return true;
            } else if (!(sender instanceof Player)) {
                sender.sendMessage(Utils.translateColorCodes(StaffChat.pluginPrefix + "&4Non-player users must include a message with this command!"));
                return true;
            } else {
                if (!StaffChatData.toggleMap.containsKey(((Player) sender).getUniqueId())) {
                    StaffChatData.toggleMap.put(((Player) sender).getUniqueId(), true);
                } else {
                    StaffChatData.toggleMap.put(((Player) sender).getUniqueId(), !StaffChatData.toggleMap.get(((Player) sender).getUniqueId()));
                }

                sender.sendMessage(Utils.translateColorCodes(StaffChat.pluginPrefix + "Staff chat toggled " + (StaffChatData.toggleMap.get(((Player) sender).getUniqueId()) ? "&aon" : "&coff")));
                return true;
            }
        }
        return true;
    }
}
