package com.runescape.link;

import com.runescape.utility.Initiator;

public class WCSInitiator implements Initiator<WorldClientSession> {
    @Override
    public void init(WorldClientSession session) throws Exception {
        session.init();
    }
}
