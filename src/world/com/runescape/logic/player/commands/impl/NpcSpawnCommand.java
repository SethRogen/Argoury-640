package com.runescape.logic.player.commands.impl;

import com.runescape.Static;
import com.runescape.logic.npc.NPC;
import com.runescape.logic.npc.NPCSpawn;
import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class NpcSpawnCommand implements CommandHandler {
	
    @Override
    public String getName() { return "npc"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
        Static.world.register(new NPC(new NPCSpawn(java.lang.Integer.parseInt(args[0]), player.getLocation(), null, 4, false)));
    }
}