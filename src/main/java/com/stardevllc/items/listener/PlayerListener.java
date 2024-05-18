package com.stardevllc.items.listener;

import com.stardevllc.items.StarItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerListener implements Listener {
    
    private StarItems plugin;
    
    public PlayerListener(StarItems plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent e) {
        plugin.getItemRegistry().handleItemEvent(e, e.getItem());
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        plugin.getItemRegistry().handleItemEvent(e, e.getItem());
    }
}
