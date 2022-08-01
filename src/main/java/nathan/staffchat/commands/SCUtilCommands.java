package nathan.staffchat.commands;

import nathan.staffchat.StaffChat;
import nathan.staffchat.StaffChatData;
import nathan.staffchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SCUtilCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if ((command.getName().equalsIgnoreCase("reloadstaffchat") || command.getName().equalsIgnoreCase("reloadsc")
                || command.getName().equalsIgnoreCase("rsc")) && sender.hasPermission("sc.manage")) {
            StaffChatData.saveToggleMap();
            StaffChat.plugin.reloadConfig();

            if (StaffChatData.isDataEmpty()) {
                StaffChatData.loadToggleMap();
            }

            final String success = Utils.translateColorCodes(StaffChat.getPluginPrefix() + "&3Reloaded Staff Chat v" + StaffChat.VERSION);

            if (sender instanceof Player) {
                sender.sendMessage(success);
            }
            Bukkit.getConsoleSender().sendMessage(success);
        }

        return true;
    }
}
