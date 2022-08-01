package nathan.staffchat;

import org.bukkit.configuration.Configuration;

import java.util.*;

public class StaffChatData {
    @SuppressWarnings("CanBeFinal")
    public static Map<UUID, Boolean> toggleMap = new HashMap<>();

    public static boolean isDataEmpty() {
        final Configuration config = StaffChat.plugin.getConfig();

        if (config.contains("data")) {
            return config.get("data") != null;
        } else {
            return true;
        }
    }

    public static void loadToggleMap() {
        final Configuration config = StaffChat.plugin.getConfig();

        if (config.contains("data") && config.get("data") != null) {
            for (String rawData : config.getStringList("data")) {
                String[] raw = rawData.split(":");
                toggleMap.put(UUID.fromString(raw[0]), Boolean.valueOf(raw[1]));
            }
        }
    }

    public static void saveToggleMap() {
        List<String> toggleMapData = new ArrayList<>();

        for (UUID uuid : toggleMap.keySet()) {
            String playerSetting = uuid.toString() + ": " + toggleMap.get(uuid);
            toggleMapData.add(playerSetting);
        }

        StaffChat.plugin.getConfig().set("data", toggleMapData);
        StaffChat.plugin.saveConfig();
    }
}
