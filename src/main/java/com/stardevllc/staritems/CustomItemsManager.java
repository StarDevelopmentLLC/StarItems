package com.stardevllc.staritems;

import com.stardevllc.starlib.registry.Registry;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CustomItemsManager implements Listener {
    private Registry<ItemKey, CustomItem> customItemRegistry = new Registry<>();
    private Registry<ItemKey, Class<? extends CustomItem>> customItemClasses = new Registry<>();

    public CustomItemsManager(JavaPlugin plugin) {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        ItemStack itemStack = e.getItem();
        ItemKey itemKey = CustomItem.getKeyFromItem(itemStack);
        if (itemKey == null) {
            return;
        }
        
        CustomItem customItem = getCustomItem(itemKey);
        if (customItem.getInteractConsumer() != null) {
            customItem.getInteractConsumer().accept(e);
        }
    }
    
    public void addCustomItemClass(ItemKey itemKey, Class<? extends CustomItem> clazz) {
        try {
            clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("CustomItem classes must have a default no-args constructor.");
        }
        
        this.customItemClasses.register(itemKey, clazz);
    }
    
    public void addCustomItem(CustomItem customItem) {
        this.customItemRegistry.register(customItem.getKey(), customItem);
    }
    
    public void removeCustomItemClass(ItemKey itemKey) {
        this.customItemClasses.deregister(itemKey);
    }
    
    public void removeCustomItem(ItemKey itemKey) {
        this.customItemRegistry.deregister(itemKey);
    }
    
    public void removeCustomItem(String namespace, String key) {
        removeCustomItem(new ItemKey(namespace, key));
    }
    
    public Class<? extends CustomItem> getCustomItemClass(ItemKey itemKey) {
        return this.customItemClasses.get(itemKey);
    }
    
    public CustomItem getCustomItem(ItemKey itemKey) {
        if (this.customItemRegistry.contains(itemKey)) {
            return this.customItemRegistry.get(itemKey);
        } else {
            Class<? extends CustomItem> itemClass = this.customItemClasses.get(itemKey);
            try {
                return itemClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
            }
        }
        
        return null;
    }
    
    public CustomItem getCustomItem(String namespace, String key) {
        return getCustomItem(new ItemKey(namespace, key));
    }
    
    public List<CustomItem> getItemsInNamespace(String namespace) {
        List<CustomItem> items = new ArrayList<>();
        this.customItemRegistry.getObjects().forEach((key, item) -> {
            if (key.getNamespace().equalsIgnoreCase(namespace)) {
                items.add(item);
            }
        });
        
        return items;
    }
    
    public List<CustomItem> getAllItems(String itemName) {
        List<CustomItem> items = new ArrayList<>();
        this.customItemRegistry.getObjects().forEach((key, item) -> {
            if (key.getKey().equalsIgnoreCase(itemName)) {
                items.add(item);
            }
        });
        return items;
    }
}