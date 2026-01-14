package com.runescape.adapter.protocol.cache.format;

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

import com.runescape.Static;
import com.runescape.logic.player.Player;

public class VarbitDefinitonAdapter {
	
	 private static final ConcurrentHashMap<Integer, VarbitDefinitonAdapter> varpbitDefs = new ConcurrentHashMap<Integer, VarbitDefinitonAdapter>();
	 
	    public int id;
	    public int baseVar;
	    public int startBit;
	    public int endBit;
	    
		private Player player;
	    private static int[] values;
	    
	    private static final int[] masklookup = new int[32];

		static {
			int i = 2;
			for (int i2 = 0; i2 < 32; i2++) {
				masklookup[i2] = i - 1;
				i += i;
			}
		}
	
		
		public VarbitDefinitonAdapter(Player player) {
			this.player = player;
			values = new int[Static.rs2Cache.getIndex(22).getLength() * 1024];
		}
	
	    public static final VarbitDefinitonAdapter getClientVarpBitDefinitions(Player player, int id) {
	    VarbitDefinitonAdapter script = varpbitDefs.get(id);
		if (script != null)
		    return script;
		byte[] data = Static.rs2Cache.getIndex(22).getArchivedFile(id >>> 10, id & 0x3ff);
		System.out.println("There are currently: " + Static.rs2Cache.getIndex(22).getLength() * 0x3ff + " bitConfigs.");
		script = new VarbitDefinitonAdapter(player);
		script.id = id;
		if (data != null)
		    script.readValueLoop(ByteBuffer.wrap(data));
		varpbitDefs.put(id, script);
		return script;
	
	    }
	    
		public static void sendVar(Player player, int id, int value) {
			sendVar(player, id, value, false);
		}
	    
	    private void readValueLoop(ByteBuffer buffer) {
		for (;;) {
		    int opcode = buffer.get();
		    if (opcode == 0)
			break;
		    readValues(buffer, opcode);
			}
	    }
		public static int getValue(int id) {
			return values[id];
		}
	    private void readValues(ByteBuffer buffer, int opcode) {
		if (opcode == 1) {
		    baseVar = buffer.getShort();
		    startBit = buffer.get();
		    endBit = buffer.get();
			}
	    }
		private static void sendVar(Player player, int id, int value, boolean force) {
			if (id < 0 || id >= values.length) // temporarly
				return;
			if (force || values[id] == value)
				return;
			setVar(id, value);
			sendClientVarp(player, id);
		}
	
		public static void setVar(int id, int value) {
			if (id == -1) // temporarly
				return;
			values[id] = value;
		}
		public static void sendVarBit(Player player, int id, int value) {
			setVarBit(player, id, value, 0x1);
		}
		
		private static void setVarBit(Player player, int id, int value, int flag) {
			if (id == -1) // temporarly
				return;
			VarbitDefinitonAdapter defs = getClientVarpBitDefinitions(player, id);
			int mask = masklookup[defs.endBit - defs.startBit];
			if (value < 0 || value > mask)
				value = 0;
			mask <<= defs.startBit;
			int varpValue = (values[defs.baseVar] & (mask ^ 0xffffffff) | value << defs.startBit & mask); // failing here <<
			System.out.println("Load ConfigByFile [Value] = " + varpValue);
			if ((flag & 0x2) != 0 || varpValue != values[defs.baseVar]) {
				setVar(defs.baseVar, varpValue);
				if ((flag & 0x1) != 0)
					sendClientVarp(player, defs.baseVar);
			}
		}
		
		private static void sendClientVarp(Player player, int id) {
			Static.proto.sendInterfaceConfig(player, id, values[id]);
		}
}
