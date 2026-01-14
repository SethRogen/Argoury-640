package com.runescape.logic.player;

import com.runescape.Static;
import com.runescape.adapter.protocol.cache.RS2CacheFileAdapter;
import com.runescape.adapter.protocol.cache.format.VarbitDefinitonAdapter;

public class Varp {

	private static final int[] BIT_SIZES = new int[32];

	static {
		int i = 2;
		for (int i2 = 0; i2 < 32; i2++) {
			BIT_SIZES[i2] = i - 1;
			i += i;
		}
	}

	private int[] values;
	private Player player;

	public Varp(Player player) {
		this.player = player;
		//values = new int[Cache.STORE.getIndexes()[2].getLastFileId(16) + 1];
		values = new int[Static.rs2Cache.getIndex(2).getLength() + 1];
	}

	public void sendVar(int id, int value) {
		sendVar(id, value, false);
	}

	public void forceSendVar(int id, int value) {
		sendVar(id, value, true);
	}

	private void sendVar(int id, int value, boolean force) {
		if (id < 0 || id >= values.length) // temporarly
			return;
		if (force || values[id] == value)
			return;
		setVar(id, value);
		sendClientVarp(id);
	}

	public void setVar(int id, int value) {
		if (id == -1) // temporarly
			return;
		values[id] = value;
	}

	public int getValue(int id) {
		return values[id];
	}

	public void forceSendVarBit(Player player, int id, int value) {
		setVarBit(player, id, value, 0x1 | 0x2);
	}

	public void sendVarBit(Player player, int id, int value) {
		setVarBit(player, id, value, 0x1);
	}

	public void setVarBit(Player player, int id, int value) {
		setVarBit(player, id, value, 0);
	}

	public int getBitValue(Player player, int id) {
		VarbitDefinitonAdapter defs = VarbitDefinitonAdapter
				.getClientVarpBitDefinitions(player, id);
		return values[defs.baseVar] >> defs.startBit
				& BIT_SIZES[defs.endBit - defs.startBit];
	}

	private void setVarBit(Player player, int id, int value, int flag) {
		if (id == -1)
			return;
		VarbitDefinitonAdapter defs = VarbitDefinitonAdapter.getClientVarpBitDefinitions(player, id);
		int mask = BIT_SIZES[defs.endBit - defs.startBit];
		if (value < 0 || value > mask)
			value = 0;
		mask <<= defs.startBit;
		int varpValue = (values[defs.baseVar] & (mask ^ 0xffffffff) | value << defs.startBit & mask);
		if ((flag & 0x2) != 0 || varpValue != values[defs.baseVar]) {
			setVar(defs.baseVar, varpValue);
			if ((flag & 0x1) != 0)
				sendClientVarp(defs.baseVar);
		}
	}
	
	private void sendClientVarp(int id) {
		Static.proto.sendConfig(player, id, values[id]);
	}
}
