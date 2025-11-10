package com.ziotic.content.command;

import com.ziotic.logic.player.Player;

public interface CommandHandle {
    String getName();
    boolean canUse(Player player);
    void execute(Player player, String[] args);
}