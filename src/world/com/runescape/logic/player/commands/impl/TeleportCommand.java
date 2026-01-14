package com.runescape.logic.player.commands.impl;

import com.runescape.logic.map.Tile;
import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class TeleportCommand implements CommandHandler {
	
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