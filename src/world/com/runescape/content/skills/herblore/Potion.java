package com.runescape.content.skills.herblore;

public enum Potion {

    ATTACK(
            "Attack",
            1, 25,
            227, 221, 249, 91,
            2428, 121, 123, 125
    ),
    ANTIPOISON(
            "Antipoison",
            5, 37.5,
            227, 235, 251, 93,
            2446, 175, 177, 179
    ),
    STRENGTH(
            "Strength",
            12, 50,
            227, 225, 253, 95,
            113, 115, 117, 119
    ),
    RESTORE(
            "Restore",
            22, 62.5,
            227, 223, 255, 97,
            2430, 127, 129, 131
    ),
    ENERGY(
            "Energy",
            26, 67.5,
            227, 1975, 255, 97,
            3008, 3010, 3012, 3014
    ),
    DEFENCE(
            "Defence",
            30, 75,
            227, 239, 257, 99,
            2432, 133, 135, 137
    ),
    AGILITY(
            "Agility",
            34, 80,
            227, 2152, 2998, 3002,
            3032, 3034, 3036, 3038
    ),
    COMBAT(
            "Combat",
            36, 84,
            227, 9736, 255, 97,
            9739, 9741, 9743, 9745
    ),
    PRAYER(
            "Prayer",
            38, 87.5,
            227, 231, 257, 99,
            2434, 139, 141, 143
    ),
    SUMMONING(
            "Summoning",
            40, 92,
            227, 12109, 12172, 12181,
            12140, 12142, 12144, 12146
    ),
    CRAFTING(
            "Crafting",
            42, 95,
            227, 5004, 14836, 14856,
            14838, 14840, 14842, 14844
    ),
    SUPER_ATTACK(
            "Super attack",
            45, 100,
            227, 221, 259, 101,
            2436, 145, 147, 149
    ),
    SANFEW_MIXTURE_1(
            "Mixture - step 1",
            1, 0,
            3026, -1, 235, 10911,
            -1, -1, -1, -1
            //10909, 10911, 10913, 10915
    ),
    SANFEW_MIXTURE_2(
            "Mixture - step 2",
            1, 0,
            10911, -1, 1526, 10919,
            -1, -1, -1, -1
            //10917, 10919, 10921, 10923
    ),
    SANFEW_SERUM(
            "Sanfew serum",
            65, 60,
            -1, 10937, -1, 10919,
            10925, 10927, 10929, 10931
    ),
    SUPER_DEFENCE(
            "Super defence",
            66, 150,
            227, 239, 265, 107,
            2442, 163, 165, 167
    ),
    SUPER_ANTIPOISON(
            "Super antipoison",
            48, 106.3,
            227, 235, 259, 101,
            2448, 181, 183, 185
    ),
    FISHING(
            "Fishing",
            50, 112.5,
            227, 231, 261, 103,
            2438, 151, 153, 155
    ),
    SUPER_ENERGY(
            "Super energy",
            52, 117.5,
            227, 2970, 261, 103,
            3016, 3018, 3020, 3022
    ),
    HUNTER(
            "Hunter",
            53, 120,
            227, 10111, 261, 103,
            9998, 10000, 10002, 10004
    ),
    SUPER_STRENGTH(
            "Super strength",
            55, 125,
            227, 225, 263, 105,
            2440, 157, 159, 161
    ),
    FLETCHING(
            "Fletching",
            58, 132,
            227, 11525, 14854, 14856,
            14846, 14848, 14850, 14852
    ),
    WEAPON_POISON(
            "Weapon poison",
            60, 137.5,
            227, 241, 263, 105,
            -1, 187, -1, -1
    ),
    SUPER_RESTORE(
            "Super restore",
            63, 142.5,
            227, 223, 3000, 3004,
            3024, 3026, 3028, 3030
    ),
    ANTIPOISON_PLUS(
            "Antipoison+",
            68, 155,
            5935, 6049, 2998, 5942,
            5943, 5945, 5947, 5949
    ),
    ANTIFIRE(
            "Antifire",
            69, 157.5,
            227, 241, 2481, 2483,
            2452, 2454, 2456, 2458
    ),
    RANGING(
            "Ranging",
            72, 162.5,
            227, 245, 267, 109,
            2444, 169, 171, 173
    ),
    WEAPON_POISON_PLUS(
            "Weapon poison+",
            73, 165,
            5935, 223, 6016, 5936,
            -1, 5937, -1, -1
    ),
    MAGIC(
            "Magic",
            76, 172.5,
            227, 3138, 2481, 2483,
            3040, 3042, 3044, 3046
    ),
    ZAMORAK_BREW(
            "Zamorak brew",
            78, 175,
            227, 247, 269, 111,
            2450, 189, 191, 193
    ),
    ANTIPOISON_PLUS_PLUS(
            "Antipoison++",
            79, 177.5,
            5935, 6051, 259, 5951,
            5952, 5954, 5956, 5958
    ),
    SARADOMIN_BREW(
            "Saradomin brew",
            81, 180,
            227, 6693, 2998, 3002,
            6685, 6687, 6689, 6691
    ),
    WEAPON_POISON_PLUS_PLUS(
            "Weapon poison++",
            82, 190,
            5935, 6018, 2398, 5939,
            -1, 5940, -1, -1
    ),
    RECOVER_SPECIAL(
            "Recover special",
            84, 200,
            227, 5972, -1, 3018,
            15300, 15301, 15302, 15303
    ),
    SUPER_ANTIFIRE(
            "Super antifire",
            85, 210,
            227, 4621, -1, 2454,
            15304, 15305, 15306, 15307
    ),
    EXTREME_ATTACK(
            "Extreme attack",
            88, 220,
            227, 261, -1, 145,
            15308, 15309, 15310, 15311
    ),
    EXTREME_STRENGTH(
            "Extreme strength",
            89, 230,
            227, 267, -1, 157,
            15312, 15313, 15314, 15315
    ),
    EXTREME_DEFENCE(
            "Extreme defence",
            90, 240,
            227, 2481, -1, 163,
            15316, 15317, 15318, 15319
    ),
    EXTREME_MAGIC(
            "Extreme magic",
            91, 250,
            227, 9594, -1, 3042,
            15320, 15321, 15322, 15323
    ),
    EXTREME_RANGING(
            "Extreme ranging",
            92, 260,
            227, 12539, -1, 169,
            15324, 15325, 15326, 15327
    ),
    SUPER_PRAYER(
            "Super prayer",
            94, 270,
            227, 6812, -1, 139,
            15328, 15329, 15330, 15331
    ),
    PRAYER_RENEWAL(
            "Prayer renewal",
            94, 190,
            227, 21622, 21624, 21628,
            21630, 21632, 21634, 21636
    ),
    OVERLOAD(
            "Overload",
            -1, -1,
            -1, -1, -1, -1,
            15332, 15333, 15334, 15335
    ),
    NMZ_OVERLOAD(
            "NMZ Overload",
            -1, -1,
            -1, -1, -1, -1,
            22371, 22372, 22373, 22374
    ),
    JUJU_FISHING(
    		"Juju fishing potion",
    		70, 158,
    		19994, 19976, 19992, 20001,
    		20019, 20020, 20021, 20022
    ),
    JUJU_WOODCUTTING(
    		"Juju woodcutting potion",
    		71, 160,
    		19994, 19972, 19993, 20002,
    		20015, 20016, 20017, 20018
    ),
    JUJU_MINING(
    		"Juju mining potion",
    		74, 168,
    		19994, 19973, 19993, 20002,
    		20003, 20004, 20005, 20006
    ),
    JUJU_HUNTER(
    		"Juju hunter potion",
    		54, 123,
    		19994, 19975, 19989, 19998,
    		20023, 20024, 20025, 20026
    ),
    JUJU_AGILITY(
    		"Juju agility potion",
    		59, 135,
    		19994, 19974, 19990, 20000,
    		20027, 20028, 20029, 20030
    ),
    JUJU_FARM(
    		"Juju farming potion",
    		64, 146,
    		19994, 19980, 19991, 19999,
    		20011, 20012, 20013, 20014
    );




    public final String name;
    public final short primaryId, herbId, secondary;
    public final byte level;
    public final double xp;
    public final short unfinishedId;
    public final int[] ids;

    Potion(String name, int level, double xp, int primaryId, int secondary, int herbId, int unfinishedId, int... ids) {
        this.name = name;
        this.primaryId = (short) primaryId;
        this.herbId = (short) herbId;
        this.secondary = (short) secondary;
        this.level = (byte) level;
        this.xp = xp;
        this.unfinishedId = (short) unfinishedId;
        this.ids = ids;
    }

    public int getDoses(int id) {
        if(ids[0] == -1) {
			return 0;
		}
        for(int i = 0; i < ids.length; i++) {
            if(id == ids[i]) {
				return 4 - i;
			}
        }
        return 0;
    }

    /**
     * Static
     */

    public static Potion get(int fromId, int toId) {
        for(Potion p : values()) {
            if((p.primaryId == toId && p.herbId == fromId) || (p.primaryId == fromId && p.herbId == toId)
                    || (p.unfinishedId == toId && p.secondary == fromId) || (p.unfinishedId == fromId && p.secondary == toId)) {
				return p;
			}
        }
        return null;
    }

    public static Potion get(int potionId) {
        for(Potion p : values()) {
            for (int id : p.ids) {
                if(id == potionId) {
					return p;
				}
            }
        }
        return null;
    }

    public static final Potion[] ANTI_POISONS = {
            ANTIPOISON_PLUS_PLUS,
            ANTIPOISON_PLUS,
            SUPER_ANTIPOISON,
            ANTIPOISON,
            SANFEW_SERUM
    };

}
