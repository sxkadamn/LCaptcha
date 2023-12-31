package tl.lcaptcha.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tl.lcaptcha.Plugin;
import tl.lcaptcha.builder.BuilderImpl;

public class MenuClick implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        BuilderImpl builder = BuilderImpl.getBuilder(player.getName());

        if (builder == null || !event.getInventory().equals(builder.getInventory())) {
            return;
        }

        event.setCancelled(true);

        if (event.getCurrentItem() != null) {
            if (event.getCurrentItem().equals(builder.getAcceptItem())) {
                handleAcceptClick(player, builder);
            } else {
                event.setCancelled(true);
                handleFailedClick(player);
            }
        }
    }

    private void handleAcceptClick(Player player, BuilderImpl builder) {
        builder.setCanClose(true);
        Plugin.getServerUtils().sendMessage(player, "messages.success");
        JoinEvent.naproverke.add(player.getName());
        player.closeInventory();
    }

    private void handleFailedClick(Player player) {
        JoinEvent.naproverke.remove(player.getName());
        player.kickPlayer(Plugin.getInstance().getConfig().getString("messages.failed").replace("&", "§"));
    }
}
