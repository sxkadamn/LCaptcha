package tl.lcaptcha.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import tl.lcaptcha.Plugin;
import tl.lcaptcha.builder.BuilderImpl;

public class MenuClose implements Listener {
    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        BuilderImpl builder = BuilderImpl.getBuilder(player.getName());

        if (builder != null &&
                event.getInventory().equals(builder.getInventory()) &&
                !builder.isCanClose()) {
            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', Plugin.getInstance().getConfig().getString("messages.close")));
        }
    }
}
