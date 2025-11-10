package com.ziotic.content.command.impl;

import com.ziotic.content.command.CommandHandle;
import com.ziotic.content.command.CommandManager;
import com.ziotic.content.skill.SkillingInterface;
import com.ziotic.logic.player.Player;

public class SkillingInterfaceCommand implements CommandHandle {
	
    @Override
    public String getName() { return "interskilling"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
    	SkillingInterface.Open(player, SkillingInterface.COOK, "Choose how many you would like to cook, <br> then click on the item to begin", new int[] { 1050 }, 1, "Santa Hat", null); 
    }
}