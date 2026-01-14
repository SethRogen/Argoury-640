package com.runescape.logic.player.commands.impl;

import com.runescape.Static;
import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class TestCommand implements CommandHandler {
	
    @Override
    public String getName() { return "test"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
    	//Static.proto.sendMessage(player, "This is a test command line.");
        // if(player.itemSelection != null) {
 			player.itemSelection.close(player);
 		//}
    }
}