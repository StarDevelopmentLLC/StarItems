package com.stardevllc.staritems.model;

import com.stardevllc.minecraft.itembuilder.ItemBuilder;
import com.stardevllc.starcore.ItemBuilders;
import com.stardevllc.starlib.objects.key.*;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class CustomItem implements Keyable {
    public static Function<String, String> stripColorFunction = ChatColor::stripColor;
    
    public static final String NBT_KEY = "staritemskey";
    
    protected JavaPlugin plugin;
    protected Key key;
    protected ItemBuilder<?, ?> itemBuilder;
    
    protected Set<EventHandler<? extends Event>> eventHandlers = new HashSet<>();
    
    protected Consumer<Player> whileInInventoryConsumer;
    protected Consumer<Player> whileOnHotbarConsumer;
    protected Consumer<Player> whileWearingConsumer;
    protected Consumer<Player> whileHoldingConsumer;
    
    public CustomItem(JavaPlugin plugin, ItemBuilder<?, ?> itemBuilder) {
        this.plugin = plugin;
        this.itemBuilder = itemBuilder;
    }
    
    public CustomItem(JavaPlugin plugin, ItemStack itemStack) {
        this.plugin = plugin;
        this.itemBuilder = ItemBuilders.of(itemStack);
    }
    
    public <T extends Event> void addEventHandler(Class<T> eventType, EventHandler<T> listener) {
        eventHandlers.add(listener);
    }
    
    public <T extends Event> void handleEvent(T event) {
        for (EventHandler<?> eventHandler : this.eventHandlers) {
            try {
                ((EventHandler<T>) eventHandler).onEvent(event);
            } catch (Throwable throwable) {}
        }
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
            nbt.setString(NBT_KEY, this.key.toString());
        });
        return itemStack;
    }
    
    @Override
    public Key getKey() {
        return key;
    }
    
    @Override
    public void setKey(Key key) {
        this.key = key;
    }
    
    @Override
    public boolean supportsSettingKey() {
        return true;
    }
}
