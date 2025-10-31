package com.stardevllc.staritems;

import com.stardevllc.itembuilder.ItemBuilders;
import com.stardevllc.staritems.listener.*;
import com.stardevllc.staritems.model.ItemRegistry;
import com.stardevllc.staritems.tasks.InventoryItemTask;
import com.stardevllc.starmclib.StarMCLib;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class StarItems {
    private StarItems() {}
    
    private static ItemRegistry itemRegistry;
    
    //Add some of the inventory events
    
    private static JavaPlugin plugin;
    
    public static void init(JavaPlugin plugin) {
        if (StarItems.plugin != null) {
            plugin.getLogger().info("StarItems has already been initialized by " + StarItems.plugin.getName());
            return;
        }
        
        ItemBuilders.init();
        
        StarItems.plugin = plugin;
        itemRegistry = new ItemRegistry(plugin);
        Bukkit.getServer().getServicesManager().register(ItemRegistry.class, itemRegistry, plugin, ServicePriority.Highest);
        StarMCLib.GLOBAL_INJECTOR.set(itemRegistry);
        
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerListener(), plugin);
        pluginManager.registerEvents(new BlockListener(), plugin);
        pluginManager.registerEvents(new EntityListener(), plugin);
        
        new InventoryItemTask(plugin).start();
    }

    public static ItemRegistry getItemRegistry() {
        return itemRegistry;
    }
}