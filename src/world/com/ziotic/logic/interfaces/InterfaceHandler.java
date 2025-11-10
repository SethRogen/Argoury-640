package com.ziotic.logic.interfaces;

import com.ziotic.logic.player.Player;

/**
 * @author Seth Rogen
 */

public interface InterfaceHandler {
    /**
     * Called when a button is clicked on an interface.
     */
    boolean handleButton(Player player, int interfaceId, int buttonId);

    /**
     * Called when the interface is opened.
     */
    default void onOpen(Player player) {}

    /**
     * Called when the interface is closed.
     */
    default void onClose(Player player) {}
}
