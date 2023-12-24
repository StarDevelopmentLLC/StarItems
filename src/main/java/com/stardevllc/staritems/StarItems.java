package com.stardevllc.staritems;

import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class StarItems extends JavaPlugin {
    
    private CustomItemsManager customItemsManager;
    
    public void onEnable() {
        this.customItemsManager = new CustomItemsManager(this);
        Bukkit.getServicesManager().register(CustomItemsManager.class, this.customItemsManager, this, ServicePriority.Highest);
    }

    public CustomItemsManager getCustomItemsManager() {
        return customItemsManager;
    }
}