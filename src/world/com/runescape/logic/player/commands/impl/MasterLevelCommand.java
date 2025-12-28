package com.runescape.logic.player.commands.impl;

import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class MasterLevelCommand implements CommandHandler {
	
    @Override
    public String getName() { return "master"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
    	 for(int i = 0; i < 25; i++) {
             player.getLevels().addXP(i, 200000000);
    	 }

    }
}