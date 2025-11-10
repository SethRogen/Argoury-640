package com.ziotic.content.command.impl;

import com.ziotic.Static;
import com.ziotic.content.command.CommandHandle;
import com.ziotic.content.command.CommandManager;
import com.ziotic.logic.npc.NPCSpawn;
import com.ziotic.logic.player.Player;
import com.ziotic.logic.npc.NPC;

public class NpcSpawnCommand implements CommandHandle {
	
    @Override
    public String getName() { return "npc"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
        Static.world.register(new NPC(new NPCSpawn(java.lang.Integer.parseInt(args[0]), player.getLocation(), null, 4, false)));
    }
}