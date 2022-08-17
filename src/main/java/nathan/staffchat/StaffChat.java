package nathan.staffchat;

import nathan.staffchat.commands.SCUtilCommands;
import nathan.staffchat.commands.StaffChatCommand;
import nathan.staffchat.events.PlayerChat;
import nathan.staffchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class StaffChat extends JavaPlugin {

    public static final String VERSION = "1.2.0";

    public static JavaPlugin plugin = null;
    private static String contentSeparator = null;
    public static String pluginPrefix = null;
    private static String scUsernameColor = null;
    private static String scChatPrefix = null;
    public static String scMessagePrefix = null;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        if (!StaffChatData.isDataEmpty()) {
            StaffChatData.loadToggleMap();
        }

        contentSeparator = this.getConfig().getString("content-separator");
        pluginPrefix = this.getConfig().getString("plugin-prefix");
        scUsernameColor = this.getConfig().getString("default-username-color");
        scChatPrefix = this.getConfig().getString("sc-prefix");
        scMessagePrefix = this.getConfig().getString("sc-message-prefix");

        Objects.requireNonNull(this.getCommand("staffchat")).setExecutor(new StaffChatCommand());
        Objects.requireNonNull(this.getCommand("reloadstaffchat")).setExecutor(new SCUtilCommands());
        this.getServer().getPluginManager().registerEvents(new PlayerChat(), this);
        this.getLogger().info("Started Staff Chat v" + VERSION);
    }

    @Override
    public void onDisable() {
        if (StaffChatData.toggleMap != null) {
            if (!StaffChatData.toggleMap.isEmpty()) {
                StaffChatData.saveToggleMap();
            }
        }
    }

    public static void sendStaffMessage(String senderName, String messageContent) {
        final String stringToSend = Utils.translateColorCodes(scChatPrefix + scUsernameColor + senderName + contentSeparator + messageContent);

        Bukkit.getConsoleSender().sendMessage(stringToSend);

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.hasPermission("sc.view")) {
                player.sendMessage(stringToSend);
            }
        }
    }
}
