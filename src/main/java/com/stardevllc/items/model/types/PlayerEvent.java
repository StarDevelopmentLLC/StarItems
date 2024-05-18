package com.stardevllc.items.model.types;

import com.stardevllc.items.model.EventType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerEvent<T extends org.bukkit.event.player.PlayerEvent> extends EventType<T> {
    public static final PlayerEvent<PlayerItemConsumeEvent> CONSUME = new PlayerEvent<>();
    public static final PlayerEvent<PlayerInteractEntityEvent> INTERACT = new PlayerEvent<>();
}
