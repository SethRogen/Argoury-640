package com.runescape.logic.object;

import com.runescape.logic.object.GameObject;
import com.runescape.logic.player.Player;

public interface ObjectHandler {
    boolean handleOption1(Player player, GameObject object);
    boolean handleOption2(Player player, GameObject object);
    boolean handleOption3(Player player, GameObject object);
}
