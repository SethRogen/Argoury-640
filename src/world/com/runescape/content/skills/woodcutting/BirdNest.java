package com.runescape.content.skills.woodcutting;

import java.util.Random;

import com.runescape.Static;
import com.runescape.content.handler.ActionHandlerSystem;
import com.runescape.content.handler.ButtonHandler;
import com.runescape.logic.item.PossesedItem;
import com.runescape.logic.player.Player;

public enum BirdNest {

    SEED(5070),
    RING(5071),
    EMPTY(5072),
    RED_EGG(5073),
    GREEN_EGG(5074),
    BLUE_EGG(7413),
    RAVEN_EGG(11966);

    private static final Random RANDOM = new Random();
    private final int id;

    BirdNest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static BirdNest forId(int id) {
        for (BirdNest nest : values()) {
            if (nest.id == id) {
                return nest;
            }
        }
        return null;
    }

    private static final int[][] SEEDS = {
            {5312, 5283, 5284, 5313, 5285, 5286},
            {5314, 5287, 5288, 5315, 5289},
            {5316, 5290},
            {5317}
    };

    private static final int[] RINGS = {
            1635, 1637, 1639, 1641, 1643
    };

    public int rollReward() {
        switch (this) {
            case SEED:
                int[] tier = SEEDS[RANDOM.nextInt(SEEDS.length)];
                return tier[RANDOM.nextInt(tier.length)];

            case RING:
                return RINGS[RANDOM.nextInt(RINGS.length)];

            default:
                return -1;
        }
    }
}
