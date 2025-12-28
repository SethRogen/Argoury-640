package com.runescape.logic.player.commands;

import com.runescape.logic.player.Player;

public interface CommandHandler {
    String getName();
    boolean canUse(Player player);
    void execute(Player player, String[] args);
}