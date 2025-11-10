package com.stardevllc.staritems.plugin;

import com.stardevllc.staritems.StarItems;
import com.stardevllc.staritems.cmd.StarItemsCommand;
import com.stardevllc.starmclib.plugin.ExtendedJavaPlugin;
import net.byteflux.libby.BukkitLibraryManager;

import java.io.File;

public class StarItemsPlugin extends ExtendedJavaPlugin {
    
    public StarItemsPlugin() {
        BukkitLibraryManager bukkitLibraryManager = new BukkitLibraryManager(this, new File(".", "plugins").toPath(), "libraries");
        bukkitLibraryManager.configureFromJSON();
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        StarItems.init(this);
        registerCommand("staritems", new StarItemsCommand(this));
    }
}