package com.stardevllc.staritems;

import com.stardevllc.starcore.api.wrappers.MCWrappers;
import com.stardevllc.starcore.api.wrappers.PlayerHandWrapper;
import com.stardevllc.starcore.config.Configuration;
import com.stardevllc.staritems.cmd.StarItemsCommand;
import com.stardevllc.staritems.listener.*;
import com.stardevllc.staritems.model.ItemRegistry;
import com.stardevllc.staritems.tasks.InventoryItemTask;
import com.stardevllc.starmclib.StarMCLib;
import com.stardevllc.starmclib.plugin.ExtendedJavaPlugin;
import org.bukkit.plugin.ServicePriority;

import java.io.File;

public class StarItems extends ExtendedJavaPlugin {
    
    private Configuration mainConfig;
    private ItemRegistry itemRegistry;
    private PlayerHandWrapper playerHandWrapper;

    @Override
    public void onEnable() {
        super.onEnable();
        this.mainConfig = new Configuration(new File(getDataFolder(), "config.yml"));
        getLogger().info("Initialized the config.yml file");
        
        this.playerHandWrapper = getServer().getServicesManager().getRegistration(MCWrappers.class).getProvider().getPlayerHandWrapper();
        if (this.playerHandWrapper != null) {
            getLogger().info("Retrieved the player hand wrapper: " + this.playerHandWrapper.getClass().getName());
        } else {
            getLogger().warning("Could not retrieve the player hand wrapper");
        }
        
        this.itemRegistry = getInjector().inject(new ItemRegistry(this));
        getServer().getServicesManager().register(ItemRegistry.class, itemRegistry, this, ServicePriority.Highest);
        StarMCLib.GLOBAL_INJECTOR.setInstance(this.itemRegistry);
        getLogger().info("Initialized the ItemRegistry");
        
        registerListeners(new PlayerListener(), new BlockListener(), new EntityListener());
        getLogger().info("Registered event handlers");
        
        registerCommand("staritems", new StarItemsCommand());
        
        new InventoryItemTask(this).start();
        getLogger().info("StarItems loading complete");
    }

    public Configuration getMainConfig() {
        return mainConfig;
    }

    public ItemRegistry getItemRegistry() {
        return itemRegistry;
    }

    public PlayerHandWrapper getPlayerHandWrapper() {
        return playerHandWrapper;
    }
}