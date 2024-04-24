package com.stardevllc.items.listener;

import com.stardevllc.items.StarItems;
import com.stardevllc.items.model.CustomItem;
import com.stardevllc.starlib.helper.StringHelper;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

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

        ItemStack itemStack = killer.getInventory().getItemInMainHand();

        if (itemStack.getType() == Material.AIR) {
            return;
        }

        String id = NBT.get(itemStack, nbt -> {
            return nbt.getString("staritemsid");
        });

        if (StringHelper.isEmpty(id)) {
            return;
        }

        CustomItem customItem = plugin.getItemRegistry().get(id);

        if (customItem == null) {
            return;
        }

        if (customItem.getEntityDeathConsumer() == null) {
            return;
        }

        customItem.getEntityDeathConsumer().accept(e);
    }
    
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player player)) {
            return;
        }

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        
        if (itemStack.getType() == Material.AIR) {
            return;
        }
        
        String id = NBT.get(itemStack, nbt -> {
            return nbt.getString("staritemsid");
        });
        
        if (StringHelper.isEmpty(id)) {
            return;
        }

        CustomItem customItem = plugin.getItemRegistry().get(id);
        
        if (customItem == null) {
            return;
        }
        
        if (customItem.getOnDamageEntityConsumer() == null) {
            return;
        }
        
        customItem.getOnDamageEntityConsumer().accept(e);
    }
}