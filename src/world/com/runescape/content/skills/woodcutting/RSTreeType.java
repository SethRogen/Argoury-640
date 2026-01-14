package com.runescape.content.skills.woodcutting;

import com.runescape.logic.utility.rsconfigs.RSItems;
import com.runescape.logic.utility.rsconfigs.RSObjects;

import java.util.ArrayList;
import java.util.List;

public enum RSTreeType {

    TREE(1, 25.0, RSItems.LOGS, 0, 59, 64, 200, 1342, new int[]{
            RSObjects.TREE_1276, RSObjects.TREE_1277, RSObjects.TREE_1278, RSObjects.TREE_1280,
            RSObjects.TREE_1330, RSObjects.TREE_1331, RSObjects.TREE_1332, RSObjects.TREE_2410,
            RSObjects.TREE_2411, RSObjects.TREE_3033, RSObjects.TREE_3034, RSObjects.TREE_3036,
            RSObjects.TREE_3879, RSObjects.TREE_3881, RSObjects.TREE_3882, RSObjects.TREE_3883,
            RSObjects.TREE_5904, RSObjects.TREE_14308, RSObjects.TREE_14309, RSObjects.TREE_37477,
            RSObjects.TREE_37478, RSObjects.TREE_37652, RSObjects.TREE_38760, RSObjects.TREE_38782,
            RSObjects.TREE_38783, RSObjects.TREE_38784, RSObjects.TREE_38785, RSObjects.TREE_38786,
            RSObjects.TREE_38787, RSObjects.TREE_38788, RSObjects.TREE_61190, RSObjects.TREE_61191,
            RSObjects.TREE_61192, RSObjects.TREE_61193, RSObjects.TREE_65535, RSObjects.DYING_TREE,
            RSObjects.DEAD_TREE, RSObjects.DEAD_TREE_1283, RSObjects.DEAD_TREE_1284, RSObjects.DEAD_TREE_1285,
            RSObjects.DEAD_TREE_1286, RSObjects.DEAD_TREE_1289, RSObjects.DEAD_TREE_1291, RSObjects.DEAD_TREE_1365,
            RSObjects.DEAD_TREE_1383, RSObjects.DEAD_TREE_1384, RSObjects.DEAD_TREE_11866, RSObjects.DEAD_TREE_32294,
            RSObjects.DEAD_TREE_37481, RSObjects.DEAD_TREE_37482, RSObjects.DEAD_TREE_37483, RSObjects.DEAD_TREE_41713,
            RSObjects.DEAD_TREE_47594, RSObjects.DEAD_TREE_47596, RSObjects.DEAD_TREE_47598, RSObjects.DEAD_TREE_47600,
            RSObjects.SWAMP_TREE, RSObjects.SWAMP_TREE_3300, RSObjects.SWAMP_TREE_9354, RSObjects.SWAMP_TREE_9355,
            RSObjects.SWAMP_TREE_9366, RSObjects.SWAMP_TREE_9387, RSObjects.SWAMP_TREE_9388, RSObjects.SWAMP_TREE_58108,
            RSObjects.SWAMP_TREE_58109, RSObjects.SWAMP_TREE_58121, RSObjects.SWAMP_TREE_58135, RSObjects.SWAMP_TREE_58140,
            RSObjects.SWAMP_TREE_58141, RSObjects.SWAMP_TREE_58142, RSObjects.EVERGREEN, RSObjects.EVERGREEN_1316,
            RSObjects.EVERGREEN_1318, RSObjects.EVERGREEN_1319, RSObjects.EVERGREEN_54778, RSObjects.EVERGREEN_54787,
            RSObjects.EVERGREEN_57932, RSObjects.EVERGREEN_57934, RSObjects.EVERGREEN_57964
    }),
    ACHEY(1, 25.0, RSItems.ACHEY_TREE_LOGS, 0, 59, 64, 200, 1342 /** FIND STUMP ID**/,  new int[]{
            RSObjects.ACHEY_TREE, RSObjects.ACHEY_TREE_29088, RSObjects.ACHEY_TREE_29089, RSObjects.ACHEY_TREE_29090
    }),
    OAK(15, 37.5, RSItems.OAK_LOGS, 8, 14, 32, 100, 1356, new int[]{
            RSObjects.OAK, RSObjects.OAK_3037, RSObjects.OAK_11999, RSObjects.OAK_37479, RSObjects.OAK_38731, RSObjects.OAK_38732
    }),
    WILLOW(30, 67.5, RSItems.WILLOW_LOGS, 8, 14, 16, 50, 5554, new int[]{
            RSObjects.WILLOW, RSObjects.WILLOW_142, RSObjects.WILLOW_2210, RSObjects.WILLOW_2372,
            RSObjects.WILLOW_37480, RSObjects.WILLOW_38616, RSObjects.WILLOW_38627, RSObjects.WILLOW_58006
    }),
    TEAK(35, 85.0, RSItems.TEAK_LOGS, 8, 15, 15, 46, 1342, new int[]{
            RSObjects.TEAK, RSObjects.TEAK_15062, RSObjects.TEAK_46275
    }),
    MAPLE(45, 100.0, RSItems.MAPLE_LOGS, 8, 59, 8, 25, 7400, new int[]{
            RSObjects.MAPLE_TREE_1307, RSObjects.MAPLE_TREE_4674, RSObjects.MAPLE_TREE_46277, RSObjects.MAPLE_TREE_51843
    }),
    HOLLOW(45, 82.0, RSItems.BARK, 8, 44, 18, 26, 1342, new int[]{
            RSObjects.HOLLOW_TREE, RSObjects.HOLLOW_TREE_2289, RSObjects.HOLLOW_TREE_4060
    }),
    MAHOGANY(50, 125.0, RSItems.MAHOGANY_LOGS, 8, 14, 8, 25, 1342, new int[]{
            RSObjects.MAHOGANY, RSObjects.MAHOGANY_46274
    }),
    YEW(60, 175.0, RSItems.YEW_LOGS, 8, 99, 4, 12, 7402, new int[]{
            RSObjects.YEW, RSObjects.YEW_12000, RSObjects.YEW_38755, RSObjects.YEW_38758, RSObjects.YEW_38759
    }),
    IVY(68, 332.5, -1, 8, 36, 7, 11, 46319, new int[]{
            RSObjects.IVY, RSObjects.IVY_670, RSObjects.IVY_673, RSObjects.IVY_675,
            RSObjects.IVY_46324, RSObjects.IVY_46322, RSObjects.IVY_46320, RSObjects.IVY_46318
    }),
    MAGIC(75, 250.0, RSItems.MAGIC_LOGS, 8, 199, 6, 6, 7401, new int[]{
            RSObjects.MAGIC_TREE_1306, RSObjects.MAGIC_TREE_37823
    });

    private final int level;
    private final double xp;
    private final int logId;
    private final int depleteChance;
    private final int respawnTime;
    private final int lowChance;
    private final int highChance;
    private final int stumpIds;
    private final int[] objectIds;

    RSTreeType(int level, double xp, int logId, int depleteChance, int respawnTime, int lowChance, int highChance, int stumpIds, int[] objectIds) {
        this.level = level;
        this.xp = xp;
        this.logId = logId;
        this.depleteChance = depleteChance;
        this.respawnTime = respawnTime;
        this.lowChance = lowChance;
        this.highChance = highChance;
        this.stumpIds = stumpIds;
        this.objectIds = objectIds;
    }

    public int getLevel() {
        return level;
    }

    public double getXp() {
        return xp;
    }

    public int getLogId() {
        return logId;
    }

    public int getDepleteChance() {
        return depleteChance;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public int getLowChance() {
        return lowChance;
    }

    public int getHighChance() {
        return highChance;
    }
    public int getStumpIds() {
        return stumpIds;
    }
    public int[] getObjectIds() {
        return objectIds;
    }

    /**
     * Utility method to find a TreeType by object ID.
     */
    public static RSTreeType getByObjectId(int objectId) {
        for (RSTreeType tree : RSTreeType.values()) {
            for (int id : tree.objectIds) {
                if (id == objectId) return tree;
            }
        }
        return null;
    }

    /**
     * Get all object IDs for all tree types.
     */
    public static List<Integer> getAllObjectIds() {
        List<Integer> allIds = new ArrayList<>();
        for (RSTreeType tree : RSTreeType.values()) {
            for (int id : tree.objectIds) {
                allIds.add(id);
            }
        }
        return allIds;
    }
}
