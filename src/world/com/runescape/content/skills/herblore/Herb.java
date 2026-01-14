package com.runescape.content.skills.herblore;

import com.runescape.Static;
import com.runescape.logic.item.PossesedItem;
import com.runescape.logic.player.Levels;
import com.runescape.logic.player.Player;

public enum Herb {

    /* Herbs in RuneScape. */
    GUAM(199, 249, 1, 2.5D),
    MARRENTILL(201, 251, 5, 3.8D),
    TARROMIN(203, 253, 11, 5.0D),
    HARRALANDER(205, 255, 20, 6.3D),
    RANARR(207, 257, 25, 7.5D),
    TOADFLAX(3049, 2998, 30, 8.0D),
    IRIT(209, 259, 40, 8.8D),
    WERGALI(14836, 14854, 41, 9.5D),
    AVANTOE(211, 261, 48, 10.0D),
    KWUARM(213, 263, 54, 11.3D),
    SNAPDRAGON(3051, 3000, 59, 11.8D),
    CADANTINE(215, 265, 65, 12.5D),
    LANTADYME(2485, 2481, 67, 13.1D),
    DWARFWEED(217, 267, 70, 13.8D),
    SPIRITWEED(12174, 12172, 35, 7.8D),
    TORSTOL(219, 269, 75, 15.0D),
    FELLSTALK(21626, 21624, 91, 16.8D),

    /* Herbs in Daemonheim. */
    SAGEWORT(17494, 17512, 3, 2.1D),
    VALERIAN(17496, 17514, 4, 3.2D),
    ALOE(17498, 17516, 8, 4.0D),
    WORMWOOD(17500, 17518, 34, 7.2D),
    MAGEBANE(17502, 17520, 37, 7.7D),
    FEATHERFOIL(17504, 17522, 41, 8.6D),
    WINTERSGRIP(17506, 17524, 67, 12.7D),
    LYCOPUS(17508, 17526, 70, 13.1D),
    BUCKTHORN(17510, 17510, 74, 13.8D),

    /* Herbs in the Herblore Habitat. */
    ERZILLE(19984, 19989, 54, 10.0D),
    UGUNE(19986, 19991, 56, 11.5D),
    ARGWAY(19985, 19990, 57, 11.6D),
    SHENGO(19987, 19992, 58, 11.7D),
    SAMADEN(19988, 19993, 59, 11.7D);

    public final short unidentifiedId;

    public final short identifiedId;

    public final short levelRequirement;

    public final double experience;

    private Herb(int unidentifiedId, int identifiedId, int levelRequirement, double experience) {
        this.unidentifiedId = (short) unidentifiedId;
        this.identifiedId = (short) identifiedId;
        this.levelRequirement = (short) levelRequirement;
        this.experience = experience;
    }

    public static boolean clean(Player player, PossesedItem item) {
        if(!item.getDefinition().name.startsWith("Grimy")) {
			return false;
		}
        for(Herb herb : values()) {
            if(herb == null || herb.unidentifiedId != item.getId()) {
				continue;
			}
            if(player.getLevels().getCurrentLevel(Levels.HERBLORE) < herb.levelRequirement) {
                Static.proto.sendMessage(player, "You need a Herblore level of at least " + herb.levelRequirement + " to do this.");
                return true;
            }
            item.setId(herb.identifiedId);
            player.doAnimation(10119, 0);
            player.getLevels().addXP(Levels.HERBLORE, herb.experience);
            Static.proto.sendMessage(player, "You clean the grimy herb.");
            player.getInventory().refresh();
            return true;
        }
        return false;
    }

}
