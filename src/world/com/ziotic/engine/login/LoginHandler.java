package com.ziotic.engine.login;

import com.ziotic.Static;
import com.ziotic.content.combat.Combat;
import com.ziotic.content.combat.Magic;
import com.ziotic.content.combat.Ranged;
import com.ziotic.content.cc.ClanManager;
import com.ziotic.logic.player.Player;

public class LoginHandler {

    public static void executeLogin(Player player) {
    	
    	player.getPathProcessor().updateHistory(player.getLocation());
    	player.setCoverage();

    	player.getLevels().refresh();

    	player.getInventory().setListening(true);
    	player.getEquipment().setListening(true);
        player.getBank().setListening(true);

    	player.getInventory().refresh();
    	player.getEquipment().refresh();

    	player.onChangedHP();

        Static.proto.sendRunEnergy(player);
        Static.proto.sendVarp(player, 173, player.isRunning() ? 1 : 0);
        player.updateXPCounter();

        player.setInPVP(Combat.isInPVPZone(player));
        Combat.updatePVPStatus(player);
        player.setInMulti(Combat.isInMultiZone(player));
        Static.proto.sendInterfaceShowConfig(player, 381, 0, !player.isInPVP());
        Static.proto.sendInterfaceShowConfig(player, 745, 1, !player.isInMulti());
        	
        player.getLevels().checkEquipmentForRequirements(false);
        	
        int value = 0;
    	value |= 30;
    	value |= 30 << 6;
    	value |= 30 << 12;
    	value |= 30 << 18;
    	value |= 30 << 24;
        Static.proto.sendVarp(player, 1583, value);

        Static.proto.sendPlayerOption(player, "Follow", 2, false);
        Static.proto.sendPlayerOption(player, "Trade with", 3, false);
        Static.proto.sendPlayerOption(player, "Req Assist", 4, false);
        	
        Magic.setMagic(player, player.getCombat().getMagic().getSpellBook());
        Static.proto.sendVarp(player, 172, player.getCombat().autoRetaliate() ? 0 : 1);
    	player.getCombat().getSpecialEnergy().update();
    	player.getCombat().getSpecialEnergy().onLogin();
    	player.getPrayerManager().sendBook(player);
    	Static.proto.sendInterfaceVariable(player, 181, 0);
        Static.proto.sendInterfaceVariable(player, 186, 6);
        Static.proto.sendVarp(player, 281, 1000);
        Static.proto.sendVarp(player, 43, player.getCombat().getWeapon().getIndex());
    		
    	Ranged.setWeapon(player);

        player.setCachedAppearanceBlock(Static.world.getPlayerUpdater().doApperanceBlock(player));
        player.getMasks().setAppearanceUpdate(true);
    	Static.proto.sendVarp(player, 1160, -1);
    	player.getMasks().setAnimation(-1, 0);

    	player.sendMessage("Welcome to Runescape.");

    	if(player.getAttributes().isSet("lastClan")) {
    	    ClanManager.joinChannel(player, player.getAttributes().get("lastClan"));
    	    player.getAttributes().unSet("lastClan");
    	}

    	if(player.isNoob()) {
    		player.initiateCharacterDesign();
    	}
    }
}
