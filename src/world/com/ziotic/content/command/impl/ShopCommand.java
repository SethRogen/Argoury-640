package com.ziotic.content.command.impl;

import com.ziotic.content.command.CommandHandle;
import com.ziotic.content.command.CommandManager;
import com.ziotic.content.shop.ShopManager;
import com.ziotic.logic.player.Player;

public class ShopCommand implements CommandHandle {
	
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

