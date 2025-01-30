package com.stardevllc.staritems.listener;

import com.stardevllc.staritems.StarItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListener implements Listener {

    private StarItems plugin;

    public BlockListener(StarItems plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        plugin.getItemRegistry().handleItemEvent(e, plugin.getPlayerHandWrapper().getItemInMainHand(e.getPlayer()));
    }
}
