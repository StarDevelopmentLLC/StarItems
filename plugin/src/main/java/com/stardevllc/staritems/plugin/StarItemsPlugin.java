package com.stardevllc.staritems.plugin;

import com.stardevllc.staritems.StarItems;
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