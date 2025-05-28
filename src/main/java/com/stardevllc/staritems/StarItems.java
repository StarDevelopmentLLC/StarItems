package com.stardevllc.staritems;

import com.stardevllc.starcore.base.wrappers.MCWrappers;
import com.stardevllc.starcore.base.wrappers.PlayerHandWrapper;
import com.stardevllc.starcore.config.Configuration;
import com.stardevllc.staritems.cmd.StarItemsCommand;
import com.stardevllc.staritems.listener.*;
import com.stardevllc.staritems.model.ItemRegistry;
import com.stardevllc.staritems.tasks.InventoryItemTask;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class StarItems extends JavaPlugin {
    
    private Configuration mainConfig;
    private ItemRegistry itemRegistry = new ItemRegistry();
    private PlayerHandWrapper playerHandWrapper;

    @Override
    public void onEnable() {
        this.mainConfig = new Configuration(new File(getDataFolder(), "config.yml"));
        getLogger().info("Initialized the config.yml file");
        
        this.playerHandWrapper = getServer().getServicesManager().getRegistration(MCWrappers.class).getProvider().getPlayerHandWrapper();
        if (this.playerHandWrapper != null) {
            getLogger().info("Retrieved the player hand wrapper: " + this.playerHandWrapper.getClass().getName());
        } else {
            getLogger().warning("Could not retrieve the player hand wrapper");
        }
        
        getServer().getServicesManager().register(ItemRegistry.class, itemRegistry, this, ServicePriority.Highest);
        getLogger().info("Registered the ItemRegistry to the ServicesManager");
        
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityListener(this), this);
        getLogger().info("Registered event handlers");
        
        getCommand("staritems").setExecutor(new StarItemsCommand(this));
        getLogger().info("Registered the /staritems command");
        
        new InventoryItemTask(this).start();
        getLogger().info("Started the InventoryItemTask");
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