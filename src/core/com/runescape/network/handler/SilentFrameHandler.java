package com.runescape.network.handler;

import org.apache.mina.core.session.IoSession;

import com.runescape.network.Frame;
import com.runescape.network.FrameHandler;

public class SilentFrameHandler implements FrameHandler {
    @Override
    public void handleFrame(IoSession session, Frame frame) {
    }
}
