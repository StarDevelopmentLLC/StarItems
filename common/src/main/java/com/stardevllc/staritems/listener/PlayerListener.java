package com.stardevllc.staritems.listener;

import com.stardevllc.staritems.StarItems;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerListener implements Listener {
    
    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent e) {
        StarItems.getItemRegistry().handleItemEvent(e, e.getItem());
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent e) {
        StarItems.getItemRegistry().handleItemEvent(e, e.getItem());
    }
}
