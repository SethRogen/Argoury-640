package com.runescape.content.skills.fletching;

public enum FletchData {
    /**
     * Logs
     */
    NORMAL(1248, 1511, 946,
            new FletchItem(52, 1, 5),
            new FletchItem(50, 5, 5),
            new FletchItem(48, 10, 10),
            new FletchItem(9440, 9, 6)
    ),
    OAK(1248, 1521, 946,
            new FletchItem(54, 20, 16.5),
            new FletchItem(56, 25, 25),
            new FletchItem(9442, 24, 16)
    ),
    WILLOW(1248, 1519, 946,
            new FletchItem(60, 35, 33.3),
            new FletchItem(58, 40, 41.5),
            new FletchItem(9444, 39, 22)
    ),
    MAPLE(1248, 1517, 946,
            new FletchItem(64, 50, 50),
            new FletchItem(62, 55, 58.3),
            new FletchItem(9448, 54, 32)
    ),
    TEAK(1248, 6333, 946,
            new FletchItem(9446, 46, 27)
    ),
    MAHOGANY(1248, 9450, 946,
            new FletchItem(9450, 61, 41)
    ),
    YEW(1248, 1515, 946,
            new FletchItem(68, 65, 67.5),
            new FletchItem(66, 70, 75),
            new FletchItem(9452, 69, 50)
    ),
    MAGIC(7211, 1513, 946,
            new FletchItem(72, 80, 83.3),
            new FletchItem(70, 85, 91.5)
    ),
    /**
     * Darts
     */
    BRONZE_DART(-1, 819, 314,
            new FletchItem(806, 1, 1.8)
    ),
    IRON_DART(-1, 820, 314,
            new FletchItem(807, 22, 3.8)
    ),
    STEEL_DART(-1, 821, 314,
            new FletchItem(808, 37, 7.5)
    ),
    MITHRIL_DART(-1, 822, 314,
            new FletchItem(809, 52, 11.2)
    ),
    ADDY_DART(-1, 823, 314,
            new FletchItem(810, 67, 15)
    ),
    RUNE_DART(-1, 824, 314,
            new FletchItem(811, 81, 18.8)
    ),
    DRAGON_DART(-1, 11232, 314,
    		new FletchItem(11230, 95, 25)
    ),
    /**
     * Bolts
     */
    BRONZE_BOLT(-1, 9375, 314,
            new FletchItem(877, 9, 0.5)
    ),
    IRON_BOLT(-1, 9377, 314,
            new FletchItem(9140, 39, 1.5)
    ),
    STEEL_BOLT(-1, 9378, 314,
            new FletchItem(9141, 46, 3.5)
    ),
    MITHRIL_BOLT(-1, 9379, 314,
            new FletchItem(9142, 54, 5)
    ),
    ADDY_BOLT(-1, 9380, 314,
            new FletchItem(9143, 61, 7)
    ),
    RUNE_BOLT(-1, 9381, 314,
            new FletchItem(9144, 69, 10)
    ),
    DRAGONBANE_BOLT(-1, 21843, 314,
    		new FletchItem(21660, 90, 25)
    ),
    DEMONBANE_BOLT(-1, 21853, 314,
    		new FletchItem(21665, 90, 25)
    ),
    /**
     * Arrows
     */
    HEADLESS_ARROWS(-1, 52, 314,
            new FletchItem(53, 1, 1)
    ),
    BRONZE_ARROW(-1, 53, 39,
            new FletchItem(882, 1, 2.6)
    ),
    IRON_ARROW(-1, 53, 40,
            new FletchItem(884, 15, 3.8)
    ),
    STEEL_ARROW(-1, 53, 41,
            new FletchItem(886, 30, 6.3)
    ),
    MITHRIL_ARROW(-1, 53, 42,
            new FletchItem(888, 45, 8.8)
    ),
    ADDY_ARROW(-1, 53, 43,
            new FletchItem(890, 60, 11.3)
    ),
    RUNE_ARROW(-1, 53, 44,
            new FletchItem(892, 75, 13.8)
    ),
    DRAGON_ARROW(-1, 53, 11237,
    		new FletchItem(11212, 90, 16.3)
    ),
    DRAGONBANE_ARROW(-1, 53, 21823,
    		new FletchItem(21640, 90, 20)
    ),
    DEMONBANE_ARROW(-1, 53, 21828,
    		new FletchItem(21645, 90, 20)
    ),
    /**
     * Strung bows
     */
    SHORTBOW(6678, 50, 1777,
            new FletchItem(841, 5, 5)
    ),
    LONGBOW(6684, 48, 1777,
            new FletchItem(839, 10, 10)
    ),
    OAK_SHORTBOW(6679, 54, 1777,
            new FletchItem(843, 20, 16.5)
    ),
    OAK_LONGBOW(6685, 56, 1777,
            new FletchItem(845, 25, 25)
    ),
    WILLOW_SHORTBOW(6680, 60, 1777,
            new FletchItem(849, 35, 33.3)
    ),
    WILLOW_LONGBOW(6686, 58, 1777,
            new FletchItem(847, 40, 41.5)
    ),
    MAPLE_SHORTBOW(6681, 64, 1777,
            new FletchItem(853, 50, 50)
    ),
    MAPLE_LONGBOW(6687, 62, 1777,
            new FletchItem(851, 55, 58.3)
    ),
    YEW_SHORTBOW(6682, 68, 1777,
            new FletchItem(857, 65, 67.5)
    ),
    YEW_LONGBOW(6688, 66, 1777,
            new FletchItem(855, 70, 75)
    ),
    MAGIC_SHORTBOW(6683, 72, 1777,
            new FletchItem(861, 80, 83.3)
    ),
    MAGIC_LONGBOW(6689, 70, 1777,
            new FletchItem(859, 85, 91.5)
    ),
    /**
     * Bolt tips
     */
    OPAL_BOLT_TIPS(891, 1609, 1755,
            new FletchItem(45, 11, 1.5)
    ),
    JADE_BOLT_TIPS(891, 1611, 1755,
            new FletchItem(9187, 26, 2)
    ),
    PEARL_BOLT_TIPS(886, 411, 1755,
            new FletchItem(46, 41, 2.2)
    ),
    TOPAZ_BOLT_TIPS(891, 1613, 1755,
            new FletchItem(9188, 48, 3)
    ),
    SAPPHIRE_BOLT_TIPS(888, 1607, 1755,
            new FletchItem(9189, 56, 4)
    ),
    EMERALD_BOLT_TIPS(889, 1605, 1755,
            new FletchItem(9190, 58, 5.5)
    ),
    RUBY_BOLT_TIPS(887, 1603, 1755,
            new FletchItem(9191, 63, 6)
    ),
    DIAMOND_BOLT_TIPS(886, 1601, 1755,
            new FletchItem(9192, 65, 7)
    ),
    DRAGON_BOLT_TIPS(885, 1615, 1755,
            new FletchItem(9193, 71, 8.2)
    ),
    ONYX_BOLT_TIPS(885, 6573, 1755,
            new FletchItem(9194, 73, 9.4)
    ),
    /**
     * Bolts
     */
    OPAL_BOLTS(-1, 45, 877,
            new FletchItem(879, 11, 1)
    ),
    JADE_BOLTS(-1, 9187, 9139,
            new FletchItem(9335, 26, 2.4)
    ),
    PEARL_BOLTS(-1, 46, 9140,
            new FletchItem(880, 41, 2.6)
    ),
    TOPAZ_BOLTS(-1, 9188, 9141,
            new FletchItem(9336, 48, 4)
    ),
    SAPPHIRE_BOLTS(-1, 9189, 9142,
            new FletchItem(9337, 56, 5)
    ),
    EMERALD_BOLTS(-1, 9190, 9142,
            new FletchItem(9338, 58, 5.5)
    ),
    RUBY_BOLTS(-1, 9191, 9143,
            new FletchItem(9339, 63, 6)
    ),
    DIAMOND_BOLTS(-1, 9192, 9143,
            new FletchItem(9340, 65, 7)
    ),
    DRAGON_BOLTS(-1, 9193, 9144,
            new FletchItem(9341, 71, 8.2)
    ),
    ONYX_BOLTS(-1, 9194, 9144,
            new FletchItem(9342, 73, 9.4)
    ),
    /**
     * Misc
     */
    GRAPPLE_TIP(-1, 9416, 9142,
            new FletchItem(9418, 59, 59)
    ),
    BOLAS(-1, 21358, 21359,
            new FletchItem(21365, 87, 50)
    );

    public final int animationId;
    public final int primaryId;
    public final int secondaryId;
    public final FletchItem[] fletchItems;

    FletchData(int animationId, int primaryId, int secondaryId, FletchItem... fletchItems) {
        this.animationId = animationId;
        this.primaryId = primaryId;
        this.secondaryId = secondaryId;
        this.fletchItems = fletchItems;
    }

    public FletchItem getFletchItem(int itemId) {
        for(FletchItem item : fletchItems) {
            if(item.makeId == itemId)
                return item;
        }
        return null;
    }

    public static FletchData get(int fromId, int toId) {
        for(FletchData data : values()) {
            if((data.primaryId == fromId && data.secondaryId == toId) || (data.secondaryId == fromId && data.primaryId == toId))
                return data;
        }
        return null;
    }

}
