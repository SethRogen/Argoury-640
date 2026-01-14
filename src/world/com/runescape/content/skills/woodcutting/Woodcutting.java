package com.runescape.content.skills.woodcutting;

import java.util.Random;

import com.runescape.Static;
import com.runescape.content.handler.ActionHandler;
import com.runescape.content.handler.ActionHandlerSystem;
import com.runescape.content.handler.ObjectOptionHandler;
import com.runescape.content.skills.HarvestingTick;
import com.runescape.content.skills.RSInterpolator;
import com.runescape.engine.event.DelayedEvent;
import com.runescape.logic.World;
import com.runescape.logic.item.ItemDefinition;
import com.runescape.logic.item.PossesedItem;
import com.runescape.logic.map.Tile;
import com.runescape.logic.object.GameObject;
import com.runescape.logic.player.Levels;
import com.runescape.logic.player.Player;
import com.runescape.logic.utility.Utility;
import com.runescape.logic.utility.rsconfigs.RSJingle;

public class Woodcutting implements ActionHandler, ObjectOptionHandler {

    private static final int[] BIRD_NESTS = {5070, 5071, 5072, 5073, 5074, 5075, 7413, 11966};
    private static final Random RANDOM = new Random();

    public static class WoodcuttingTick extends HarvestingTick {

        private final Hatchet axe;
        private final RSTreeType tree;
        private boolean treeDepleted = false;

        public WoodcuttingTick(Player player, GameObject obj) {
            super(player, obj);
            this.tree = RSTreeType.getByObjectId(obj.getId());
            this.axe = getBestAxe(player);
        }

        @Override
        public void init() {
            if (tree == null || axe == null) {
                if (axe == null) {
                    player.sendMessage("You don’t have a hatchet to chop this tree.");
                }
                stop();
                return;
            }
            player.sendMessage(tree == RSTreeType.IVY ? "You swing your hatchet at the ivy." : "You swing your hatchet at the tree.");
            player.doAnimation(tree == RSTreeType.IVY ?  axe.getIvyAnimation() : axe.getAnimation());
        }

        @Override
        public int getInterval() {
            return 4;
        }

        @Override
        public int getSkill() {
            return Levels.WOODCUTTING;
        }

        @Override
        public double getExperience() {
            return 0;
        }

        @Override
        public int getAnimation() {
            return tree == RSTreeType.IVY ?  axe.getIvyAnimation() : axe.getAnimation();
        }

        @Override
        public boolean isPeriodicRewards() {
            return true;
        }

        @Override
        public PossesedItem getReward() {
            return tree.getLogId() != -1 ? new PossesedItem(tree.getLogId(), 0) : null;
        }

        @Override
        public double getRewardFactor() {
            return axe.getRatio();
        }

        @Override
        public void onReward() {
            if (tree == RSTreeType.IVY) {
                player.getLevels().addXP(Levels.WOODCUTTING, tree.getXp());
                player.sendMessage("You successfully chop away some ivy.");
            } else if (tree.getLogId() != -1) {
                boolean success = true;
                if (tree.getHighChance() > 0) {
                    int low = (int) (tree.getLowChance() * axe.getRatio());
                    int high = (int) (tree.getHighChance() * axe.getRatio());
                    success = RSInterpolator.rollSuccess(player.getLevels().getLevel(Levels.WOODCUTTING), low, high);
                }
                if (success) {
                    player.getInventory().add(new PossesedItem(tree.getLogId(), 1));
                    player.getLevels().addXP(Levels.WOODCUTTING, tree.getXp());
                    ItemDefinition reward = ItemDefinition.forId(tree.getLogId());
                    player.sendMessage("You get some " + reward.name.toLowerCase());
                }
            }
            /** Bird nests **/
            if (Utility.random(256) == 1) {
                int nest = BIRD_NESTS[RANDOM.nextInt(BIRD_NESTS.length)];
                Static.world.getGroundItemManager().add(nest, 1, player.getLocation(), player.getProtocolName(), false);
                player.sendMessage(tree == RSTreeType.IVY
                        ? "<col=FF0000>A bird's nest falls out of the ivy.</col>"
                        : "<col=FF0000>A bird's nest falls out of the tree.</col>");
                player.playJingle(RSJingle.BIRD_NEST_FALLEN, 0);
            }
            /**
             * TODO: Redo Depletion where Jagex used life min / max per tree so we need to add this and make it global
             */
            if (tree.getDepleteChance() == 0 || Utility.random(tree.getDepleteChance()) == 0) {
                treeDepleted = true;
                expire();
                stop();
            }
        }


        @Override
        public boolean shouldExpire() {
        	return !canChop(player, tree) || treeDepleted;
        }

        @Override
        public void expire() {
            handleTreeDeplete(obj, tree);
        }

        @Override
        public void stopped(boolean forceResetMasks) {
            player.doAnimation(-1);
        }

        private boolean canChop(Player player, RSTreeType tree) {
            Hatchet axe = getBestAxe(player);
            if (axe == null) {
                player.sendMessage("You don’t have a hatchet to chop this tree.");
                return false;
            }
            if (player.getLevels().getLevel(Levels.WOODCUTTING) < tree.getLevel()) {
                player.sendMessage(tree == RSTreeType.IVY ? "You need a Woodcutting level of " + tree.getLevel() + " to chop ivy." : "You need a Woodcutting level of " + tree.getLevel() + " to chop this tree.");
                return false;
            }
            if (player.getInventory().isFull()) {
                player.sendMessage("Your inventory is too full to hold any more logs.");
                return false;
            }
            return true;
        }

        private Hatchet getBestAxe(Player player) {
            Hatchet best = null;
            for (Hatchet h : Hatchet.values()) {
                if (player.getLevels().getLevel(Levels.WOODCUTTING) >= h.getLevel()
                        && (player.getInventory().contains(h.getItemId()) || player.getEquipment().contains(h.getItemId()))) {
                    if (best == null || h.getRatio() > best.getRatio()) {
                        best = h;
                    }
                }
            }
            return best;
        }

        private void handleTreeDeplete(GameObject obj, RSTreeType tree) {
            final int origTreeId = obj.getId();
            final int origTreeType = obj.getType();
            final int origTreeDir = obj.getDirection();
            final Tile origTreeLoc = obj.getLocation();
            int stumpId = tree.getStumpIds();
            if (stumpId != -1) {
                Static.world.getObjectManager().add(stumpId, origTreeLoc, origTreeType, origTreeDir);
            }
            int respawnTime = tree.getRespawnTime() * 1000; // convert to milliseconds if needed
            Static.engine.submit(new DelayedEvent(respawnTime) {
                @Override
                public void run() {
                    Static.world.getObjectManager().add(origTreeId, origTreeLoc, origTreeType, origTreeDir);
                }
            });
        }

    }

	@Override
	public void handleObjectOption1(Player player, GameObject obj) {
		player.registerTick(new WoodcuttingTick(player, obj));
		
	}

	@Override
	public void handleObjectOption2(Player player, GameObject obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleObjectOption3(Player player, GameObject obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(ActionHandlerSystem system) throws Exception {
        for (RSTreeType t : RSTreeType.values()) {
            system.registerObjectOptionHandler(t.getObjectIds(), this);
        }
		
	}

	@Override
	public boolean explicitlyForMembers() {
		// TODO Auto-generated method stub
		return false;
	}
}
