package com.runescape.logic.input.impl;

import com.runescape.logic.input.PlayerInput;
import com.runescape.logic.interfaces.GameInterfaces;
import com.runescape.logic.item.BankManager;
import com.runescape.logic.player.Player;

public class BankDepositXInput implements PlayerInput {

    @Override
    public void handle(Player player, Object input) {
        if (player.getCurrentInterface() != GameInterfaces.BANK_SCREEN) return;
        if (input instanceof Integer) {
            int amount = (Integer) input;
            int index = player.getAttributes().getInt("depositXIndex");
            BankManager.depositItem(player, index, amount);
            player.getAttributes().set("depositX", amount);
        }
    }
}
