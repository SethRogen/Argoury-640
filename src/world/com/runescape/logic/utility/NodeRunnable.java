package com.runescape.logic.utility;

import com.runescape.logic.Node;

public interface NodeRunnable<T extends Node> {
    public void run(T node);
}
