package com.stardevllc.staritems.model;

import com.stardevllc.staritems.StarItems;
import com.stardevllc.starlib.dependency.Inject;
import com.stardevllc.starlib.helper.StringHelper;
import com.stardevllc.starlib.registry.StringRegistry;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public class ItemRegistry extends StringRegistry<CustomItem> {
    
    @Inject
    private StarItems plugin;
    
    public ItemRegistry(StarItems plugin) {
        super(null, null, CustomItem::getName, null, null);
        addRegisterListener((s, customItem) -> plugin.getLogger().info("Registered the item " + customItem.getName() + " from the plugin " + customItem.getPlugin().getName() + " v" + customItem.getPlugin().getDescription().getVersion()));
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