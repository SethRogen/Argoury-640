package com.runescape.logic.player.commands.impl;

import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class AnimationCommand implements CommandHandler {
	
    @Override
    public String getName() { return "anim"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
    	int id = Integer.parseInt(args[0]);
    	int delay = Integer.parseInt(args[1]);
    	
    	 if(args != null) {
             if(args.length == 2) {
                 player.doAnimation(id, delay);
             } else {
                 player.doAnimation(id);
             }
         }
    }

}