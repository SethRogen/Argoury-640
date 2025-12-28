package com.runescape.logic.player.commands.impl;

import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class MasterSkillerCommand implements CommandHandler {
	
    @Override
    public String getName() { return "skiller"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
        for (int i = 7; i < 25; i++) {
            if (i == player.getLevels().SUMMONING) continue;
            player.getLevels().addXP(i, 200_000_000);
        }
    }
}