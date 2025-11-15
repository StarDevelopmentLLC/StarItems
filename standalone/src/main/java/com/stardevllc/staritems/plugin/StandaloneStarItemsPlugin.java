package com.stardevllc.staritems.plugin;

import com.stardevllc.staritems.StarItems;
import com.stardevllc.staritems.cmd.StarItemsCommand;
import com.stardevllc.starmclib.StarMCLib;
import com.stardevllc.starmclib.plugin.ExtendedJavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class StandaloneStarItemsPlugin extends ExtendedJavaPlugin {
    @Override
    public void onEnable() {
        Plugin starmclibPlugin = Bukkit.getPluginManager().getPlugin("StarMCLib");
        if (starmclibPlugin != null) {
            getLogger().severe("StarMCLib plugin detected with the StarItems-Standalone plugin");
            getLogger().severe("Please either replace StarItems-Standalone with StarItems Plugin or remove StarMCLib plugin");
            getLogger().severe("Please see the wiki page for more information");
            getLogger().severe("https://github.com/StarDevelopmentLLC/StarItems/wiki/Available-Binaries");
        }
        
        StarMCLib.init(this);
        super.onEnable();
        StarItems.init(this);
        registerCommand("staritems", new StarItemsCommand(this));
    }
}