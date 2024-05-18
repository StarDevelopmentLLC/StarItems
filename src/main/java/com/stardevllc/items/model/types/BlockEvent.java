package com.stardevllc.items.model.types;

import com.stardevllc.items.model.EventType;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockEvent<T extends org.bukkit.event.block.BlockEvent> extends EventType<T> {
    public static final BlockEvent<BlockBreakEvent> BREAK = new BlockEvent<>();
}
