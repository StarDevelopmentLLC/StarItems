package com.stardevllc.items.model;

import org.bukkit.event.Event;

public interface EventHandler<T extends Event> {
    void onEvent(T event);
}
