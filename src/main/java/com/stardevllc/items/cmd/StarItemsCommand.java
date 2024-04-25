package com.stardevllc.items.cmd;

import com.stardevllc.items.StarItems;
import com.stardevllc.items.model.CustomItem;
import com.stardevllc.items.model.ItemRegistry;
import com.stardevllc.starcore.color.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StarItemsCommand implements CommandExecutor {
    
    private StarItems plugin;

    public StarItemsCommand(StarItems plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("staritems.admin")) {
            ColorUtils.coloredMessage(sender, "&cYou don't have permission to use this command!");
            return true;
        }
        
        if (!(args.length > 0)) {
            ColorUtils.coloredMessage(sender, "&cUsage: /staritems list");
            return true;
        }
        
        if (args[0].equalsIgnoreCase("list")) {
            ItemRegistry registry = plugin.getItemRegistry();
            
            if (registry.getObjects().isEmpty()) {
                ColorUtils.coloredMessage(sender, "&cThere are no items registered to StarItems.");
                return true;
            }
            
            ColorUtils.coloredMessage(sender, "&aList of registered items.");
            for (CustomItem customItem : registry) {
                ColorUtils.coloredMessage(sender, "  &8- &a" + customItem.getName() + " &7[&d" + customItem.getPlugin() + "&7]");
                return true;
            }
        }
        
        return true;
    }
}
