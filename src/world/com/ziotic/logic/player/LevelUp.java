package com.ziotic.logic.player;

import com.ziotic.Static;

/**
 * Level up class.
 * @author Seth Rogen
 *
 */
public class LevelUp {
	
	 public static final int[] SKILL_ICON = { 100000000, 400000000, 200000000,
		      450000000, 250000000, 500000000, 300000000, 1100000000, 1250000000,
		      1300000000, 1050000000, 1200000000, 800000000, 1000000000, 900000000,
		      650000000, 600000000, 700000000, 1400000000, 1450000000, 850000000,
		      1500000000, 1600000000, 1650000000, 1700000000 };

	/**
	 * Called when a player levels up.
	 * @param player The player.
	 * @param skill The skill id.
	 */
	
	public static void levelUp(Player player, int skill, int newlevel) {
		player.getAttributes().set("leveledUp", skill);
		player.getAttributes().set("leveledUp["+skill+"]", Boolean.TRUE);
		player.doGraphics(199, 0, 100);
		Static.proto.sendMessage(player, "You've just advanced a " + Levels.SKILL_NAME[skill] + " level! You have reached level " + newlevel + ".");
		if (newlevel == 99 || newlevel == 120) { //This only is used once you hit level 99 or 120.
			player.doGraphics(1633, 0 , 100); // to do max level graphics level up
			player.doGraphics(1635, 0 , 100); // to do 
			Static.proto.sendMessage(player, "<col=800000>Well done! You've achieved the highest possible level in this skill!</col>"); //DARK RED
		}
		Static.proto.sendString(player, 740, 0 , "Congratulations, you have just advanced a " + Levels.SKILL_NAME[skill] + " level!");
		Static.proto.sendString(player, 740, 1, "You have now reached level " + newlevel + ".");
		Static.proto.sendVarp(player, 1179, SKILL_ICON[skill]); //TODO FLASHING SKILL ICONS
		Static.proto.sendChatboxInterface(player, 740);
	}
		/**
		 1630: Small blue rocket speeding across floor (New level 99 graphics)
1631: Bigger blue rocket speeding across floor (New level 99 graphics)
1632: Blue Rocket speeding across floor (New level 99 graphics)
1633: Blue rocket flying upwards (New level 99 graphics)
1634: Sparkly blue rocket flying upwards (New level 99 graphics)
1635: Sparkles (New level 99 graphics)
1636: Sparkles and confetti (New level 99 graphics)
1637: Sparkles and falling stars (New level 99 graphics)
		 */

}
