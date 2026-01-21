package com.runescape.logic.player.commands;

import java.util.HashMap;
import java.util.Map;

import com.runescape.content.combat.Combat;
import com.runescape.logic.map.PathProcessor;
import com.runescape.logic.player.Player;
import com.runescape.logic.player.commands.impl.AnimationCommand;
import com.runescape.logic.player.commands.impl.BankCommand;
import com.runescape.logic.player.commands.impl.GraphicCommand;
import com.runescape.logic.player.commands.impl.InterfaceCommand;
import com.runescape.logic.player.commands.impl.ItemCommand;
import com.runescape.logic.player.commands.impl.MasterLevelCommand;
import com.runescape.logic.player.commands.impl.MasterSkillerCommand;
import com.runescape.logic.player.commands.impl.NpcSpawnCommand;
import com.runescape.logic.player.commands.impl.ObjectSpawnCommand;
import com.runescape.logic.player.commands.impl.SendSoundCommand;
import com.runescape.logic.player.commands.impl.SendStringCommand;
import com.runescape.logic.player.commands.impl.SendVarCommand;
import com.runescape.logic.player.commands.impl.ShopCommand;
import com.runescape.logic.player.commands.impl.SkillingInterfaceCommand;
import com.runescape.logic.player.commands.impl.TeleportCommand;
import com.runescape.logic.player.commands.impl.TestCommand;

/**
 * @author Seth Rogen
 */

public class CommandManager {
	
    private static final Map<String, CommandHandler> commands = new HashMap<>();
    
    static {
        register(new ItemCommand());
        register(new ShopCommand());
        register(new InterfaceCommand());
        register(new NpcSpawnCommand());
        register(new MasterLevelCommand());
        register(new TeleportCommand());
        register(new TestCommand());
        register(new SkillingInterfaceCommand());
        register(new AnimationCommand());
        register(new GraphicCommand());
        register(new ObjectSpawnCommand());
        register(new SendStringCommand());
        register(new SendVarCommand());
        register(new MasterSkillerCommand());
        register(new SendSoundCommand());
        register(new BankCommand());
    }
    
    private static void register(CommandHandler cmd) {
        commands.put(cmd.getName().toLowerCase(), cmd);
    }
    
	/**
	 * 
	 * @param player
	 * @param cmd
	 * @param args
	 * @param s
	 */
    public static void executeCommand(Player player, String commandQuery, String cmdName, String[] args, String s) {
	    if(player.getPathProcessor().getMoveSpeed() == PathProcessor.MOVE_SPEED_WALK) {
	        return;
	    }
	     CommandHandler cmd = commands.get(cmdName.toLowerCase());
	        if (cmd == null) {
	            player.sendMessage("Unknown command: " + cmdName);
	            return;
	        }
	        if (!cmd.canUse(player)) {
	            player.sendMessage("You don't have permission to use this command.");
	            return;
	        }
	        cmd.execute(player, args);
	}	
    /**
     * 
     * @param Checks for commands
     * @return
     */
	public boolean checkCombat(Player player) {
	    return player.isInPVP() && player.getCombat().inCombat();
	}
	
	public boolean isPlayer(Player player) {
	    return true;
	}
	
	public boolean checkInPVP(Player player) {
		return Combat.isInPVPZone(player);
	}

	public boolean checkMod(Player player) {
	    return player.isModerator();
	}

	public static boolean isAdmin(Player player) {
	    return player.isAdministrator();
	}

	public boolean checkMember(Player player) {
	    if(!player.isMember() && !player.isModerator()) {
	        player.onMembersOnlyFeature();
	        return false;
	    }
	    return true;
	}
}
