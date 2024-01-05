package com.stardevllc.staritems;

import com.stardevllc.starmclib.item.ItemBuilder;
import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class CustomItem {
    private ItemKey key;
    private ItemBuilder itemBuilder;
    private Consumer<PlayerInteractEvent> interactConsumer;

    public CustomItem(String namespace, String id, ItemBuilder itemBuilder) {
        this(new ItemKey(namespace, id), itemBuilder);
    }
    
    public CustomItem(String namespace, String id, ItemBuilder itemBuilder, Consumer<PlayerInteractEvent> interactConsumer) {
        this(new ItemKey(namespace, id), itemBuilder, interactConsumer);
    }
    
    public CustomItem(ItemKey key, ItemBuilder itemBuilder) {
        this(key, itemBuilder, null);
    }

    public CustomItem(ItemKey key, ItemBuilder itemBuilder, Consumer<PlayerInteractEvent> interactConsumer) {
        this.key = key;
        this.itemBuilder = itemBuilder;
        this.interactConsumer = interactConsumer;
    }
    
    public void saveKeyToStack(ItemStack itemStack) {
        NBT.modify(itemStack, nbt -> {
            ReadWriteNBT staritemsCompound = nbt.getOrCreateCompound("staritems");
            staritemsCompound.setString(this.key.getNamespace(), this.key.getKey());
        });
    }

    public ItemStack toItemStack() {
        return toItemStack(1);
    }
    
    public ItemStack toItemStack(int amount) {
        ItemStack stack = itemBuilder.build();
        stack.setAmount(amount);
        saveKeyToStack(stack);
        applyCustomNBT(stack);
        return stack;
    }
    
    public void applyCustomNBT(ItemStack itemStack) {
        
    }

    public void setInteractConsumer(Consumer<PlayerInteractEvent> interactConsumer) {
        this.interactConsumer = interactConsumer;
    }

    public ItemKey getKey() {
        return key;
    }

    public ItemBuilder getItemBuilder() {
        return itemBuilder;
    }

    public Consumer<PlayerInteractEvent> getInteractConsumer() {
        return interactConsumer;
    }
}
