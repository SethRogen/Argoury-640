package com.ziotic.content.command.impl;

import com.ziotic.content.command.CommandHandle;
import com.ziotic.content.command.CommandManager;
import com.ziotic.logic.map.Tile;
import com.ziotic.logic.player.Player;

public class TeleportCommand implements CommandHandle {
	
    @Override
    public String getName() { return "tele"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
    	int x = java.lang.Integer.parseInt(args[0]);
    	int y = java.lang.Integer.parseInt(args[1]);
    	int z = player.getZ();
    	if(args.length > 2) {
    		z = java.lang.Integer.parseInt(args[2]);
    	}
    	player.setTeleportDestination(Tile.locate(x, y, z));
    }
}