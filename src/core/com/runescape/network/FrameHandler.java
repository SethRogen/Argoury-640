package com.runescape.network;

import org.apache.mina.core.session.IoSession;

public interface FrameHandler {
    public void handleFrame(IoSession session, Frame frame);
}
