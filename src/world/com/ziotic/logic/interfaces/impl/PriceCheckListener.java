package com.ziotic.logic.interfaces.impl;

import com.ziotic.Static;
import com.ziotic.logic.item.ItemContainer;
import com.ziotic.logic.item.ItemPriceManager;
import com.ziotic.logic.item.PossesedItem;
import com.ziotic.logic.player.DisplayMode;
import com.ziotic.logic.player.Player;
import com.ziotic.logic.utility.GameInterface;

/**
 * @author Seth Rogen
 * 
 */

public class PriceCheckListener {
	
	private Player player;
	private static ItemContainer PriceCheckInv = null;
	
	public PriceCheckListener(Player player) { 
		this.player = player; 
		PriceCheckInv = new ItemContainer(player, 28);
	}
	
    public void sendOptions() {
    	Static.proto.sendAccessMask(player, -1, 54, 206, 18, 0, 2);
    	Static.proto.sendAccessMask(player, -1, 0, 207, 0, 0, 2);
    	Static.proto.sendInterfaceScript(player, 207, 0, 93, 4, 7,"Add", "Add-5", "Add-10", "Add-All", "Add-X", "Examine");
    }
	
	public void PriceCheckOpen(Player player) { 
		Static.proto.sendInterface(player, new GameInterface(206));
    	//Static.proto.sendInterfaceScript(player, 207, 0, 93, 4, 7,"Add", "Add-5", "Add-10", "Add-All", "Add-X", "Examine");
		 if (player.getDisplayMode() == DisplayMode.FIXED) {
             Static.proto.sendInterface(player, 207, 548, 197, false); // inventory interface
         } else { // might be different ids for fixed and resize
             Static.proto.sendInterface(player, 207, 746, 84, false); // inventory interface
         }
		 PossesedItem[] items = new PossesedItem[PriceCheckInv.size()];
		    for (int i = 0; i < PriceCheckInv.size(); i++) {
		        items[i] = PriceCheckInv.get(i); // get each item from the container
		    }
		    Static.proto.sendItems(player, 90, false, items);
		Static.proto.sendItems(player, 90, false, items);
	    Static.proto.sendAccessMask(player, -1, 54, 206, 18, 0, 2);
	    Static.proto.sendAccessMask(player, -1, 0, 207, 0, 0, 2);
	    Static.proto.sendInterfaceScript(player, 207, 0, 0, 162, 250,"Add", "Add-5", "Add-10", "Add-All", "Add-X", "Examine");
	    
		 Static.proto.sendInterfaceConfig(player, 728, 0);
		for (int i = 0; i < PriceCheckInv.size(); i++) {
			Static.proto.sendInterfaceConfig(player, 700 + i, 0);
		}
		
		//TODO ADD REMOVE WHEN CLOSED 
	}
	
	
    public int getSlotId(int clickSlotId) { return clickSlotId / 2; }
    
    public void addItem(int slot, int amount) {
        PossesedItem item = player.getInventory().get(slot);
        if (item == null) return;
        //TODO ADD SO U CAN'T ADD NON TRADEABLES 
        int maxAmount = player.getInventory().amount(item.getId());
        if (amount > maxAmount) amount = maxAmount;
        PossesedItem toAdd = new PossesedItem(item.getId(), amount);
        PriceCheckInv.add(toAdd);
        player.getInventory().remove(toAdd);
        refreshItems();
    }
    
    public void refreshItems() {
        int totalPrice = 0;
        for (int i = 0; i < PriceCheckInv.size(); i++) {
            PossesedItem item = PriceCheckInv.get(i);
            int price = 0;
            if (item != null) price =ItemPriceManager.getPrice(item.getId()) * item.getAmount();
            totalPrice += price;
            Static.proto.sendInterfaceConfig(player, 700 + i, price); // update price per slot
        }
        Static.proto.sendInterfaceConfig(player, 728, totalPrice); // update total
    }
	
	
    public void sendInterItems() {
    	//player.getPackets().sendItems(90, pcInv);
    	//Static.proto.sendItems(player, 90, false, PriceCheckInv);
    }
	

}
