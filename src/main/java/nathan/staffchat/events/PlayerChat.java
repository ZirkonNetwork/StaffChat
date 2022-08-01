package nathan.staffchat.events;

import nathan.staffchat.StaffChat;
import nathan.staffchat.StaffChatData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void playerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("sc.use") && event.getMessage().startsWith(StaffChat.getUseScPrefix().toLowerCase())) {
            StaffChat.sendStaffMessage(event.getPlayer().getDisplayName(), event.getMessage().substring(1));
            event.setCancelled(true);
        } else if (player.hasPermission("sc.use") && (StaffChatData.toggleMap.containsKey(player.getUniqueId()) && StaffChatData.toggleMap.get(player.getUniqueId()))) {
            StaffChat.sendStaffMessage(event.getPlayer().getDisplayName(), event.getMessage().substring(1));
            event.setCancelled(true);
        }
    }
}
