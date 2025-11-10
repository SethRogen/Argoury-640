package com.ziotic.content.skill;

import java.util.Random;

/**
 * @author Seth Rogen
 */
public class SkillManager {

    private static final Random RANDOM = new Random();

    /**
     * Linearly interpolates between two integer chances based on level.
     */
    public static int interpolate(int value, int minChance, int maxChance, int minLvl, int maxLvl) {
        return minChance + (maxChance - minChance) * (value - minLvl) / (maxLvl - minLvl);
    }

    /**
     * Linearly interpolates between two double chances based on level.
     */
    public static double interpolate(int value, double minChance, double maxChance, int minLvl, int maxLvl) {
        return minChance + (maxChance - minChance) * (value - minLvl) / (double)(maxLvl - minLvl);
    }

    /**
     * Returns true or false depending on whether a random roll falls within the interpolated chance.
     */
    public static boolean interpolateChance(int value, int minChance, int maxChance, int minLvl, int maxLvl, int cap) {
        return RANDOM.nextInt(cap) <= interpolate(value, minChance, maxChance, minLvl, maxLvl);
    }

    /**
     * Performs a weighted interpolation between low and high values based on level.
     */
    public static int interpolate(int low, int high, int level) {
        return (int) Math.floor(low * (99 - level) / 98.0 + Math.floor(high * (level - 1) / 98.0) + 1);
    }
    
    
}

