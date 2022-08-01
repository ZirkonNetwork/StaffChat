package nathan.staffchat.utils;

import org.bukkit.ChatColor;

public class Utils {
    public static String translateColorCodes(String stringToTranslate) {
        return ChatColor.translateAlternateColorCodes('&', stringToTranslate);
    }

    public static String msgBuilder(String[] args) {
        StringBuilder builder = new StringBuilder();

        for (String word : args) {
            builder.append(word).append(" ");
        }

        return builder.toString();
    }
}
