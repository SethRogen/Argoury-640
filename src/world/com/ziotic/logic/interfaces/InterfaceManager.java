package com.ziotic.logic.interfaces;

import java.util.HashMap;
import java.util.Map;

import com.ziotic.logic.player.Player;



/**
 * @author Seth Rogen
 */

public class InterfaceManager {

    private static final Map<Integer, InterfaceHandler> handlers = new HashMap<>();

    static {
        //register(548, new InventoryInterface());
      //  register(387, new EquipmentInterface());
        // Add others here
    }

    private static void register(int interfaceId, InterfaceHandler handler) {
        handlers.put(interfaceId, handler);
    }

    public static boolean handleButton(Player player, int interfaceId, int buttonId) {
        InterfaceHandler handler = handlers.get(interfaceId);
        if (handler == null) return false;
        return handler.handleButton(player, interfaceId, buttonId);
    }
}