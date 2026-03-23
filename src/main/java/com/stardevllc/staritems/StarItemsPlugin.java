package com.stardevllc.staritems;

import com.stardevllc.staritems.cmd.StarItemsCommand;
import com.stardevllc.starmclib.plugin.ExtendedJavaPlugin;

public class StarItemsPlugin extends ExtendedJavaPlugin {
    
    @Override
    public void onEnable() {
        super.onEnable();
        StarItems.init(this);
        registerCommand("staritems", new StarItemsCommand(this));
    }
}