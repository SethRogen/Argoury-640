package com.runescape.link.network.handler;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.runescape.link.WorldServerSession;
import com.runescape.network.Frame;
import com.runescape.utility.Logging;

public class WSSGrandExchangeHandler extends WSSFrameHandler {
	
	private static final Logger logger = Logging.log();

	@Override
	public void handleFrame(WorldServerSession world, IoSession session, Frame frame) {
		// TODO Auto-generated method stub
        try {
            switch (frame.getOpcode()) {
                
            }
        } catch (Exception e) {
            logger.error("Error handling clan frame [" + frame.toString() + "]", e);
        }
	}

}
