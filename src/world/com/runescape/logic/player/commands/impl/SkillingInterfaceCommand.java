package com.runescape.logic.player.commands.impl;

import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class SkillingInterfaceCommand implements CommandHandler {
	
    @Override
    public String getName() { return "interskilling"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
    	//SkillingInterface.Open(player, SkillingInterface.COOK, "Choose how many you would like to cook, <br> then click on the item to begin", new int[] { 1050 }, 1, "Santa Hat", null); 
    }
}