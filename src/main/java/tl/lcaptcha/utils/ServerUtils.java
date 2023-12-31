package tl.lcaptcha.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import tl.lcaptcha.Plugin;

import java.util.List;

public class ServerUtils {
    private final Plugin plugin;

    public ServerUtils(Plugin plugin) {
        this.plugin = plugin;
    }

    public void sendMessage(CommandSender player, String key) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Plugin.getInstance().getConfig().getString(key)));
    }

    public void broadcastMessage(String key, String... placeholders) {
        Bukkit.getOnlinePlayers().forEach(player ->
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        getConfigString(key, placeholders))));
    }

    public String getConfigString(String key, String... placeholders) {
        String message = plugin.getConfig().getString(key, "");
        return replacePlaceholders(message, placeholders);
    }

    public int getConfigInt(String key) {
        return plugin.getConfig().getInt(key, 0);
    }

    public List<String> getConfigStringList(String key) {
        return plugin.getConfig().getStringList(key);
    }

    public String replacePlaceholders(String message, String... placeholders) {
        for (int i = 0; i < placeholders.length; i += 2) {
            message = message.replace(placeholders[i], placeholders[i + 1]);
        }
        return message;
    }
}
