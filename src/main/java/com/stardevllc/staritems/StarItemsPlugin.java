package com.stardevllc.staritems;

import com.stardevllc.minecraft.plugin.ExtendedJavaPlugin;
import com.stardevllc.staritems.cmd.StarItemsCommand;

public class StarItemsPlugin extends ExtendedJavaPlugin {
    
    @Override
    public void onEnable() {
        super.onEnable();
        StarItems.init(this);
        registerCommand("staritems", new StarItemsCommand(this));
    }
}