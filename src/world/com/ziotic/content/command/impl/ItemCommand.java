package com.ziotic.content.command.impl;

import com.ziotic.content.command.CommandHandle;
import com.ziotic.content.command.CommandManager;
import com.ziotic.logic.item.Item;
import com.ziotic.logic.item.ItemDefinition;
import com.ziotic.logic.player.Player;

public class ItemCommand implements CommandHandle {
	
    @Override
    public String getName() { return "item"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
      //int id = Integer.parseInt(args[0]);
        //int amount = (args.length > 1) ? Integer.parseInt(args[1]) : 1;
        //player.getInventory().add(id, amount);
        try {
            int itemId = Integer.parseInt(args[0]);
            ItemDefinition def = ItemDefinition.forId(itemId);

            if (def == null) {
                player.sendMessage("Invalid item ID: " + itemId);
                return;
            }

            int lendId = def.getLendId();
            player.sendMessage("Lend ID for item " + itemId + " (" + def.name + "): " + lendId);
            player.getInventory().add(lendId, 1);
        } catch (NumberFormatException e) {
            player.sendMessage("Invalid item ID format.");
        }
    }
}