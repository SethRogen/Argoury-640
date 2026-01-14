package com.runescape.logic.player.commands.impl;

import com.runescape.content.shop.ShopManager;
import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class ShopCommand implements CommandHandler {
	
    @Override
    public String getName() { return "shop"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
        int id = Integer.parseInt(args[0]);
        ShopManager.openShop(player, id);
    }
}

