package com.runescape.content.skills.cooking;

public enum CookData {
    /**
     * Fish
     */
    SHRIMPS(317, 315, 323, 34, 1, 30),
    SARDINE(327, 325, 369, 38, 1, 40),
    ANCHOVIES(321, 319, 323, 34, 1, 30),
    HERRING(345, 347, 357, 37, 5, 50),
    TROUT(335, 333, 343, 50, 15, 70),
    PIKE(349, 351, 343, 52, 20, 80),
    SALMON(331, 329, 343, 58, 25, 90),
    TUNA(359, 361, 367, 65, 30, 100),
    LOBSTER(377, 379, 381, 74, 40, 120),
    SWORDFISH(371, 373, 375, 86, 45, 140),
    MONKFISH(7944, 7946, 7948, 92, 62, 150),
    SHARK(383, 385, 387, 99, 80, 210),
    ROCKTAIL(15270, 15272, 15274, 99, 93, 225),
    /**
     * Misc
     */
    CRAYFISH(13435, 13433, 13437, 99, 1, 30),
    CRAB_MEAT(7518, 7521, 7520, 61, 31, 100);

    public final short rawId, cookId;
    public final short burnId;
    public final byte noBurnLevel;
    public final byte levelRequest;
    public final double xp;

    CookData(int rawId, int cookId, int burnId, int noBurnLevel, int levelRequest, double xp) {
        this.rawId = (short) rawId;
        this.cookId = (short) cookId;
        this.burnId = (short) burnId;
        this.noBurnLevel = (byte) noBurnLevel;
        this.levelRequest = (byte) levelRequest;
        this.xp = xp;
    }

    public static CookData get(int itemId) {
        for(CookData data : CookData.values()) {
            if(data.rawId == itemId)
                return data;
        }
        return null;
    }

}
