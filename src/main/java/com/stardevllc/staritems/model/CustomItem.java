package com.stardevllc.staritems.model;

import com.stardevllc.starcore.StarColors;
import com.stardevllc.starcore.base.itembuilder.ItemBuilder;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class CustomItem {
    protected JavaPlugin plugin;
    protected String name;
    protected ItemBuilder itemBuilder;
    
    protected Set<EventHandler<? extends Event>> eventHandlers = new HashSet<>();
    
    protected Consumer<Player> whileInInventoryConsumer;
    protected Consumer<Player> whileOnHotbarConsumer;
    protected Consumer<Player> whileWearingConsumer;
    protected Consumer<Player> whileHoldingConsumer;
    
    public CustomItem(JavaPlugin plugin, String name, ItemBuilder itemBuilder) {
        this.plugin = plugin;
        this.name = StarColors.stripColor(name.toLowerCase().replace(" ", "_"));
        this.itemBuilder = itemBuilder;
    }
    
    public CustomItem(JavaPlugin plugin, ItemStack itemStack) {
        this.plugin = plugin;
        this.itemBuilder = ItemBuilder.fromItemStack(itemStack);
        this.name = NBT.get(itemStack, nbt -> {
            return nbt.getString("staritemsid");
        });
    }
    
    public <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? extends T> listener) {
        eventHandlers.add(listener);
    }
    
    public <T extends Event> void handleEvent(T event) {
        for (EventHandler eventHandler : this.eventHandlers) {
            try {
                eventHandler.onEvent(event);
            } catch (Throwable throwable) {
            }
        }
    }
    
    public String getName() {
        return name;
    }
    
    public Consumer<Player> getWhileInInventoryConsumer() {
        return whileInInventoryConsumer;
    }
    
    public CustomItem setWhileInInventoryConsumer(Consumer<Player> whileInInventoryConsumer) {
        this.whileInInventoryConsumer = whileInInventoryConsumer;
        return this;
    }
    
    public Consumer<Player> getWhileOnHotbarConsumer() {
        return whileOnHotbarConsumer;
    }
    
    public CustomItem setWhileOnHotbarConsumer(Consumer<Player> whileOnHotbarConsumer) {
        this.whileOnHotbarConsumer = whileOnHotbarConsumer;
        return this;
    }
    
    public Consumer<Player> getWhileWearingConsumer() {
        return whileWearingConsumer;
    }
    
    public CustomItem setWhileWearingConsumer(Consumer<Player> whileWearingConsumer) {
        this.whileWearingConsumer = whileWearingConsumer;
        return this;
    }
    
    public Consumer<Player> getWhileHoldingConsumer() {
        return whileHoldingConsumer;
    }
    
    public CustomItem setWhileHoldingConsumer(Consumer<Player> whileHoldingConsumer) {
        this.whileHoldingConsumer = whileHoldingConsumer;
        return this;
    }
    
    public JavaPlugin getPlugin() {
        return plugin;
    }
    
    public ItemStack toItemStack() {
        ItemStack itemStack = this.itemBuilder.build();
        NBT.modify(itemStack, nbt -> {
            nbt.setString("staritemsid", this.name);
        });
        return itemStack;
    }
}
