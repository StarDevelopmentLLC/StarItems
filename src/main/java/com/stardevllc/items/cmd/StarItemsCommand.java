package com.stardevllc.items.cmd;

import com.stardevllc.items.StarItems;
import com.stardevllc.items.model.CustomItem;
import com.stardevllc.items.model.ItemRegistry;
import com.stardevllc.starcore.color.ColorHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StarItemsCommand implements CommandExecutor {
    
    private StarItems plugin;

    public StarItemsCommand(StarItems plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("staritems.admin")) {
            ColorHandler.getInstance().coloredMessage(sender, "&cYou don't have permission to use this command!");
            return true;
        }
        
        if (!(args.length > 0)) {
            ColorHandler.getInstance().coloredMessage(sender, "&cUsage: /staritems list");
            ColorHandler.getInstance().coloredMessage(sender, "&cUsage: /staritems give <name> [amount]");
            return true;
        }
        
        if (args[0].equalsIgnoreCase("list")) {
            if (!sender.hasPermission("staritems.admin.list")) {
                ColorHandler.getInstance().coloredMessage(sender, "&cYou don't have permission to use this command!");
                return true;
            }
            ItemRegistry registry = plugin.getItemRegistry();
            
            if (registry.getObjects().isEmpty()) {
                ColorHandler.getInstance().coloredMessage(sender, "&cThere are no items registered to StarItems.");
                return true;
            }

            ColorHandler.getInstance().coloredMessage(sender, "&aList of registered items.");
            for (CustomItem customItem : registry) {
                ColorHandler.getInstance().coloredMessage(sender, "  &8- &a" + customItem.getName() + " &7[&d" + customItem.getPlugin() + "&7]");
                return true;
            }
        } else if (args[0].equalsIgnoreCase("give")) {
            if (!sender.hasPermission("staritems.admin.give")) {
                ColorHandler.getInstance().coloredMessage(sender, "&cYou don't have permission to use this command!");
                return true;
            }
            
            if (!(sender instanceof Player player)) {
                ColorHandler.getInstance().coloredMessage(sender, "&cOnly players can use that command.");
                return true;
            }
            
            if (!(args.length > 1)) {
                ColorHandler.getInstance().coloredMessage(sender, "&cUsage: /staritems give <name> [amount]");
                return true;
            }
            
            CustomItem customItem = plugin.getItemRegistry().get(args[1]);
            if (customItem == null) {
                ColorHandler.getInstance().coloredMessage(sender, "&cYou provided an invalid item id.");
                return true;
            }

            ItemStack itemStack = customItem.toItemStack();
            
            if (args.length > 2) {
                try {
                    int amount = Integer.parseInt(args[2]);
                    itemStack.setAmount(amount);
                } catch (NumberFormatException e) {
                    ColorHandler.getInstance().coloredMessage(player, "You provided an invalid integer for the amount.");
                    return true;
                }
            }
            
            player.getInventory().addItem(customItem.toItemStack());
            ColorHandler.getInstance().coloredMessage(player, "&eYou gave yourself &b" + itemStack.getAmount() + " &eof the custom item &b" + customItem.getName());
        }
        
        return true;
    }
}
