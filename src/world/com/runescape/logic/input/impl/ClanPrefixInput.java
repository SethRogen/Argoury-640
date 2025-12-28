package com.runescape.logic.input.impl;

import com.runescape.content.clanchat.ClanManager;
import com.runescape.logic.input.PlayerInput;
import com.runescape.logic.player.Player;

public class ClanPrefixInput implements PlayerInput {

    @Override
    public void handle(Player player, Object input) {
        if (input instanceof String) {
            String prefix = (String) input;
            ClanManager.changeClanPrefix(player, prefix);
        }
    }
}
