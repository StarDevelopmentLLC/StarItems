package com.stardevllc.items;

import com.stardevllc.items.model.ItemRegistry;
import com.stardevllc.starcore.utils.Config;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class StarItems extends JavaPlugin {
    
    private Config mainConfig;
    
    private ItemRegistry itemRegistry = new ItemRegistry();

    @Override
    public void onEnable() {
        this.mainConfig = new Config(new File(getDataFolder(), "config.yml"));
        
        getServer().getServicesManager().register(ItemRegistry.class, itemRegistry, this, ServicePriority.Highest);
    }

    public Config getMainConfig() {
        return mainConfig;
    }
}