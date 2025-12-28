package com.runescape.logic.dialogue;

public interface Dialogue {
    /**
     * Gets the text in this dialogue.
     *
     * @return The text in this dialogue.
     */
    public String[] text(Conversation conversation);
}
