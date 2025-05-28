package com.stardevllc.staritems.cmd;

import com.stardevllc.starcore.StarColors;
import com.stardevllc.staritems.StarItems;
import com.stardevllc.staritems.model.CustomItem;
import com.stardevllc.staritems.model.ItemRegistry;
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
            StarColors.coloredMessage(sender, "&cYou don't have permission to use this command!");
            return true;
        }
        
        if (!(args.length > 0)) {
            StarColors.coloredMessage(sender, "&cUsage: /staritems list");
            StarColors.coloredMessage(sender, "&cUsage: /staritems give <name> [amount]");
            return true;
        }
        
        if (args[0].equalsIgnoreCase("list")) {
            if (!sender.hasPermission("staritems.admin.list")) {
                StarColors.coloredMessage(sender, "&cYou don't have permission to use this command!");
                return true;
            }
            ItemRegistry registry = plugin.getItemRegistry();
            
            if (registry.getObjects().isEmpty()) {
                StarColors.coloredMessage(sender, "&cThere are no items registered to StarItems.");
                return true;
            }

            StarColors.coloredMessage(sender, "&aList of registered items.");
            for (CustomItem customItem : registry) {
                StarColors.coloredMessage(sender, "  &8- &a" + customItem.getName() + " &7[&d" + customItem.getPlugin() + "&7]");
            }
            return true;
        } else if (args[0].equalsIgnoreCase("give")) {
            if (!sender.hasPermission("staritems.admin.give")) {
                StarColors.coloredMessage(sender, "&cYou don't have permission to use this command!");
                return true;
            }
            
            if (!(sender instanceof Player player)) {
                StarColors.coloredMessage(sender, "&cOnly players can use that command.");
                return true;
            }
            
            if (!(args.length > 1)) {
                StarColors.coloredMessage(sender, "&cUsage: /staritems give <name> [amount]");
                return true;
            }
            
            CustomItem customItem = plugin.getItemRegistry().get(args[1]);
            if (customItem == null) {
                StarColors.coloredMessage(sender, "&cYou provided an invalid item id.");
                return true;
            }

            ItemStack itemStack = customItem.toItemStack();
            
            if (args.length > 2) {
                try {
                    int amount = Integer.parseInt(args[2]);
                    itemStack.setAmount(amount);
                } catch (NumberFormatException e) {
                    StarColors.coloredMessage(player, "You provided an invalid integer for the amount.");
                    return true;
                }
            }
            
            player.getInventory().addItem(customItem.toItemStack());
            StarColors.coloredMessage(player, "&eYou gave yourself &b" + itemStack.getAmount() + " &eof the custom item &b" + customItem.getName());
        }
        
        return true;
    }
}
