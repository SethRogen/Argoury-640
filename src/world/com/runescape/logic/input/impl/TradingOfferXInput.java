package com.runescape.logic.input.impl;

import com.runescape.logic.input.PlayerInput;
import com.runescape.logic.player.Player;

public class TradingOfferXInput implements PlayerInput {

    @Override
    public void handle(Player player, Object input) {
        if (input instanceof Integer) {
            int amount = (Integer) input;
            int index = player.getAttributes().getInt("offerXIndex");
            player.getTradingManager().offerItem(player, amount, index);
        }
    }
}
