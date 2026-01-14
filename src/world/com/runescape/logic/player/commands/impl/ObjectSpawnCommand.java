package com.runescape.logic.player.commands.impl;

import com.runescape.Static;
import com.runescape.logic.object.ObjectDefinition;
import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class ObjectSpawnCommand implements CommandHandler {
	
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