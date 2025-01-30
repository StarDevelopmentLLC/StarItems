package com.stardevllc.staritems.listener;

import com.stardevllc.staritems.StarItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityListener implements Listener {
    
    private StarItems plugin;

    public EntityListener(StarItems plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        Player killer = e.getEntity().getKiller();
        if (killer == null) {
            return;
        }
        
        plugin.getItemRegistry().handleItemEvent(e, plugin.getPlayerHandWrapper().getItemInMainHand(killer));
    }
    
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player player)) {
            return;
        }

        plugin.getItemRegistry().handleItemEvent(e, plugin.getPlayerHandWrapper().getItemInMainHand(player));
    }
}