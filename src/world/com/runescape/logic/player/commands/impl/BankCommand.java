package com.runescape.logic.player.commands.impl;

import com.runescape.Static;
import com.runescape.logic.interfaces.GameInterfaces;
import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class BankCommand implements CommandHandler {
	
    @Override
    public String getName() { return "bank"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
    	Static.proto.sendInterface(player, GameInterfaces.BANK_SCREEN);
    }
}

