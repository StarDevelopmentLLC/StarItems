package com.stardevllc.staritems.model;

import org.bukkit.event.Event;

@FunctionalInterface
public interface EventHandler<T extends Event> {
    void onEvent(T event);
}
