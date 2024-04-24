package com.stardevllc.items.listener;

import com.stardevllc.items.StarItems;
import com.stardevllc.items.model.CustomItem;
import com.stardevllc.starlib.helper.StringHelper;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener {
    
    private StarItems plugin;

    public BlockListener(StarItems plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        ItemStack mainHand = e.getPlayer().getInventory().getItemInMainHand();

        if (mainHand.getType() != Material.AIR) {
            String id = NBT.get(mainHand, nbt -> {
                return nbt.getString("staritemsid");
            });
            if (!StringHelper.isEmpty(id)) {
                CustomItem customItem = plugin.getItemRegistry().get(id);
                if (customItem != null) {
                    if (customItem.getBlockBreakConsumer() != null) {
                        customItem.getBlockBreakConsumer().accept(e);
                    }
                }
            }
        }
    }
}
