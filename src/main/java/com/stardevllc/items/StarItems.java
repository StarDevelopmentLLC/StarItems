package com.stardevllc.items;

import com.stardevllc.items.cmd.StarItemsCommand;
import com.stardevllc.items.listener.BlockListener;
import com.stardevllc.items.listener.EntityListener;
import com.stardevllc.items.listener.PlayerListener;
import com.stardevllc.items.model.ItemRegistry;
import com.stardevllc.items.tasks.InventoryItemTask;
import com.stardevllc.items.wrapper.PlayerHandWrapper;
import com.stardevllc.items.wrapper.impl.PlayerHandWrapper_1_8;
import com.stardevllc.items.wrapper.impl.PlayerHandWrapper_1_9;
import com.stardevllc.starcore.utils.Config;
import com.stardevllc.starcore.utils.NMSVersion;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class StarItems extends JavaPlugin {
    
    private Config mainConfig;
    private ItemRegistry itemRegistry = new ItemRegistry();
    private PlayerHandWrapper playerHandWrapper;

    @Override
    public void onEnable() {
        this.mainConfig = new Config(new File(getDataFolder(), "config.yml"));
        
        if (NMSVersion.CURRENT_VERSION.ordinal() <= NMSVersion.v1_8_R3.ordinal()) {
            playerHandWrapper = new PlayerHandWrapper_1_8();
        } else {
            playerHandWrapper = new PlayerHandWrapper_1_9();
        }
        
        getServer().getServicesManager().register(ItemRegistry.class, itemRegistry, this, ServicePriority.Highest);
        
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityListener(this), this);
        
        getCommand("staritems").setExecutor(new StarItemsCommand(this));
        
        new InventoryItemTask(this).start();
    }

    public Config getMainConfig() {
        return mainConfig;
    }

    public ItemRegistry getItemRegistry() {
        return itemRegistry;
    }

    public PlayerHandWrapper getPlayerHandWrapper() {
        return playerHandWrapper;
    }
}