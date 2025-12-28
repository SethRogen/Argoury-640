package com.runescape.adapter.protocol.handler;

import com.runescape.logic.item.Item;
import com.runescape.logic.item.PossesedItem;
import com.runescape.logic.player.Player;
import com.runescape.network.Frame;
import com.runescape.network.handler.PlayerFrameHandler;
import com.runescape.utility.Logging;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class ItemSwitchHandler extends PlayerFrameHandler {

    private static final Logger logger = Logging.log();

    @Override
    public void handleFrame(Player player, IoSession session, Frame frame) {
        int interfaceSet1 = frame.readInt();
        int id1 = frame.readShortA();
        int interfaceSet2 = frame.readInt();
        int indexFrom = frame.readLEShort();
        int indexTo = frame.readLEShort();
        int id2 = frame.readLEShort();
        int interfaceId1 = interfaceSet1 >> 16;
        int childId1 = interfaceSet1 & 0xffff;
        int interfaceId2 = interfaceSet2 >> 16;
        int childId2 = interfaceSet2 & 0xffff;

        if (!handleSwitch(player, interfaceId1, childId1, interfaceId2, childId2, id1, id2, indexFrom, indexTo)) {
            unhandledSwitch(player, interfaceId1, childId1, interfaceId2, childId2, id1, id2, indexFrom, indexTo);
        }
        if (interfaceId1 != interfaceId2) {
            logger.warn("Item switch performed with different interfaces! Check interface IDs.");
        }
    }

    private boolean handleSwitch(Player player, int interfaceId1, int childId1, int interfaceId2, int childId2, int id1, int id2, int indexFrom, int indexTo) {
        switch (interfaceId1) {
            case 149:
                indexTo -= 28;
            case 763:
                PossesedItem pItem1 = player.getInventory().get(indexFrom);
                PossesedItem pItem2 = player.getInventory().get(indexTo);
                if (pItem1 == null) {
                    return true;
                }
                player.getInventory().set(pItem1, indexTo);
                player.getInventory().set(pItem2, indexFrom);
                if (interfaceId1 != 149) {
                    player.getInventory().refresh();
                }
                return true;
            default:
                return false;
        }
    }

    private void unhandledSwitch(Player player, int interfaceId1, int childId1, int interfaceId2, int childId2, int id1, int id2, int indexFrom, int indexTo) {
        logger.debug("Unhandled item switch [inter1=" + interfaceId1 + ", child1=" + childId1 + ", inter2=" + interfaceId2 + ", child2=" + childId2 + ", id1=" + id1 + ", id2=" + id2 +
                ", from=" + indexFrom + ", to=" + indexTo + "]");
    }
}
