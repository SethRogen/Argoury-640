package com.runescape.content.combat;

import com.runescape.Static;
import com.runescape.content.combat.Combat.ActionType;
import com.runescape.content.prayer.PrayerManager;
import com.runescape.logic.Entity;
import com.runescape.logic.World;
import com.runescape.logic.item.PossesedItem;
import com.runescape.logic.npc.NPC;
import com.runescape.logic.player.Player;

public class SpecialAttacks {

    public static boolean handleSpecial(Player player, Entity victim, Combat combat, PossesedItem weapon, ActionType actionType, int defAnim) {
        try {
            int reqSpecAmt = getReqSpecialAmount(weapon.getId());
            if (reqSpecAmt > combat.getSpecialEnergy().getAmount()) {
                Static.proto.sendMessage(player, "Not enough special energy left.");
                return false;
            }
            combat.getSpecialEnergy().decrease(reqSpecAmt);
            combat.getSpecialEnergy().update();
            double distance = player.getLocation().distance(victim.getLocation());
            switch (weapon.getId()) {
                case 14484: // Dragon Claws
                    combat.executeAnimation(10961, 0, true, false);
                    combat.executeGraphics(1950, 100 << 16, 0);
                    double accuracy = 2.2;
                    double strength = 1.0;
                    int splatDelay = 20;
                    for (int i = 0; i < 4; i++) {
                        accuracy += 0.1;
                        switch (i) {
                            case 1: splatDelay += 10; break;
                            case 2: splatDelay += 5; break;
                            case 3: splatDelay += 5; break;
                        }
                        int hit = combat.hit(victim, actionType, true, strength, accuracy, null, 1, null, new int[]{splatDelay}, new int[]{splatDelay});
                        if (hit != 0) {
                            switch (i) {
                                case 0: doDragonClawsSpec(combat, victim, actionType, 3, hit); break;
                                case 1: doDragonClawsSpec(combat, victim, actionType, 2, hit); break;
                                case 2: doDragonClawsSpec(combat, victim, actionType, 1, hit); break;
                            }
                            break;
                        }
                        splatDelay += 5;
                    }
                    victim.getCombat().executeAnimation(defAnim, 0, false, false);
                    return true;

                case 1215: case 1231: case 5680: case 5698:
                case 13465: case 13467: case 13976: case 13978: // Dragon dagger
                    combat.executeAnimation(1062, 0, true, false);
                    combat.executeGraphics(252, 100 << 16, 55);
                    combat.hit(victim, actionType, true, 1.1, 1.3, null, 2, null, new int[]{20, 25}, new int[]{0});
                    victim.getCombat().executeAnimation(defAnim, 0, false, false);
                    return true;

                case 11694: // AGS
                    combat.executeAnimation(7074, 0, true, false);
                    combat.executeGraphics(1222, 0, 55);
                    combat.hit(victim, actionType, true, 1.25, 1.3, null, 1, null, new int[]{20}, new int[]{0});
                    victim.getCombat().executeAnimation(defAnim, 0, false, false);
                    return true;

                case 861: // Magic Shortbow
                    combat.executeAnimation(1074, 0, true, false);
                    combat.executeGraphics(256, 0, 96);
                    combat.executeGraphics(256, 30, 96);
                    combat.hit(victim, actionType, true, 1.0, 0.9, null, 2, null, new int[]{20 + 5 + (int) distance * 5, 50 + 5 + (int) distance * 5}, new int[]{10, 25});
                    Static.world.sendProjectile(player, victim, 249, player.getLocation(), victim.getLocation(), 44, 41, 5, 20, 15, 0, player.getSize());
                    Static.world.sendProjectile(player, victim, 249, player.getLocation(), victim.getLocation(), 44, 41, 5, 50, 15, 0, player.getSize());
                    return true;

                case 4587: // Dragon Scimitar
                    combat.executeAnimation(12031, 0, true, false);
                    combat.executeGraphics(2118, 0, 55);
                    int damage = combat.hit(victim, actionType, true, 1, 1.2, null, 1, null, new int[]{20}, new int[]{0});
                    if (damage > 0) {
                        PrayerManager.hitByDragonScimitar(victim);
                    }
                    return true;

                default:
                    return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getReqSpecialAmount(int id) {
        switch (id) {
            case 11694: case 14484: return 50;
            case 1215: case 1231: case 5680: case 5698: case 13465: case 13467: case 13976: return 25;
            case 11235: return 65;
            case 861: case 4587: return 55;
            default: return 50;
        }
    }

    private static void doDragonClawsSpec(Combat combat, Entity victim, ActionType actionType, int hitAmount, int damage) {
        switch(hitAmount) {
            case 3: {
                int hit2 = damage / 2;
                int hit3 = World.getRandom(hit2);
                int hit4 = hit2 - hit3;
                combat.hit(victim, actionType, true, 1.0, 1.0, null, 1, new int[]{hit2}, new int[]{30}, new int[]{30});
                combat.hit(victim, actionType, true, 1.0, 1.0, null, 1, new int[]{hit3}, new int[]{35}, new int[]{35});
                combat.hit(victim, actionType, true, 1.0, 1.0, null, 1, new int[]{hit4}, new int[]{40}, new int[]{40});
                break;
            }
            case 2: {
                int hit3 = damage / 2;
                int hit4 = damage / 2;
                combat.hit(victim, actionType, true, 1.0, 1.0, null, 1, new int[]{hit3}, new int[]{35}, new int[]{35});
                combat.hit(victim, actionType, true, 1.0, 1.0, null, 1, new int[]{hit4}, new int[]{40}, new int[]{40});
                break;
            }
            case 1:
                combat.hit(victim, actionType, true, 1.5, 2.3, null, 1, null, new int[]{40}, new int[]{40});
                break;
        }
    }
}
