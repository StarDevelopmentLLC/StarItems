package com.stardevllc.staritems;

import com.stardevllc.staritems.cmd.StarItemsCommand;
import com.stardevllc.staritems.listener.*;
import com.stardevllc.staritems.model.ItemRegistry;
import com.stardevllc.staritems.tasks.InventoryItemTask;
import com.stardevllc.starmclib.StarMCLib;
import com.stardevllc.starmclib.plugin.ExtendedJavaPlugin;
import org.bukkit.plugin.ServicePriority;

public class StarItems extends ExtendedJavaPlugin {
    
    private ItemRegistry itemRegistry;

    @Override
    public void onEnable() {
        super.onEnable();
        StarMCLib.registerPluginEventBus(getEventBus());
        StarMCLib.registerPluginInjector(this, getInjector());
        getLogger().info("Initialized the config.yml file");
        
        this.itemRegistry = getInjector().inject(new ItemRegistry(this));
        getServer().getServicesManager().register(ItemRegistry.class, itemRegistry, this, ServicePriority.Highest);
        StarMCLib.GLOBAL_INJECTOR.setInstance(this.itemRegistry);
        getLogger().info("Initialized the ItemRegistry");
        
        registerListeners(new PlayerListener(), new BlockListener(), new EntityListener());
        getLogger().info("Registered event handlers");
        
        registerCommand("staritems", getInjector().inject(new StarItemsCommand()));
        
        new InventoryItemTask(this).start();
        getLogger().info("StarItems loading complete");
    }

    public ItemRegistry getItemRegistry() {
        return itemRegistry;
    }
}