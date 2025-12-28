package com.runescape.content.skills.cooking;

import com.runescape.content.skills.Firemaking;
import com.runescape.content.skills.SkillDialogue;
import com.runescape.logic.object.GameObject;
import com.runescape.logic.player.Player;

public class Cooking {
	
    public static boolean openCook(Player player, int itemId, GameObject rsObject) {
        boolean onFire = false;
        if(isRange(rsObject.getId()) || (onFire = Firemaking.isFire(rsObject.getId()))) {
            CookData cookData = CookData.get(itemId);
            if(cookData == null)
                return false;
            player.skillObject = new CookType(cookData, onFire ? rsObject : null);
            player.openSkillDialogue(SkillDialogue.COOKING, player.getInventory().amount(itemId), itemId);
            return true;
        }
        return false;
    }
    
    public static boolean isRange(int objectId) {
        switch(objectId) {
            case 2728:
            case 3039:
            case 9085:
            case 9086:
            case 9087:
            case 9682:
            case 12269:
            case 14919:
            case 22713:
            case 22714:
            case 24283:
            case 24284:
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
            case 21376:
                return true;
        }
        return false;
    }

}
