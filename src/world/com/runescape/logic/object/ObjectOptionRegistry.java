package com.runescape.logic.object;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.runescape.logic.object.options.impl.BankObjects;
import com.runescape.logic.object.options.impl.DepositBox;
import com.runescape.utility.Logging;

public class ObjectOptionRegistry {

    private static final Logger logger = Logging.log();
    private static final Map<Integer, ObjectHandler> handlers = new HashMap<>();

    static {
        // Deposit box objects
        int[] depositBoxIds = {25937, 9398};
        for (int id : depositBoxIds) {
            handlers.put(id, new DepositBox());
        }
        
        // Bank Objects
        int[] bankObjectIds = {2213, 11402};
        for (int id : bankObjectIds) {
            handlers.put(id, new BankObjects());
        }
    }

    public static ObjectHandler getHandler(int objectId, int option) {
        ObjectHandler handler = handlers.get(objectId);
        if (handler == null) {
            logger.warn("No options found for [ object ID = " + objectId + " ]");
        }
        return handler;
    }

    public static ObjectHandler getHandler(int objectId) {
        return getHandler(objectId, -1);
    }
}
