package com.runescape.logic.player.commands.impl;

import com.runescape.Static;
import com.runescape.logic.map.Tile;
import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class SendSoundCommand implements CommandHandler {
	
    @Override
    public String getName() { return "sound"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
    	int id = java.lang.Integer.parseInt(args[0]);
    	int delay = java.lang.Integer.parseInt(args[1]);
    	Static.proto.sendRSJingle(player, id, delay);
    }
}