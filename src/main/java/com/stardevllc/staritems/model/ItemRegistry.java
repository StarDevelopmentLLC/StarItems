package com.stardevllc.staritems.model;

import com.stardevllc.helper.StringHelper;
import com.stardevllc.registry.StringRegistry;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public class ItemRegistry extends StringRegistry<CustomItem> {
    public ItemRegistry() {
        super(null, null, CustomItem::getName, null, null);
    }
    
    public void handleItemEvent(Event event, ItemStack... itemStacks) {
        if (itemStacks == null) {
            return;
        }

        for (ItemStack itemStack : itemStacks) {
            if (itemStack == null) {
                return;
            }
            
            if (itemStack.getType() == Material.AIR) {
                return;
            }

            String id = NBT.get(itemStack, nbt -> {
                return nbt.getString("staritemsid");
            });

            if (StringHelper.isEmpty(id)) {
                return;
            }

            CustomItem customItem = get(id);
            if (customItem == null) {
                return;
            }

            customItem.handleEvent(event);
        }
    }
}