package com.runescape.content.skills;

import java.util.concurrent.ThreadLocalRandom;

public final class RSInterpolator {

    private static final int MAX_LEVEL = 99;
    private static final int MIN_LEVEL = 1;
    private static final int ROLL_RANGE = 256; // 0–255

    private RSInterpolator() {
        // utility class
    }

    /**
     * Calculates a Jagex-style interpolated success value.
     *
     * @param level      Player's current skill level
     * @param low        Success value at level 1 (0–255)
     * @param high       Success value at level 99 (0–255)
     * @return           Interpolated success threshold (0–255)
     */
    public static int successValue(int level, int low, int high) {
        level = clamp(level, MIN_LEVEL, MAX_LEVEL);
        int numerator = (level - MIN_LEVEL) * (high - low);
        int denominator = MAX_LEVEL - MIN_LEVEL;
        return low + (numerator / denominator);
    }

    /**
     * Rolls success/failure using a Jagex-style random check.
     *
     * @param level      Player's current skill level
     * @param low        Success value at level 1
     * @param high       Success value at level 99
     * @return           true if success, false if failure
     */
    public static boolean rollSuccess(int level, int low, int high) {
        int success = successValue(level, low, high);
        int roll = ThreadLocalRandom.current().nextInt(ROLL_RANGE);
        return roll < success;
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
    
    private double interpolate(double low, double high, int skill) {
        double maxLevel = 99.0; // max woodcutting level
        return low + (high - low) * (skill / maxLevel);
    }
    
    public static int interpolate(int low, int high, int level) {
        double numerator = low * (99 - level) / 98.0;
        double denominator = Math.floor(high * (level - 1) / 98.0);
        return (int) Math.floor(numerator + denominator + 1);
    }
    
}
