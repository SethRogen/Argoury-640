package com.runescape.io.sql;

import com.runescape.utility.Configuration;
import com.runescape.utility.Initiator;

public class SQLInitiator implements Initiator<SQLSession> {
    private Configuration cfg;

    public SQLInitiator(Configuration cfg) {
        this.cfg = cfg;
    }

    @Override
    public void init(SQLSession session) throws Exception {
        session.init(cfg);
    }
}
