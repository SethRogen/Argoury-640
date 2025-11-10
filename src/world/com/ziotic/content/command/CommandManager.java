package com.ziotic.content.command;

import java.util.HashMap;
import java.util.Map;

import com.ziotic.content.combat.Combat;
import com.ziotic.content.command.impl.AnimationCommand;
import com.ziotic.content.command.impl.GraphicCommand;
import com.ziotic.content.command.impl.InterfaceCommand;
import com.ziotic.content.command.impl.ItemCommand;
import com.ziotic.content.command.impl.MasterLevelCommand;
import com.ziotic.content.command.impl.NpcSpawnCommand;
import com.ziotic.content.command.impl.ObjectSpawnCommand;
import com.ziotic.content.command.impl.SendStringCommand;
import com.ziotic.content.command.impl.SendVarCommand;
import com.ziotic.content.command.impl.ShopCommand;
import com.ziotic.content.command.impl.SkillingInterfaceCommand;
import com.ziotic.content.command.impl.TeleportCommand;
import com.ziotic.content.command.impl.TestCommand;
import com.ziotic.logic.map.PathProcessor;
import com.ziotic.logic.player.Player;

/**
 * @author Seth Rogen
 */

public class CommandManager {
	
    private static final Map<String, CommandHandle> commands = new HashMap<>();
    
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
    }
    
    private static void register(CommandHandle cmd) {
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
	     CommandHandle cmd = commands.get(cmdName.toLowerCase());
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
