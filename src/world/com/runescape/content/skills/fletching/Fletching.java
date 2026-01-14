package com.runescape.content.skills.fletching;

import java.util.HashSet;
import java.util.Set;

import com.runescape.Static;
import com.runescape.content.handler.ActionHandler;
import com.runescape.content.handler.ActionHandlerSystem;
import com.runescape.content.handler.ItemOnItemHandler;
import com.runescape.content.skills.HarvestingTick;
import com.runescape.content.skills.SkillDialogue;
import com.runescape.content.skills.RSSkillTick;
import com.runescape.engine.tick.Tick;
import com.runescape.logic.item.Item;
import com.runescape.logic.item.ItemDefinition;
import com.runescape.logic.item.PossesedItem;
import com.runescape.logic.player.Levels;
import com.runescape.logic.player.Player;

public class Fletching implements ActionHandler, ItemOnItemHandler {

    private static boolean isTool(int itemId) {
        return itemId == 946 || itemId == 1755;
    }

    @Override
    public void load(ActionHandlerSystem system) throws Exception {
        Set<Integer> idSet = new HashSet<>();
        idSet.add(946);   // Knife
        idSet.add(1755);  // Chisel

        for (FletchData data : FletchData.values()) {
            if (data.primaryId != -1)
                idSet.add(data.primaryId);
            if (data.secondaryId != -1)
                idSet.add(data.secondaryId);
        }

        int[] ids = idSet.stream().mapToInt(Integer::intValue).toArray();
        system.registerItemOnItemHandler(ids, this);
    }

    @Override
    public boolean explicitlyForMembers() {
        return false;
    }

    @Override
    public void handleItemOnItem(Player player, Item item1, int index1, Item item2, int index2) {
        open(player, item1.getId(), item2.getId());
    }

    public static boolean open(Player player, int fromId, int toId) {
        FletchData fletchData = FletchData.get(fromId, toId);
        if (fletchData == null)
            return false;

        int count;
        if (isTool(fromId)) {
            count = player.getInventory().amount(toId);
        } else if (isTool(toId)) {
            count = player.getInventory().amount(fromId);
        } else {
            count = Math.min(player.getInventory().amount(fromId),
                             player.getInventory().amount(toId));
        }

        int[] items = new int[fletchData.fletchItems.length];
        for (int i = 0; i < items.length; i++)
            items[i] = fletchData.fletchItems[i].makeId;

        player.skillObject = fletchData;
        player.openSkillDialogue(SkillDialogue.FLETCHING, count, items);
        return true;
    }

    public static void select(Player player, int slot, int amount) {
        Static.proto.sendCloseChatboxInterface(player);
        FletchData data = (FletchData) player.skillObject;
        if (data == null)
            return;
        player.removeSkillDialogue();
        if (slot < 0 || slot >= data.fletchItems.length)
            return;
        FletchItem item = data.fletchItems[slot];
        if (player.getLevels().getCurrentLevel(Levels.FLETCHING) < item.levelRequest) {
            Static.proto.sendMessage(player,
                    "You need a Fletching level of at least " + item.levelRequest + " to fletch this.");
            return;
        }
        player.registerTick(new Fletching().new FletchingTick(player, data, item, amount));
    }

    public final class FletchingTick extends RSSkillTick {

        private final FletchData data;
        private final FletchItem item;
        private final int amount;
        private int made = 0;

        public FletchingTick(Player player, FletchData data, FletchItem item, int amount) {
            super(player);
            this.data = data;
            this.item = item;
            this.amount = amount;
        }

        @Override
        public void init() {
        }

        @Override
        public int getInterval() {
            return 3;
        }

        @Override
        public int getSkill() {
            return Levels.FLETCHING;
        }

        @Override
        public double getExperience() {
            return 0;
        }

        @Override
        public int getAnimation() {
        	return data.animationId != -1 ? data.animationId : -1;
        }

        @Override
        public boolean isPeriodicRewards() {
            return true;
        }

        @Override
        public PossesedItem getReward() {
            if (!hasMaterials())
               return null;
            int primaryAmount = player.getInventory().amount(data.primaryId);
            int secondaryAmount = player.getInventory().amount(data.secondaryId);
            if (data.animationId != -1) {
                player.doAnimation(data.animationId, 0);
            }
            double otherXp = 0;
            int increment = 1;
            ItemDefinition def = ItemDefinition.forId(data.secondaryId);
            if(data.primaryId == 52 || data.primaryId == 53 || data.secondaryId == 314) {
                increment = Math.min(primaryAmount, secondaryAmount);
                if(increment > 15) {
					increment = 15;
				}
                otherXp = item.xp * increment;
            } else if (def != null && def.name != null && def.name.toLowerCase().endsWith("bolts")) {
                    increment = Math.min(primaryAmount, secondaryAmount);
                    int maxIncrement = data.primaryId == 9194 ? 24 : 12;
                    if (increment > maxIncrement) {
                        increment = maxIncrement;
                    }
                    otherXp = item.xp * increment;
                }
            player.getInventory().remove(data.primaryId, increment);
            if(data.secondaryId != -1 && !isTool(data.secondaryId)) {
				player.getInventory().remove(data.secondaryId, data.secondaryId == 21359 ? 2 : increment);
			}
            if(item.makeId == 859) {
                /**
                 * Magic longbow
                 */
                //if(player.getPosition().inBounds(Bounds.ardougne)) {
				//	ArdougneLocal.instance.fletchMagicLong(player);
				//}
            }
            else if(item.makeId == 52) {
				increment = 15;
			} else if(data.secondaryId == 1755) {
				increment = data.primaryId == 6573 ? 24 : 12;
			}
            player.getLevels().addXP(Levels.FLETCHING, item.xp);
            ItemDefinition def2 = ItemDefinition.forId(data.primaryId);
            String itemName = def2 != null ? def2.name : "Unknown";
            player.sendMessage("You fletch the " + itemName.toLowerCase() + ".");
            return new PossesedItem(item.makeId, increment);
        }

        @Override
        public double getRewardFactor() {
            return 1.0;
        }

        @Override
        public void onReward() {
           
        }

        @Override
        public boolean shouldExpire() {
            return made >= amount || !hasMaterials();
        }

        @Override
        public void expire() {
            player.sendMessage("You've finished fletching.");
            player.doAnimation(-1);
        }

        @Override
        public void stopped(boolean forceResetMasks) {
            player.doAnimation(-1);
        }

        private boolean hasMaterials() {
            if (player.getInventory().amount(data.primaryId) <= 0)
                return false;
            if (data.secondaryId != -1 && !isTool(data.secondaryId)
                    && player.getInventory().amount(data.secondaryId) <= 0)
                return false;
            return true;
        }

        private boolean isTool(int id) {
            return id == 946 || id == 1755;
        }
    }



}
