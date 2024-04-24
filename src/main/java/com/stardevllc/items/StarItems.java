package com.stardevllc.items;

import com.stardevllc.starcore.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class StarItems extends JavaPlugin {
    
    private Config mainConfig;

    @Override
    public void onEnable() {
        this.mainConfig = new Config(new File(getDataFolder(), "config.yml"));
    }

    public Config getMainConfig() {
        return mainConfig;
    }
}