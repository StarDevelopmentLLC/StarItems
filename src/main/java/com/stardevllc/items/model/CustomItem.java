package com.stardevllc.items.model;

import com.stardevllc.starcore.item.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.function.Consumer;

public class CustomItem {
    protected String name;
    protected ItemBuilder itemBuilder;

    protected Consumer<PlayerInteractEvent> interactConsumer;
    protected Consumer<Player> whileInInventoryConsumer;
    protected Consumer<Player> whileOnHotbarConsumer;
    protected Consumer<Player> whileWearingConsumer;
    
    protected Consumer<EntityDamageByEntityEvent> onDamageEntityConsumer;
    protected Consumer<BlockBreakEvent> blockBreakConsumer;
    
    public CustomItem(String name, ItemBuilder itemBuilder) {
        this.name = name;
        this.itemBuilder = itemBuilder;
    }

    public String getName() {
        return name;
    }

    public ItemBuilder getItemBuilder() {
        return itemBuilder;
    }

    public Consumer<PlayerInteractEvent> getInteractConsumer() {
        return interactConsumer;
    }

    public void setInteractConsumer(Consumer<PlayerInteractEvent> interactConsumer) {
        this.interactConsumer = interactConsumer;
    }

    public Consumer<Player> getWhileInInventoryConsumer() {
        return whileInInventoryConsumer;
    }

    public void setWhileInInventoryConsumer(Consumer<Player> whileInInventoryConsumer) {
        this.whileInInventoryConsumer = whileInInventoryConsumer;
    }

    public Consumer<Player> getWhileOnHotbarConsumer() {
        return whileOnHotbarConsumer;
    }

    public void setWhileOnHotbarConsumer(Consumer<Player> whileOnHotbarConsumer) {
        this.whileOnHotbarConsumer = whileOnHotbarConsumer;
    }

    public Consumer<Player> getWhileWearingConsumer() {
        return whileWearingConsumer;
    }

    public void setWhileWearingConsumer(Consumer<Player> whileWearingConsumer) {
        this.whileWearingConsumer = whileWearingConsumer;
    }

    public Consumer<EntityDamageByEntityEvent> getOnDamageEntityConsumer() {
        return onDamageEntityConsumer;
    }

    public void setOnDamageEntityConsumer(Consumer<EntityDamageByEntityEvent> onDamageEntityConsumer) {
        this.onDamageEntityConsumer = onDamageEntityConsumer;
    }

    public Consumer<BlockBreakEvent> getBlockBreakConsumer() {
        return blockBreakConsumer;
    }

    public void setBlockBreakConsumer(Consumer<BlockBreakEvent> blockBreakConsumer) {
        this.blockBreakConsumer = blockBreakConsumer;
    }
}
