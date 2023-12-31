package tl.lcaptcha.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tl.lcaptcha.Plugin;
import tl.lcaptcha.builder.BuilderImpl;

public class QuitEvent implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        BuilderImpl builder = BuilderImpl.getBuilder(player.getName());

        if (builder != null) {
            Plugin.getBuilders().remove(builder);
            JoinEvent.naproverke.remove(player.getName());
        }
    }
}
