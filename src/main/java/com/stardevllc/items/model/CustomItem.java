package com.stardevllc.items.model;

import com.stardevllc.starcore.color.ColorUtils;
import com.stardevllc.starcore.item.ItemBuilder;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public class CustomItem {
    protected JavaPlugin plugin;
    protected String name;
    protected ItemBuilder itemBuilder;

    protected Consumer<PlayerInteractEvent> interactConsumer; //Handled
    protected Consumer<Player> whileInInventoryConsumer; //Handled
    protected Consumer<Player> whileOnHotbarConsumer; //Handled
    protected Consumer<Player> whileWearingConsumer; //Handled
    protected Consumer<Player> whileHoldingConsumer; //Handled
    
    protected Consumer<EntityDamageByEntityEvent> onDamageEntityConsumer; //Handled
    protected Consumer<BlockBreakEvent> blockBreakConsumer; //Handled
    protected Consumer<EntityDeathEvent> entityDeathConsumer; //Handled
    
    public CustomItem(JavaPlugin plugin, String name, ItemBuilder itemBuilder) {
        this.plugin = plugin;
        this.name = ColorUtils.stripColor(name.toLowerCase().replace(" ", "_"));
        this.itemBuilder = itemBuilder;
    }
    
    public CustomItem(JavaPlugin plugin, ItemStack itemStack) {
        this.plugin = plugin;
        this.itemBuilder = ItemBuilder.fromItemStack(itemStack);
        this.name = NBT.get(itemStack, nbt -> {
            return nbt.getString("staritemsid");
        });
    }

    public String getName() {
        return name;
    }

    public Consumer<PlayerInteractEvent> getInteractConsumer() {
        return interactConsumer;
    }

    public CustomItem setInteractConsumer(Consumer<PlayerInteractEvent> interactConsumer) {
        this.interactConsumer = interactConsumer;
        return this;
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

    public Consumer<EntityDamageByEntityEvent> getOnDamageEntityConsumer() {
        return onDamageEntityConsumer;
    }

    public CustomItem setOnDamageEntityConsumer(Consumer<EntityDamageByEntityEvent> onDamageEntityConsumer) {
        this.onDamageEntityConsumer = onDamageEntityConsumer;
        return this;
    }

    public Consumer<BlockBreakEvent> getBlockBreakConsumer() {
        return blockBreakConsumer;
    }

    public CustomItem setBlockBreakConsumer(Consumer<BlockBreakEvent> blockBreakConsumer) {
        this.blockBreakConsumer = blockBreakConsumer;
        return this;
    }

    public Consumer<EntityDeathEvent> getEntityDeathConsumer() {
        return entityDeathConsumer;
    }

    public CustomItem setEntityDeathConsumer(Consumer<EntityDeathEvent> entityDeathConsumer) {
        this.entityDeathConsumer = entityDeathConsumer;
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
