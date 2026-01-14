package com.runescape.content.skills;

import org.apache.log4j.Logger;

import com.runescape.content.handler.ActionHandlerSystem;
import com.runescape.content.handler.ButtonHandler;
import com.runescape.logic.player.Player;
import com.runescape.utility.Logging;

public enum SkillDialogue {

    /*
        Config Ids
        0 - Make
        1 - Make sets
        2 - Cook
        3 - Roast
        4 - Offer
        5 - Sell
        6 - Bake
        7 - Cut
        8 - Deposit
        9 - Make (Without 'Custom' or 'All')
        10 - Teleport
        11 - Select
        13 - Take
     */

    COOKING(2, "Choose how many you would like to cook,<br>then click on the item to begin."),
    CRAFTING(0, "Choose how many you would like to make,<br>then click on the item to begin."),
    HERBLORE(0, "Choose how many you would like to make,<br>then click on the item to begin."),
    FLETCHING(0, "Choose how many you would like to make,<br>then click on the item to begin.");

    public final byte configId;

    public String info;

    SkillDialogue(int configId, String info) {
        this.configId = (byte) configId;
        this.info = info;
    }

    public static void handleAmount(Player player, int buttonId) {
        if(player.skillDialogue == null)
            return;
        if(buttonId == 19) {
            int newAmount = player.selectedSkillDialogueAmount + 1;
            if(newAmount > player.maxSkillDialogueAmount)
                newAmount = player.maxSkillDialogueAmount;
            player.selectedSkillDialogueAmount = newAmount;
            return;
        }
        if(buttonId == 20) {
            int newAmount = player.selectedSkillDialogueAmount - 1;
            if(newAmount < 0)
                newAmount = 0;
            player.selectedSkillDialogueAmount = newAmount;
            return;
        }
    }

    /**
     * Children
     */

    public static final int[][] CHILDREN = {
            {755, 132},
            {756, 133},
            {757, 134},
            {758, 135},
            {759, 136},
            {760, 137},

            {1139, 280},
            {1140, 281},
            {1141, 282},
            {1142, 283},

            {120, 275},
            {185, 316},
            {87, 317},
            {90, 318},
    };
}
