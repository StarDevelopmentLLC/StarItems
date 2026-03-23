package com.stardevllc.staritems.listener;

import com.stardevllc.smcversion.MCWrappers;
import com.stardevllc.staritems.StarItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityListener implements Listener {
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        Player killer = e.getEntity().getKiller();
        if (killer == null) {
            return;
        }
        
        StarItems.getItemRegistry().handleItemEvent(e, MCWrappers.getPlayerHandWrapper().getItemInMainHand(killer));
    }
    
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player player)) {
            return;
        }

        StarItems.getItemRegistry().handleItemEvent(e, MCWrappers.getPlayerHandWrapper().getItemInMainHand(player));
    }
}