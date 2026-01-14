package com.runescape.content.activites;

import com.runescape.logic.npc.NPC;
import com.runescape.logic.player.Player;

public abstract class Controller {

    public final Player player;

    public Controller(Player player) {
        this.player = player;
    }

    public boolean isActive() {
        return false; //player.activeController == this;
    }

    /**
     * Abstract
     */

    public abstract void entered();

    public abstract void exited(boolean logout);

    public abstract boolean handleDeath();

    public abstract boolean canAttack(Player pVictim, boolean message);

    public abstract boolean canAttack(NPC nVictim, boolean message);

    public abstract boolean isTeleportAllowed(boolean dragonStone);

    public abstract boolean isSummoningAllowed();

    public abstract boolean checkActive();
}
