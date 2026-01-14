package com.runescape.adapter.protocol.handler;

import com.runescape.Static;
import com.runescape.content.skills.SkillDialogue;
import com.runescape.logic.player.Player;
import com.runescape.network.Frame;
import com.runescape.network.handler.PlayerFrameHandler;
import com.runescape.utility.Logging;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class ButtonHandler extends PlayerFrameHandler {
	private static final Logger logger = Logging.log();
    private int opcode;

    public ButtonHandler(int opcode) {
        this.opcode = opcode;
    }

    @Override
    public void handleFrame(Player player, IoSession session, Frame frame) {
        int interfaceSet = frame.readInt();
        int interfaceId = interfaceSet >> 16;
        int b = interfaceSet & 0xff;
        int b2 = frame.readUnsignedShortA();
        int b3 = frame.readUnsignedShortA();
        if (b2 == 65535) {
            b2 = 0;
        }
        if (b3 == 65535) {
            b3 = 0;
        }
        
        switch(interfaceId) { 
        case 916: 
            if (player.itemSelection != null) {
				player.itemSelection.handleButton(player, b);
			} else {
				SkillDialogue.handleAmount(player, b);
			}
        	break;
        default:
            logger.debug("Unhandled interface [id=" + interfaceId + ", button=" + b + ", button2=" + b2 + "]");
            break;
        }
        if (!Static.ahs.handleButton(player, opcode, interfaceId, b, b2, b3)) {
            Static.callScript("buttons.handleButton", player, opcode, interfaceId, b, b2, b3);
        }
        // player.preventFrameSpam(frame.getOpcode());
    }
}
