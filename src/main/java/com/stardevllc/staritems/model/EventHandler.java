package com.stardevllc.staritems.model;

import org.bukkit.event.Event;

public interface EventHandler<T extends Event> {
    void onEvent(T event);
}
