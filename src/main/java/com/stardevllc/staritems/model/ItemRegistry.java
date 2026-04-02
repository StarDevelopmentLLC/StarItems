package com.stardevllc.staritems.model;

import com.stardevllc.registry.PluginRegistry;
import com.stardevllc.starlib.helper.StringHelper;
import com.stardevllc.starlib.objects.key.impl.StringKey;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class ItemRegistry extends PluginRegistry<CustomItem> {
    public ItemRegistry(JavaPlugin plugin) {
        super(CustomItem.class, new StringKey("customitems"), "Custom Items", new HashMap<>(), null);
        addRegisterListener(e -> plugin.getLogger().info("Registered the item " + e.value().getName() + " from the plugin " + e.value().getPlugin().getName() + " v" + e.value().getPlugin().getDescription().getVersion()));
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
                return nbt.getString(CustomItem.NBT_KEY);
            });
            
            if (StringHelper.isEmpty(id)) {
                return;
            }

            CustomItem customItem = get(new StringKey(id));
            if (customItem == null) {
                return;
            }

            customItem.handleEvent(event);
        }
    }
}