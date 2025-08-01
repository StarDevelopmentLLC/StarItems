package com.stardevllc.staritems.tasks;

import com.stardevllc.starcore.utils.StarThread;
import com.stardevllc.staritems.StarItems;
import com.stardevllc.staritems.model.CustomItem;
import com.stardevllc.starlib.helper.StringHelper;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryItemTask extends StarThread<StarItems> {
    public InventoryItemTask(StarItems plugin) {
        super(plugin, 20L, 1L, false);
    }

    @Override
    public void onRun() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerInventory inv = player.getInventory();
            
            CustomItem mainHandItem = getCustomItem(plugin.getPlayerHandWrapper().getItemInMainHand(player));
            if (mainHandItem != null) {
                if (mainHandItem.getWhileHoldingConsumer() != null) {
                    mainHandItem.getWhileHoldingConsumer().accept(player);
                }
            }
            
            CustomItem offHandItem = getCustomItem(plugin.getPlayerHandWrapper().getItemInOffHand(player));
            if (offHandItem != null) {
                if (offHandItem.getWhileHoldingConsumer() != null) {
                    offHandItem.getWhileHoldingConsumer().accept(player);
                }
            }

            for (ItemStack armorStack : inv.getArmorContents()) {
                CustomItem armorItem = getCustomItem(armorStack);
                if (armorItem != null) {
                    if (armorItem.getWhileWearingConsumer() != null) {
                        armorItem.getWhileWearingConsumer().accept(player);
                    }
                }
            }

            ItemStack[] contents = inv.getContents();
            for (int i = 0; i < contents.length; i++) {
                ItemStack itemStack = contents[i];
                CustomItem customItem = getCustomItem(itemStack);
                if (customItem == null) {
                    continue;
                }

                if (i >= 0 && i <= 8) { //Hotbar check
                    if (customItem.getWhileOnHotbarConsumer() != null) {
                        customItem.getWhileOnHotbarConsumer().accept(player);
                    }
                }
                
                if (customItem.getWhileInInventoryConsumer() != null) {
                    customItem.getWhileInInventoryConsumer().accept(player);
                }
            }
        }
    }
    
    private CustomItem getCustomItem(ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return null;
        }
        
        String id = NBT.get(itemStack, nbt -> {
            return nbt.getString("staritemsid");
        });
        
        if (StringHelper.isEmpty(id)) {
            return null;
        }
        
        return plugin.getItemRegistry().get(id);
    }
}
