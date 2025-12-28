package com.runescape.logic.object;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.runescape.logic.object.options.impl.DepositBox;
import com.runescape.utility.Logging;

public class ObjectOptionRegistry {

    private static final Logger logger = Logging.log();
    private static final Map<Integer, ObjectHandler> handlers = new HashMap<>();

    static {
    	//ObjectID, Option Classs
        handlers.put(25937, new DepositBox()); // Deposit box object ID
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
