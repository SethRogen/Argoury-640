package com.runescape.content.skills.herblore;

import java.util.Arrays;

import com.runescape.Static;
import com.runescape.content.skills.SkillDialogue;
import com.runescape.logic.item.PossesedItem;
import com.runescape.logic.player.Levels;
import com.runescape.logic.player.Player;

public class Herblore {

    public static boolean handle(Player player, PossesedItem fromItem, PossesedItem toItem) {
        if(fromItem.getId() == 269 || toItem.getId() == 269) {
            if(fromItem.getDefinition().name.startsWith("Extreme") || toItem.getDefinition().name.startsWith("Extreme")) {
                makeOverload(player);
                return true;
            }
        }
        Potion potion = Potion.get(fromItem.getId(), toItem.getId());
        if(potion == null) {
            Potion fromPotion = Potion.get(fromItem.getId());
            Potion toPotion = Potion.get(toItem.getId());
            if(fromPotion == null || toPotion == null || fromPotion != toPotion) {
				return false;
			}
            decant(player, fromPotion, toPotion, fromItem, toItem);
            return true;
        }
        boolean unfinished = fromItem.getId() != potion.unfinishedId && toItem.getId() != potion.unfinishedId;
        int itemId = unfinished ? potion.unfinishedId : potion.ids[1];
        int amount1, amount2;
        if(unfinished) {
            amount1 = player.getInventory().amount(potion.herbId);
            amount2 = player.getInventory().amount(potion.primaryId);
        } else {
            amount1 = player.getInventory().amount(potion.unfinishedId);
            amount2 = player.getInventory().amount(potion.secondary);
            if(potion.secondary == 12539) {
				amount2 /= 5;
			}
        }
        player.skillObject = new HerbType(potion, unfinished);
        player.openSkillDialogue(SkillDialogue.HERBLORE, Math.min(amount1, amount2), itemId);
        return true;
    }

    public static void select(final Player player, final int amount) {
        Static.proto.sendCloseChatboxInterface(player);
        final HerbType herbType = (HerbType) player.skillObject;
        if(herbType == null) {
			return;
		}
        if(herbType.potion == Potion.OVERLOAD) {
            selectOverload(player, amount);
            return;
        }
        final int toAdd, toReplace, toDelete, toDeleteAmount;
        if(!herbType.unfinished) {
            if(player.getLevels().getCurrentLevel(Levels.HERBLORE) < herbType.potion.level) {
            	Static.proto.sendMessage(player, "You need a Herblore level of at least " + herbType.potion.level + " to make this.");
                return;
            }
            toAdd = herbType.potion.ids[1];
            toReplace = herbType.potion.unfinishedId;
            toDelete = herbType.potion.secondary;
            toDeleteAmount = toDelete == 12539 ? 5 : 1;
        } else {
            toAdd = herbType.potion.unfinishedId;
            toReplace = herbType.potion.primaryId;
            toDelete = herbType.potion.herbId;
            toDeleteAmount = 1;
        }
    }

    /**
     * Overloads
     */

    private static void makeOverload(Player player) {
        int[] amounts = {
                player.getInventory().amount(269),
                player.getInventory().amount(15309),
                player.getInventory().amount(15313),
                player.getInventory().amount(15317),
                player.getInventory().amount(15321),
                player.getInventory().amount(15325)
        };
        Arrays.sort(amounts);
        int count = amounts[0];
        amounts = null;
        if(count <= 0) {
        	Static.proto.sendMessage(player, "You need 1 of each type of 3-dosed extreme potion to make an overload.");
            //PacketSender.sendChatboxMessage(player, "You need 1 of each type of 3-dosed extreme potion to make an overload.");
            return;
        }
        if(player.getLevels().getCurrentLevel(Levels.HERBLORE) < 96) {
        	Static.proto.sendMessage(player, "You need a herblore level of at least 96 to make this potion.");
            return;
        }
        player.skillObject = new HerbType(Potion.OVERLOAD, false);
        player.openSkillDialogue(SkillDialogue.HERBLORE, count, 15333);
    }

    private static void selectOverload(final Player player, final int amount) {
      
    }

    /**
     * Decanting
     */

    private static void decant(Player player, Potion fromPotion, Potion toPotion, PossesedItem fromItem, PossesedItem toItem) {
        int doses = fromPotion.getDoses(fromItem.getId()) + toPotion.getDoses(toItem.getId());
        if(doses == 4) {
            toItem.setId(fromPotion.ids[0]);
            fromItem.setId(229);
            player.getInventory().refresh();
            return;
        }
        if(doses == 3) {
            toItem.setId(fromPotion.ids[1]);
            fromItem.setId(229);
            player.getInventory().refresh();
            return;
        }
        if(doses == 2) {
            toItem.setId(fromPotion.ids[2]);
            fromItem.setId(229);
            player.getInventory().refresh();
            return;
        }
        if(doses == 5) {
            toItem.setId(fromPotion.ids[0]);
            fromItem.setId(fromPotion.ids[3]);
            player.getInventory().refresh();
            return;
        }
        if(doses == 6) {
            toItem.setId(fromPotion.ids[0]);
            fromItem.setId(fromPotion.ids[2]);
            player.getInventory().refresh();
            return;
        }
    }

    public static int bulkDecant(Player player, int toDoses) {
        int decanted = 0;
       
        return decanted;
    }

    private static int getPotionId(PossesedItem item, boolean decantPotionNoted) {
      
        return item.getId();
    }

}
