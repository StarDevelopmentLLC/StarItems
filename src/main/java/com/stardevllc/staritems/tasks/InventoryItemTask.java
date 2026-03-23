package com.stardevllc.staritems.tasks;

import com.stardevllc.smcversion.MCWrappers;
import com.stardevllc.staritems.StarItems;
import com.stardevllc.staritems.model.CustomItem;
import com.stardevllc.starlib.helper.StringHelper;
import com.stardevllc.starmclib.StarThread;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryItemTask extends StarThread<JavaPlugin> {
    public InventoryItemTask(JavaPlugin plugin) {
        super(plugin, 20L, 1L, false);
    }

    @Override
    public void onRun() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerInventory inv = player.getInventory();
            
            CustomItem mainHandItem = getCustomItem(MCWrappers.getPlayerHandWrapper().getItemInMainHand(player));
            if (mainHandItem != null) {
                if (mainHandItem.getWhileHoldingConsumer() != null) {
                    mainHandItem.getWhileHoldingConsumer().accept(player);
                }
            }
            
            CustomItem offHandItem = getCustomItem(MCWrappers.getPlayerHandWrapper().getItemInOffHand(player));
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

                if (i <= 8) { //Hotbar check
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
        
        return StarItems.getItemRegistry().get(id);
    }
}
