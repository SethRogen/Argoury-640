package com.runescape.logic.player.commands.impl;

import com.runescape.logic.item.Item;
import com.runescape.logic.item.ItemDefinition;
import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class ItemCommand implements CommandHandler {
	
    @Override
    public String getName() { return "item"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
        int id = Integer.parseInt(args[0]);
        int amount = (args.length > 1) ? Integer.parseInt(args[1]) : 1;
        player.getInventory().add(id, amount);
      /*  try {
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
        }*/
    }
}