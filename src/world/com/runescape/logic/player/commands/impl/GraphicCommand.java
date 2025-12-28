package com.runescape.logic.player.commands.impl;

import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class GraphicCommand implements CommandHandler {
	
    @Override
    public String getName() { return "graphics"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
    	int id = Integer.parseInt(args[0]);
    	int delay = Integer.parseInt(args[1]);
    	int height = Integer.parseInt(args[2]);
    	
    	if(args != null) {
            if(args.length == 3) {
                player.doGraphics(id, delay, height);
            } else if(args.length == 2) {
                player.doGraphics(id, delay);
            } else {
                player.doGraphics(id);
            }
        }
    }

}