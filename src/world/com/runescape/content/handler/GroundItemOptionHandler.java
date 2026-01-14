package com.runescape.content.handler;

import com.runescape.logic.item.GroundItem;
import com.runescape.logic.player.Player;

public interface GroundItemOptionHandler extends ActionHandler {
    void handleGroundItemOption(Player player, GroundItem item);
}
