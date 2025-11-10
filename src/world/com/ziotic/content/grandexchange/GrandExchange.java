package com.ziotic.content.grandexchange;

import org.apache.log4j.Logger;

import com.ziotic.Static;
import com.ziotic.content.handler.ActionHandlerSystem;
import com.ziotic.content.handler.ButtonHandler;
import com.ziotic.logic.player.Player;
import com.ziotic.logic.utility.GameInterface;
import com.ziotic.utility.Logging;

/**
 * `
 * 
 */

public class GrandExchange implements ButtonHandler {
	
	private static final Logger logger = Logging.log();
	
    /*
     * public static final int ITEMID_CONFIG = 1109, AMOUNT_CONFIG = 1110,
     * PRICE_PER_CONFIG = 1111, SLOT_CONFIG = 1112, TYPE_CONFIG = 1113;
     */
	
	public static void GrandExchangeOpen(Player player) { 
		Static.proto.sendVarp(player, 1109, -1); //ITEMID_CONFIG = 1109
		Static.proto.sendVarp(player, 1110, 0); //AMOUNT_CONFIG = 1110
		Static.proto.sendVarp(player, 1111, 0); //PRICE_PER_CONFIG = 1111
		Static.proto.sendVarp(player, 1112, -1); //SLOT_CONFIG = 1112
		Static.proto.sendVarp(player, 1113, -1); //TYPE_CONFIG = 1113
		Static.proto.sendVarp(player, 1114, 0); //SET MARKETPRICE_CONFIG = 1114
		Static.proto.sendVarp(player, 1115, 0);
		Static.proto.sendVarp(player, 1116, 0);
		Static.proto.sendVarp(player, 1118, 0);
        Static.proto.sendInterfaceVariable(player, 199, -1);
        if (player.isMember()) { //disable slots if not member and active if memeber
        	
        }
		Static.proto.sendInterface(player, new GameInterface(105));
		logger.debug("Grand Exchange [open]");
		
	}
	
	
	public static void BuyOffer(Player player) { 
		Static.proto.sendVarp(player, 1109, -1);
		Static.proto.sendVarp(player, 1110, 0);
		Static.proto.sendVarp(player, 1111, 0);
		Static.proto.sendVarp(player, 1112, 0); //slot
		Static.proto.sendVarp(player, 1113, 0); // buy
		Static.proto.sendVarp(player, 1114, 0);
		Static.proto.sendVarp(player, 1115, 0);
		Static.proto.sendVarp(player, 1116, 0);
        Static.proto.sendInterface(player, 449, 548, 197, false);
        Static.proto.sendInterfaceVariable(player, 1241, 16750848);
        Static.proto.sendInterfaceVariable(player, 1242, 15439903);
        Static.proto.sendInterfaceVariable(player, 741, -1);
        Static.proto.sendInterfaceVariable(player, 743, -1);
        Static.proto.sendInterfaceVariable(player, 744, 0);
        Static.proto.sendInterface(player, 389, 752, 7, true);
        Static.proto.sendInterfaceScript(player, 570, "Grand Exchange Item Search");
	}
	
	
    public static int getPrice(int itemId) {
		return itemId;
    }
    
	/**
	 * 
	 * @param Create Offer
	 * @String PlayerName
	 * @INT ItemID
	 * @String Type
	 * @INT Price 
	 * @INT Qauntity
	 */
	
	public void CreateOffer(Player player) { 
		Static.currentLink().getDBLoader().createOffer("", 4151, "", 100, 1);
	}


	@Override
	public void load(ActionHandlerSystem system) throws Exception {
		system.registerButtonHandler(new int[]{105}, this);
		
	}


	@Override
	public boolean explicitlyForMembers() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void handleButton(Player player, int opcode, int interfaceId, int b, int b2, int b3) {
		Static.callScript("buttons.handleButton", player, opcode, interfaceId, b, b2, b3);
		switch(interfaceId) { 
		case 105: 
			switch(b) { 
				case 31: //Buy slot 1
					BuyOffer(player);
				break;
				 default:
                     logger.debug("Grand Exchange - Unhandled button [interface=" + interfaceId + ", button=" + b + ", button2=" + b2 + ", button3=" + b3 + ", opcode=" + opcode + "]");
                     break;
			}
			break;
		}
		
	}

}
