package com.runescape.adapter.protocol.cache.format;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.runescape.Static;
import com.runescape.logic.utility.Utility;


public final class ClientScriptMapAdapter {

    @SuppressWarnings("unused")
    private char aChar6337;
    @SuppressWarnings("unused")
    private char aChar6345;
    private String defaultStringValue;
    private int defaultIntValue;
    private HashMap<Long, Object> values;

    private static final ConcurrentHashMap<Integer, ClientScriptMapAdapter> interfaceScripts = new ConcurrentHashMap<Integer, ClientScriptMapAdapter>();

    public static void main(String[] args) throws IOException {
	//Cache.init();
	//MusicHints.init();
	ClientScriptMapAdapter names = ClientScriptMapAdapter.getMap(1345);
	ClientScriptMapAdapter hint1 = ClientScriptMapAdapter.getMap(952);
	System.out.println(hint1);
	for (Object v : names.values.values()) {
	    int key = (int) ClientScriptMapAdapter.getMap(1345).getKeyForValue(v);
	    int id = ClientScriptMapAdapter.getMap(1351).getIntValue(key);
	   // String hint = MusicHints.getHint(id);
	  //  System.out.println(id + ", " + v + "; " + hint + ", ");
	}
    }

    public static final ClientScriptMapAdapter getMap(int scriptId) {
	ClientScriptMapAdapter script = interfaceScripts.get(scriptId);
	if (script != null)
	    return script;
	byte[] data = Static.rs2Cache.getIndex(17).getArchivedFile(scriptId >>> 0xba9ed5a8, scriptId & 0xff);
	script = new ClientScriptMapAdapter();
	if (data != null)
	    script.readValueLoop(ByteBuffer.wrap(data));
	interfaceScripts.put(scriptId, script);
	return script;

    }

    public int getDefaultIntValue() {
	return defaultIntValue;
    }

    public String getDefaultStringValue() {
	return defaultStringValue;
    }

    public HashMap<Long, Object> getValues() {
	return values;
    }

    public Object getValue(long key) {
	if (values == null)
	    return null;
	return values.get(key);
    }

    public long getKeyForValue(Object value) {
	for (Long key : values.keySet()) {
	    if (values.get(key).equals(value))
		return key;
	}
	return -1;
    }

    public int getSize() {
	if (values == null)
	    return 0;
	return values.size();
    }

    public int getIntValue(long key) {
	if (values == null)
	    return defaultIntValue;
	Object value = values.get(key);
	if (value == null || !(value instanceof Integer))
	    return defaultIntValue;
	return (Integer) value;
    }
    
    public int getKeyIndex(long key) {
	if (values == null)
	    return -1;
	int i = 0;
	for(long k : values.keySet()) {
	    if(k == key)
		return i;
	    i++;
	}
	return -1;
    }
    
    public int getIntValueAtIndex(int i) {
	if (values == null)
	    return -1;
	return 	(int) values.values().toArray()[i];
    }

    public String getStringValue(long key) {
	if (values == null)
	    return defaultStringValue;
	Object value = values.get(key);
	if (value == null || !(value instanceof String))
	    return defaultStringValue;
	return (String) value;
    }

    private void readValueLoop(ByteBuffer buffer) {
	for (;;) {
	    int opcode = buffer.get();
	    if (opcode == 0)
		break;
	    readValues(buffer, opcode);
	}
    }

    private void readValues(ByteBuffer buffer, int opcode) {
	if (opcode == 1)
	    aChar6337 = Utility.method2782((byte) buffer.get());
	else if (opcode == 2)
	    aChar6345 = Utility.method2782((byte) buffer.get());
	else if (opcode == 3)
	    defaultStringValue = buffer.toString();
	else if (opcode == 4)
	    defaultIntValue = buffer.getInt();
	else if (opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
	    int count = buffer.getShort();
	    int loop = opcode == 7 || opcode == 8 ? buffer.getShort() : count;
	    if (values == null)
		values = new HashMap<Long, Object>(Utility.getHashMapSize(count));
	    for (int i = 0; i < loop; i++) {
		int key = opcode == 7 || opcode == 8 ? buffer.getShort() : buffer.getInt();
		Object value = opcode == 5 || opcode == 7 ? buffer.toString() : buffer.getInt();
		values.put((long) key, value);
	    }
	}
    }

    private ClientScriptMapAdapter() {
	defaultStringValue = "null";
    }
}
