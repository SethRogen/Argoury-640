package com.runescape.logic.input;

import com.runescape.logic.player.Player;

public interface PlayerInput {
    /**
     * Handles the input for the player.
     *
     * @param player the player
     * @param input the input (String or Integer)
     */
    void handle(Player player, Object input);
}
