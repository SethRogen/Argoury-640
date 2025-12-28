package com.runescape.logic.utility;

import com.runescape.logic.player.Player;
import com.runescape.network.Frame;

public interface PlayerUpdater extends EntityUpdater<Player> {
    public Frame doApperanceBlock(Player player);

    public Player[] getPlayers();

    public int[] getPlayerLocations();
}
