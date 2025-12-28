package com.runescape.utility;

public interface Initiator<T extends Poolable> {
    public void init(T object) throws Exception;
}
