package com.runescape.content.skills;

import com.runescape.engine.tick.Tick;
import com.runescape.logic.item.PossesedItem;
import com.runescape.logic.player.Player;

public abstract class RSSkillTick extends Tick {

    protected final Player player;
    private boolean firstTick = true;

    public RSSkillTick(Player player) {
        super("skill", 1, Tick.TickPolicy.STRICT);
        this.player = player;
    }

    @Override
    public boolean execute() {
        if (!player.isConnected() || player.getPathProcessor().moving()) {
            stop(true);
            return false;
        }
        if (firstTick) {
            firstTick = false;
            super.setInterval(getInterval());
        }
        player.doAnimation(getAnimation());
        handleReward();
        
        if (shouldExpire()) {
            expire();
            stop(true);
            return false;
        }

        return true;
    }

    @Override
    public void onStart() {
        init();
    }

    private void handleReward() {
        if (!isPeriodicRewards()) {
            reward();
        } else {
            if (Math.random() <= getRewardFactor()) {
                reward();
            }
        }
    }

    public abstract void init();

    public abstract int getInterval();

    public abstract int getSkill();

    public abstract double getExperience();

    public abstract int getAnimation();

    public abstract boolean isPeriodicRewards();

    public abstract PossesedItem getReward();

    public abstract double getRewardFactor();

    public abstract void onReward();

    public abstract boolean shouldExpire();

    public abstract void expire();

    public abstract void stopped(boolean forceResetMasks);

    protected final boolean reward() {
        PossesedItem reward = getReward();
        if (reward == null || player.getInventory().add(reward.getId(), reward.getAmount())) {
            player.getLevels().addXP(getSkill(), getExperience());
            player.updateXPCounter();
            onReward();
            return true;
        }
        return false;
    }

    @Override
    public void stop() {
        stop(false);
    }

    public final void stop(boolean forceResetMasks) {
        super.stop();
        stopped(forceResetMasks);
    }
}
