package com.stardevllc.staritems.listener;

import com.stardevllc.smcversion.MCWrappers;
import com.stardevllc.staritems.StarItems;
import com.stardevllc.starlib.dependency.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListener implements Listener {

    @Inject
    private StarItems plugin;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        plugin.getItemRegistry().handleItemEvent(e, MCWrappers.getPlayerHandWrapper().getItemInMainHand(e.getPlayer()));
    }
}
