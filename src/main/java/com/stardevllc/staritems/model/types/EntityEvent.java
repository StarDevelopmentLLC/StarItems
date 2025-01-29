package com.stardevllc.staritems.model.types;

import com.stardevllc.staritems.model.EventType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityEvent<T extends org.bukkit.event.entity.EntityEvent> extends EventType<T> {
    public static final EntityEvent<EntityDamageByEntityEvent> DAMAGE_BY_OTHER = new EntityEvent<>();
    public static final EntityEvent<EntityDeathEvent> DEATH = new EntityEvent<>();
}
