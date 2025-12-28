package com.runescape.logic.player.commands.impl;

import com.runescape.Static;
import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class SendStringCommand implements CommandHandler {
	
    @Override
    public String getName() { return "sendString"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
        int interfaceId = Integer.parseInt(args[0]);
        int max = Integer.parseInt(args[1]);
        String text = args[2];
            Static.proto.sendString(player, interfaceId, max, text);
    }
}
