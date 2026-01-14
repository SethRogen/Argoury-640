package com.runescape.content.transportation;

import com.runescape.content.handler.ActionHandlerSystem;
import com.runescape.content.handler.ItemOptionHandler;
import com.runescape.logic.item.PossesedItem;
import com.runescape.logic.player.Player;

public class TeleportTabs implements ItemOptionHandler {
	
	private int VARROCK_TELEPORT = 8007;

	@Override
	public void load(ActionHandlerSystem system) throws Exception {
		system.registerItemOptionHandler(new int[]{VARROCK_TELEPORT}, this);
		
	}

	@Override
	public boolean explicitlyForMembers() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleItemOption1(Player player, PossesedItem item, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleItemOption2(Player player, PossesedItem item, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleItemOption3(Player player, PossesedItem item, int index) {
		// TODO Auto-generated method stub
		
	}  
	
	
	private void LOCATION_VARROCK(Player player) { 
		
	}
	
	

}
