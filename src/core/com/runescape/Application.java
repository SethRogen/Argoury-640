package com.runescape;

public interface Application {
    public static enum AppType {
        GAME, LOBBY, LINK, GAME_AND_LOBBY
    }

    public void main(String[] args) throws Throwable;
}
