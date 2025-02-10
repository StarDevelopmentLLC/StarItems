package com.stardevllc.staritems.model.types;

import com.stardevllc.staritems.model.EventType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerEvent<T extends org.bukkit.event.player.PlayerEvent> extends EventType<T> {
    public static final PlayerEvent<PlayerItemConsumeEvent> CONSUME = new PlayerEvent<>();
    public static final PlayerEvent<PlayerInteractEntityEvent> INTERACT_ENTITY = new PlayerEvent<>();
    public static final PlayerEvent<PlayerInteractEvent> INTERACT = new PlayerEvent<>();
}
