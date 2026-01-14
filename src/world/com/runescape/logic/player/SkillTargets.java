package com.runescape.logic.player;

import com.runescape.content.handler.ActionHandlerSystem;
import com.runescape.content.handler.ButtonHandler;

public class SkillTargets implements ButtonHandler {
	
	private final int SKILL_TAB = 320; //Skill interface ID 
	
	private final int SET_LEVEL_TARGET = 2; //Opcode for Level Target
	private final int SET_XP_TARGET = 3; //Opcode for Xp Target
	private final int CLEAR_TARGET = 4; //Opcode to clear target
	
	public int getTargetIdByComponentId(int componentId) {
		int[] mappings = {200, 11, 52, 93, 28, 193, 76, 19, 36, 60, 84, 110, 186, 179, 44, 68, 172, 165, 101, 118, 126, 134, 142, 150, 158};
		for (int i = 0; i < mappings.length; i++) {
			if (mappings[i] == componentId) {
				return i;
			}
		}
		return -1;
	}

	public int getSkillIdByTargetId(int targetId) {
		int[] mappings = {Levels.ATTACK, Levels.STRENGTH, Levels.RANGE, Levels.MAGIC, Levels.DEFENCE, Levels.CONSTITUTION, Levels.PRAYER, Levels.AGILITY, Levels.HERBLORE, Levels.THIEVING, Levels.CRAFTING, Levels.RUNECRAFTING, Levels.MINING, Levels.SMITHING, Levels.FISHING, Levels.COOKING, Levels.FIREMAKING, Levels.WOODCUTTING, Levels.FLETCHING, Levels.SLAYER, Levels.FARMING, Levels.CONSTRUCTION, Levels.HUNTER, Levels.SUMMONING, Levels.DUNGEONNEERING};
		if (targetId >= 0 && targetId < mappings.length) {
			return mappings[targetId];
		} else {
			return -1;
		}
	}

	@Override
	public void load(ActionHandlerSystem system) throws Exception {
		system.registerButtonHandler(new int[]{SKILL_TAB}, this);
		
	}

	@Override
	public boolean explicitlyForMembers() {
		return false;
	}

	@Override
	public void handleButton(Player player, int opcode, int interfaceId, int b, int b2, int b3) {
		handleButtons(player, interfaceId, b, opcode);
		
	}
	
	private void handleButtons(Player player, int interfaceId, int b, int opcode) { 
	    if (interfaceId != SKILL_TAB) {
	        return;
	    }
	    int targetId = getTargetIdByComponentId(b);
	    if (targetId == -1) {
	        return;
	    }
	    switch (opcode) {
	        case SET_LEVEL_TARGET:
	        case SET_XP_TARGET:
	            int skillId = getSkillIdByTargetId(targetId);
	            if (skillId == -1) {
	                return;
	            }
	            boolean usingLevel = opcode == SET_LEVEL_TARGET;
	            player.getAttributes().set(usingLevel ? "levelSkillTarget" : "xpSkillTarget", skillId);
	            player.sendMessage("Please enter target " + (usingLevel ? "level" : "xp") + " you want to set: ");
	            
	        break;
	        case CLEAR_TARGET:
	        	player.sendMessage("You have cleared your target");
	        break;
	    }
	}


}
