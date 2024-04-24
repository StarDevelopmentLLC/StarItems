package com.stardevllc.items.listener;

import com.stardevllc.items.StarItems;
import com.stardevllc.items.model.CustomItem;
import com.stardevllc.starlib.helper.StringHelper;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {
    
    private StarItems plugin;
    
    public PlayerListener(StarItems plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        ItemStack itemStack = e.getItem();
        
        if (itemStack == null) {
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
        
        if (customItem.getInteractConsumer() == null) {
            return;
        }
        
        customItem.getInteractConsumer().accept(e);
    }
}
