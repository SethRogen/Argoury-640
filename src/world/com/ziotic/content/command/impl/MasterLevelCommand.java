package com.ziotic.content.command.impl;

import com.ziotic.content.command.CommandHandle;
import com.ziotic.content.command.CommandManager;
import com.ziotic.logic.player.Player;

public class MasterLevelCommand implements CommandHandle {
	
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