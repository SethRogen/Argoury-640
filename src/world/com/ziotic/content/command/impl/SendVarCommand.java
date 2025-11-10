package com.ziotic.content.command.impl;

import com.ziotic.Static;
import com.ziotic.content.command.CommandHandle;
import com.ziotic.content.command.CommandManager;
import com.ziotic.logic.player.Player;

public class SendVarCommand implements CommandHandle {
	
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
