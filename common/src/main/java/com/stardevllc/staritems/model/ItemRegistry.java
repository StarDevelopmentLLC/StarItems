package com.stardevllc.staritems.model;

import com.stardevllc.starlib.eventbus.BusDispatcher;
import com.stardevllc.starlib.eventbus.impl.StarEventBus;
import com.stardevllc.starlib.helper.StringHelper;
import com.stardevllc.starlib.registry.AbstractRegistry;
import com.stardevllc.starlib.registry.RegistryKey;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry extends AbstractRegistry<CustomItem> {
    public ItemRegistry(JavaPlugin plugin) {
        super(RegistryKey.of("customitems"), "Custom Items", new HashMap<>());
        
        //This event bus is just used internally for the Registry Events themselves to allow listening for them
        setDispatcher(new BusDispatcher<>(new StarEventBus<>(ItemRegistry.Event.class)));
        
        addRegisterListener(e -> plugin.getLogger().info("Registered the item " + e.value().getName() + " from the plugin " + e.value().getPlugin().getName() + " v" + e.value().getPlugin().getDescription().getVersion()));
    }
    
    @Override
    public Map<RegistryKey, CustomItem> toMapCopy() {
        return new HashMap<>(this.backingMap);
    }
    
    public void handleItemEvent(org.bukkit.event.Event event, ItemStack... itemStacks) {
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