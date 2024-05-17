package com.stardevllc.items.model;

import com.stardevllc.starlib.registry.StringRegistry;

public class ItemRegistry extends StringRegistry<CustomItem> {
    public ItemRegistry() {
        super(null, null, CustomItem::getName, null, null);
    }
}