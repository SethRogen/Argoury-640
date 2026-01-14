package com.runescape.content.skills.cooking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.runescape.Static;
import com.runescape.content.handler.ActionHandler;
import com.runescape.content.handler.ActionHandlerSystem;
import com.runescape.content.handler.ItemOnObjectHandler;
import com.runescape.content.skills.RSInterpolator;
import com.runescape.content.skills.SkillDialogue;
import com.runescape.content.skills.firemaking.Firemaking;
import com.runescape.content.skills.RSSkillTick;
import com.runescape.logic.item.ItemDefinition;
import com.runescape.logic.item.PossesedItem;
import com.runescape.logic.object.GameObject;
import com.runescape.logic.player.Levels;
import com.runescape.logic.player.Player;
import com.runescape.logic.utility.Utility;

public class Cooking implements ActionHandler, ItemOnObjectHandler {

    public static boolean openCook(Player player, int itemId, GameObject object) {
        boolean onFire = false;
        if (!isRange(object.getId()) && !(onFire = Firemaking.isFire(object.getId()))) {
            return false;
        }
        CookData data = CookData.get(itemId);
        if (data == null) {
            return false;
        }
        player.skillObject = new CookType(data, onFire ? object : null);
        player.openSkillDialogue(
                SkillDialogue.COOKING,
                player.getInventory().amount(itemId),
                itemId
        );
        return true;
    }

    public static void cook(Player player, int amount) {
        Static.proto.sendCloseChatboxInterface(player);
        CookType cookType = (CookType) player.skillObject;
        if (cookType == null) {
            return;
        }
        player.removeSkillDialogue();
        if (player.getLevels().getCurrentLevel(Levels.COOKING) < cookType.data.level) {
            Static.proto.sendMessage(
                    player,
                    "You need a Cooking level of " + cookType.data.level + " to cook this."
            );
            return;
        }
        player.registerTick(new CookingTick(player, cookType, amount));
    }
    
    public static final class CookingTick extends RSSkillTick {

        private final CookType cookType;
        private final int amount;

        private int cooked;
        private int animationTicks;

        public CookingTick(Player player, CookType cookType, int amount) {
            super(player);
            this.cookType = cookType;
            this.amount = amount;
        }

        @Override
        public int getSkill() {
            return Levels.COOKING;
        }

        @Override
        public int getInterval() {
            return 4;
        }

        @Override
        public int getAnimation() {
            return cookType.fireObject == null ? 883 : 897;
        }

        @Override
        public boolean isPeriodicRewards() {
            return true;
        }
        
        
        
        
        @Override
        public PossesedItem getReward() {
            int level = player.getLevels().getCurrentLevel(Levels.COOKING);
            ItemDefinition cooked = ItemDefinition.forId(cookType.data.cookId);
            ItemDefinition burnt = ItemDefinition.forId(cookType.data.burnId);
            boolean success = RSInterpolator.rollSuccess(level, cookType.data.lowChance, cookType.data.highChance);

            // Handle cooking animations
            if (cookType.fireObject == null) {
                player.doAnimation(883, 0); // standard cooking animation
            } else {
                if (cookType.fireObject.getId() == -1) {
                    // optional: handle special fire object case
                }
                if (animationTicks++ == 4) {
                    player.doAnimation(897, 0); // fire cooking animation
                    animationTicks = 0;
                }
            }

            // Apply result
            if (success) {
                // Successfully cooked
                player.getInventory().replace(cookType.data.rawId, cookType.data.cookId);
                player.getLevels().addXP(Levels.COOKING, cookType.data.xp);
                Static.proto.sendMessage(player, "You successfully cook the " + cooked.name.toLowerCase() + ".");
            } else {
                // Burnt fish
                player.getInventory().replace(cookType.data.rawId, cookType.data.burnId);
                Static.proto.sendMessage(player, "You accidentally burn the " + burnt.name.toLowerCase() + ".");
            }

            return new PossesedItem(cookType.data.cookId, 0);
        }

/*
        @Override
        public PossesedItem getReward() {
        	
            int level = player.getLevels().getCurrentLevel(Levels.COOKING);
            boolean success = RSInterpolator.rollSuccess(level, cookType.data.lowChance, cookType.data.highChance);
            
            
            if(cookType.fireObject == null) {
            	 player.doAnimation(883, 0);
            } else {
                if(cookType.fireObject.getId() == -1) {
                	//
                }
                if(animationTicks++ == 4) {
                	 player.doAnimation(897, 0);
                    animationTicks = 0;
                }
            }   
            int b = cookType.data.levelRequest / (cookType.fireObject != null ? 15 : 20);
            if(b <= 0) {
				b = 1;
			}
            int currentLevel = player.getLevels().getCurrentLevel(Levels.COOKING);
            int noBurnLevel = cookType.data.noBurnLevel;
            if(currentLevel < noBurnLevel && Utility.random((currentLevel - cookType.data.levelRequest) + 20) < Utility.random(b)) {
                player.getInventory().replace(cookType.data.rawId, cookType.data.burnId);
                Static.proto.sendMessage(player, "You accidentally burn the fish.");
            } else {
                if(cookType.data.rawId == 377) {
                }
                player.getInventory().replace(cookType.data.rawId, cookType.data.cookId);
                player.getLevels().addXP(Levels.COOKING, cookType.data.xp);
                Static.proto.sendMessage(player, "You successfully cook the fish.");
            }
            return new PossesedItem(cookType.data.cookId, 0);
        }*/

        @Override
        public double getExperience() {
            return 0; // handled manually
        }

        @Override
        public void onReward() {

        }

        @Override
        public boolean shouldExpire() {
            // Stop if no raw food left
            if (player.getInventory().amount(cookType.data.rawId) <= 0) {
                Static.proto.sendMessage(player, "You have run out of raw food.");
                return true;
            }
            // Stop if fire/range is gone
            if (cookType.fireObject != null & cookType.burnedFire) {
                int objId = cookType.fireObject.getId();
                if (!Firemaking.isFire(objId) && !Cooking.isRange(objId)) {
                    Static.proto.sendMessage(player, "The fire has burned out.");
                    return true;
                }
            }
            return false;
        }

        @Override
        public void expire() {
            player.sendMessage("You have finished cooking.");
            player.doAnimation(-1);
            cookType.burnedFire = true;
        }

        @Override
        public void stopped(boolean forceResetMasks) {
        	 player.doAnimation(-1);
        }

		@Override
		public void init() {
			
		}

		@Override
		public double getRewardFactor() {
			return 1.0;
		}
    }
    
    public static boolean isRange(int objectId) {
        switch (objectId) {
            case 2728: case 3039: case 9085: case 9086: case 9087:
            case 9682: case 12269: case 14919: case 21376:
            case 22713: case 22714:
            case 24283: case 24284:
            case 24313:
            case 25730:
            case 33500:
            case 34495:
            case 34546:
            case 36973:
            case 37629:
            case 40110:
            case 41264:
            case 42345:
            case 42476:
            case 43071:
            case 43081:
            case 44078:
            case 45316:
            case 45319:
            case 47633:
            case 49035:
            case 49130:
            case 52576:
            case 61333:
                return true;
        }
        return false;
    }

    @Override
    public void handleItemOnObject(Player player, PossesedItem item, int itemIndex, GameObject obj) {
        Cooking.openCook(player, item.getId(), obj);
    }
    
    @Override
    public void load(ActionHandlerSystem system) throws Exception {
        List<Integer> itemList = new ArrayList<>();
        Set<Integer> fireObjects = new HashSet<>();
        int maxObjectId = 65000;
        for (int objId = 0; objId < maxObjectId; objId++) {
            if (Cooking.isRange(objId) || Firemaking.isFire(objId)) {
                fireObjects.add(objId);
            }
        }
        int totalPairs = itemList.size() * fireObjects.size();
        int[][] pairs = new int[totalPairs][2];
        int index = 0;
        for (int itemId : itemList) {
            for (int fireId : fireObjects) {
                pairs[index][0] = itemId;
                pairs[index][1] = fireId;
                index++;
            }
        }
        system.registerItemOnObjectHandler(pairs, this);
    }

    
	@Override
	public boolean explicitlyForMembers() {
		// TODO Auto-generated method stub
		return false;
	}
}
