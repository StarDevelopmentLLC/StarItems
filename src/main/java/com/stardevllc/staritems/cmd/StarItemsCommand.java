package com.stardevllc.staritems.cmd;

import com.stardevllc.staritems.StarItems;
import com.stardevllc.staritems.model.CustomItem;
import com.stardevllc.staritems.model.ItemRegistry;
import com.stardevllc.starlib.dependency.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StarItemsCommand implements CommandExecutor {
    
    @Inject
    private StarItems plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("staritems.admin")) {
            plugin.getColors().coloredLegacy(sender, "&cYou don't have permission to use this command!");
            return true;
        }
        
        if (!(args.length > 0)) {
            plugin.getColors().coloredLegacy(sender, "&cUsage: /staritems list");
            plugin.getColors().coloredLegacy(sender, "&cUsage: /staritems give <name> [amount]");
            return true;
        }
        
        if (args[0].equalsIgnoreCase("list")) {
            if (!sender.hasPermission("staritems.admin.list")) {
                plugin.getColors().coloredLegacy(sender, "&cYou don't have permission to use this command!");
                return true;
            }
            ItemRegistry registry = plugin.getItemRegistry();
            
            if (registry.getObjects().isEmpty()) {
                plugin.getColors().coloredLegacy(sender, "&cThere are no items registered to StarItems.");
                return true;
            }

            plugin.getColors().coloredLegacy(sender, "&aList of registered items.");
            for (CustomItem customItem : registry) {
                plugin.getColors().coloredLegacy(sender, "  &8- &a" + customItem.getName() + " &7[&d" + customItem.getPlugin() + "&7]");
            }
            return true;
        } else if (args[0].equalsIgnoreCase("give")) {
            if (!sender.hasPermission("staritems.admin.give")) {
                plugin.getColors().coloredLegacy(sender, "&cYou don't have permission to use this command!");
                return true;
            }
            
            if (!(sender instanceof Player player)) {
                plugin.getColors().coloredLegacy(sender, "&cOnly players can use that command.");
                return true;
            }
            
            if (!(args.length > 1)) {
                plugin.getColors().coloredLegacy(sender, "&cUsage: /staritems give <name> [amount]");
                return true;
            }
            
            CustomItem customItem = plugin.getItemRegistry().get(args[1]);
            if (customItem == null) {
                plugin.getColors().coloredLegacy(sender, "&cYou provided an invalid item id.");
                return true;
            }

            ItemStack itemStack = customItem.toItemStack();
            
            if (args.length > 2) {
                try {
                    int amount = Integer.parseInt(args[2]);
                    itemStack.setAmount(amount);
                } catch (NumberFormatException e) {
                    plugin.getColors().coloredLegacy(player, "You provided an invalid integer for the amount.");
                    return true;
                }
            }
            
            player.getInventory().addItem(customItem.toItemStack());
            plugin.getColors().coloredLegacy(player, "&eYou gave yourself &b" + itemStack.getAmount() + " &eof the custom item &b" + customItem.getName());
        }
        
        return true;
    }
}
