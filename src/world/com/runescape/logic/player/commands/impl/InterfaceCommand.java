package com.runescape.logic.player.commands.impl;

import com.runescape.Static;
import com.runescape.content.shop.ShopManager;
import com.runescape.logic.interfaces.GameInterface;
import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.CommandHandler;
import com.runescape.logic.player.commands.CommandManager;

public class InterfaceCommand implements CommandHandler {

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
