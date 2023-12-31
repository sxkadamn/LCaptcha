package tl.lcaptcha.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tl.lcaptcha.Plugin;
import tl.lcaptcha.builder.BuilderImpl;

import java.util.ArrayList;
import java.util.List;

public class JoinEvent implements Listener {

    public static List<String> naproverke = new ArrayList<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if(naproverke.contains(player.getName())) {
            Plugin.getServerUtils().sendMessage(player, "messages.already");
            return;
        }
        Bukkit.getScheduler().runTaskLater(Plugin.getInstance(), () -> {
            BuilderImpl builder = new BuilderImpl(6, player.getName());
            builder.openInventory(player);
            Plugin.getBuilders().add(builder);
            builder.setCanClose(false);
        }, 20L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Plugin.getInstance(), () -> {
            if (!naproverke.contains(player.getName()))
                player.kickPlayer(ChatColor.translateAlternateColorCodes('&', Plugin.getInstance().getConfig().getString("messages.time_left")));
        }, 20L * Plugin.getInstance().getConfig().getInt("time"));
    }
}
