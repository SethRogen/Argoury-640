package com.ziotic.content.command.impl;

import com.ziotic.Static;
import com.ziotic.content.command.CommandHandle;
import com.ziotic.content.command.CommandManager;
import com.ziotic.logic.player.Player;

public class SendStringCommand implements CommandHandle {
	
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
