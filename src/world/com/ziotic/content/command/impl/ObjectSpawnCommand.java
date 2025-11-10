package com.ziotic.content.command.impl;

import com.ziotic.Static;
import com.ziotic.content.command.CommandHandle;
import com.ziotic.content.command.CommandManager;
import com.ziotic.logic.object.ObjectDefinition;
import com.ziotic.logic.player.Player;

public class ObjectSpawnCommand implements CommandHandle {
	
    @Override
    public String getName() { return "obj"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
    	int id = Integer.parseInt(args[0]);
        int type = 0;
        int dir = Integer.parseInt(args[1]);
        String name = ObjectDefinition.forId(id).name;
        boolean solid = ObjectDefinition.forId(id).walkable;
        
        
        player.sendMessage(name);
        Static.world.getObjectManager().add(id, player.getLocation(), type, dir);
    }

}