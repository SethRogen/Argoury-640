package com.ziotic.content.command.impl;

import com.ziotic.Static;
import com.ziotic.content.command.CommandHandle;
import com.ziotic.content.command.CommandManager;
import com.ziotic.content.shop.ShopManager;
import com.ziotic.logic.player.Player;
import com.ziotic.logic.utility.GameInterface;

public class InterfaceCommand implements CommandHandle {

    @Override
    public String getName() { return "interface"; }

    @Override
    public boolean canUse(Player player) { return CommandManager.isAdmin(player); }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 0) { player.sendMessage("Usage: ::interface (id)"); return; }
        try {
            int id = Integer.parseInt(args[0]);
            Static.proto.sendCloseInterface(player);

            if (args.length == 1) {
                Static.proto.sendInterface(player, new GameInterface(id));
            } else {
                int loc = Integer.parseInt(args[1]);
                Static.proto.sendInterface(player, new GameInterface(id, new int[]{loc, loc}, null));
            }
        } catch (NumberFormatException e) {
            player.sendMessage("Invalid interface ID.");
        }
    }
}
