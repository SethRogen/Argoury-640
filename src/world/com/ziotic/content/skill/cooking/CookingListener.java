package com.ziotic.content.skill.cooking;

import java.util.Random;

import com.ziotic.Static;
import com.ziotic.content.handler.ActionHandler;
import com.ziotic.content.handler.ActionHandlerSystem;
import com.ziotic.content.handler.ItemOnObjectHandler;
import com.ziotic.content.skill.SkillManager;
import com.ziotic.content.skill.SkillingInterface;
import com.ziotic.logic.item.PossesedItem;
import com.ziotic.logic.object.GameObject;
import com.ziotic.logic.player.Player;



/**
 * @author Seth Rogen
 */


public class CookingListener implements ActionHandler, ItemOnObjectHandler {
	
	/**
	 * Enum Struture 
	 * 
	 * RawID, CookedID, BurntID, Level, Experince, OnlyFire
	 */
    private CookingRecipe getCookingRecipeForRawId(int rawId) {
        for (CookingRecipe recipe : CookingRecipe.values()) {
            if (recipe.getRawId() == rawId) {
                return recipe;
            }
        }
        return null; // Recipe not found for the given raw ID
    }
    /**
     * Animation IDS - Cooking on fire, 897
     */
	@Override
	public void handleItemOnObject(final Player player, PossesedItem item, int itemIndex, GameObject obj) {
	    int rawId = item.getId(); // Get the ID of the raw item
	    CookingRecipe recipe = getCookingRecipeForRawId(rawId); // Look up the CookingRecipe based on the raw ID
	    final String key = item.getDefinition().name;
	    if (recipe != null) {
	    	if (player.getLevels().COOKING > recipe.getLevel()) { 
	    		SkillingInterface.Open(player, SkillingInterface.COOK, "Choose how many you would like to cook, <br> then click on the item to begin", new int[] { rawId }, player.getInventory().amount(rawId), key, null);
	    	} else { 
	    		Static.proto.sendMessage(player, "You do not have the required level to cook this.");
	    	}
	    }
	}

	
    public boolean Roll(Player player) {
    	int success = SkillManager.interpolate(128, 512, player.getLevels().COOKING);
		if (success > new Random().nextInt(256)) {
			return true;
		}
        return false;
    }
    
    @Override
    public void load(ActionHandlerSystem system) throws Exception {
        int[][] rawFishIds = new int[CookingRecipe.values().length][2];

        int index = 0;
        for (CookingRecipe recipe : CookingRecipe.values()) {
            rawFishIds[index][0] = recipe.getRawId();
            rawFishIds[index][1] = 2732; // The object ID where the cooking action is performed
            index++;
        }

        system.registerItemOnObjectHandler(rawFishIds, this);
    }

	@Override
	public boolean explicitlyForMembers() {
		// TODO Auto-generated method stub
		return false;
	}

}