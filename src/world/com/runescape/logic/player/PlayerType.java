package com.runescape.logic.player;

public interface PlayerType {
    public String getName();

    public String getProtocolName();

    public int getWorld();

    public String getClanOwner();

    public boolean inGame();

    public boolean inLobby();
}
