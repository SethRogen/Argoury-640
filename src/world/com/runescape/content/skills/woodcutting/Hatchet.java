package com.runescape.content.skills.woodcutting;

import com.runescape.logic.player.Levels;
import com.runescape.logic.player.Player;
import com.runescape.logic.utility.rsconfigs.RSAnimation;
import com.runescape.logic.utility.rsconfigs.RSItems;

public enum Hatchet {

    BRONZE(RSItems.BRONZE_HATCHET, 1, RSAnimation.CHOP_BRONZE_HATCHET, RSAnimation.IVY_BRONZE_HATCHET, 1.0),
    IRON(RSItems.IRON_HATCHET, 1, RSAnimation.CHOP_IRON_HATCHET, RSAnimation.IVY_IRON_HATCHET, 1.5),
    STEEL(RSItems.STEEL_HATCHET, 6, RSAnimation.CHOP_STEEL_HATCHET, RSAnimation.IVY_STEEL_HATCHET, 2.0),
    BLACK(RSItems.BLACK_HATCHET, 6, RSAnimation.CHOP_BLACK_HATCHET, RSAnimation.IVY_BLACK_HATCHET, 2.25),
    MITHRIL(RSItems.MITHRIL_HATCHET, 21, RSAnimation.CHOP_MITHRIL_HATCHET, RSAnimation.IVY_MITHRIL_HATCHET, 2.5),
    ADAMANT(RSItems.ADAMANT_HATCHET, 31, RSAnimation.CHOP_ADAMANT_HATCHET, RSAnimation.IVY_ADAMANT_HATCHET, 3.0),
    RUNE(RSItems.RUNE_HATCHET, 41, RSAnimation.CHOP_RUNE_HATCHET, RSAnimation.IVY_RUNE_HATCHET, 3.5),
    DRAGON(RSItems.DRAGON_HATCHET, 61, RSAnimation.CHOP_DRAGON_HATCHET, RSAnimation.IVY_DRAGON_HATCHET, 3.75),
    INFERNO_ADZE(RSItems.INFERNO_ADZE, 61, RSAnimation.CHOP_INFERNO_ADZE, RSAnimation.IVY_INFERNO_ADZE, 3.75);

    private final int itemId;
    private final int level;
    private final int animation;
    private final int ivyAnimation;
    private final double ratio;

    Hatchet(int itemId, int level, int animation, int ivyAnimation, double ratio) {
        this.itemId = itemId;
        this.level = level;
        this.animation = animation;
        this.ivyAnimation = ivyAnimation;
        this.ratio = ratio;
    }

    public int getItemId() {
        return itemId;
    }

    public int getLevel() {
        return level;
    }

    public int getAnimation() {
        return animation;
    }

    public int getIvyAnimation() {
        return ivyAnimation;
    }

    public double getRatio() {
        return ratio;
    }

    private Hatchet getBestAxe(Player player) {
        Hatchet best = null;
        for (Hatchet h : Hatchet.values()) {
            boolean hasAxe = player.getInventory().contains(h.getItemId())
                          || player.getEquipment().contains(h.getItemId());
            if (player.getLevels().getLevel(Levels.WOODCUTTING) >= h.getLevel() && hasAxe) {
                if (best == null || h.getRatio() > best.getRatio()) {
                    best = h;
                }
            }
        }
        return best;
    }
}
