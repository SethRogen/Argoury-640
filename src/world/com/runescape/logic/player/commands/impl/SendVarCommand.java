package com.runescape.logic.player.commands.impl;

import com.runescape.Static;
import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class SendVarCommand implements CommandHandler {
	
    @Override
    public String getName() { return "sendvar"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
        int id = Integer.parseInt(args[0]);
        int val = Integer.parseInt(args[1]);
        
        Static.proto.sendInterfaceConfig(player, id, val);
    }
}
