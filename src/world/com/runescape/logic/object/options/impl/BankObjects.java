package com.runescape.logic.object.options.impl;

import com.runescape.Static;
import com.runescape.logic.interfaces.GameInterfaces;
import com.runescape.logic.object.GameObject;
import com.runescape.logic.object.ObjectHandler;
import com.runescape.logic.player.Player;

public class BankObjects implements ObjectHandler {

    @Override
    public boolean handleOption1(Player player, GameObject object) {
    	 Static.proto.sendInterface(player, GameInterfaces.BANK_SCREEN);
        return true;
    }

    @Override
    public boolean handleOption2(Player player, GameObject object) {
    	Static.proto.sendInterface(player, GameInterfaces.BANK_SCREEN);
        return true;
    }
    @Override
    public boolean handleOption3(Player player, GameObject object) {
    	System.out.println("Collect Box");
        return true;
    }
}
