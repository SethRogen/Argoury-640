package com.ziotic.content.command.impl;

import com.ziotic.content.command.CommandHandle;
import com.ziotic.content.command.CommandManager;
import com.ziotic.logic.player.Player;

public class AnimationCommand implements CommandHandle {
	
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