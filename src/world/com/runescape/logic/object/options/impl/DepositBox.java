package com.runescape.logic.object.options.impl;

import com.runescape.logic.object.GameObject;
import com.runescape.logic.object.ObjectHandler;
import com.runescape.logic.player.Player;

public class DepositBox implements ObjectHandler {

    @Override
    public boolean handleOption1(Player player, GameObject object) {
        player.sendMessage("You use the deposit box (Option 1).");
        // Logic for click 1 (deposit coins/items)
        return true;
    }

    @Override
    public boolean handleOption2(Player player, GameObject object) {
        player.sendMessage("You use the deposit box (Option 2).");
        // Logic for click 2 (view contents, withdraw, etc.)
        return true;
    }
    
    @Override
    public boolean handleOption3(Player player, GameObject object) {
//TT
        return true;
    }
}
