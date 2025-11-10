package com.ziotic.content.command.impl;

import com.ziotic.content.command.CommandHandle;
import com.ziotic.content.command.CommandManager;
import com.ziotic.logic.interfaces.impl.PriceCheckListener;
import com.ziotic.logic.player.Player;

public class TestCommand implements CommandHandle {
	
    @Override
    public String getName() { return "test"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
    	PriceCheckListener listener = new PriceCheckListener(player);
    	listener.PriceCheckOpen(player);
    	//GrandExchange.GrandExchangeOpen(player);
    	//Static.proto.sendInterface(player, new GameInterface(201));
    	//Static.proto.sendAccessMask(player, -1, 28 * 8, 201, 15, 0, 2);
    	//VarBitDefiniton.sendVarBit(player, 5903, 3);
    }
}