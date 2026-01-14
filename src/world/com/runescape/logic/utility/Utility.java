package com.runescape.logic.utility;

import java.util.concurrent.ThreadLocalRandom;

public class Utility {
	
	public static char[] aCharArray6385 = { '\u20ac', '\0', '\u201a', '\u0192',
			'\u201e', '\u2026', '\u2020', '\u2021', '\u02c6', '\u2030',
			'\u0160', '\u2039', '\u0152', '\0', '\u017d', '\0', '\0', '\u2018',
			'\u2019', '\u201c', '\u201d', '\u2022', '\u2013', '\u2014',
			'\u02dc', '\u2122', '\u0161', '\u203a', '\u0153', '\0', '\u017e',
			'\u0178' };
	
	
	public static char method2782(byte value) {
		int byteChar = 0xff & value;
		if (byteChar == 0)
			throw new IllegalArgumentException("Non cp1252 character 0x"
					+ Integer.toString(byteChar, 16) + " provided");
		if ((byteChar ^ 0xffffffff) <= -129 && byteChar < 160) {
			int i_4_ = aCharArray6385[-128 + byteChar];
			if ((i_4_ ^ 0xffffffff) == -1)
				i_4_ = 63;
			byteChar = i_4_;
		}
		return (char) byteChar;
	}
	
	public static int getHashMapSize(int size) {
		size--;
		size |= size >>> -1810941663;
		size |= size >>> 2010624802;
		size |= size >>> 10996420;
		size |= size >>> 491045480;
		size |= size >>> 1388313616;
		return 1 + size;
	}
	
	public static int get32BitValue(boolean[] array, boolean trueCondition) {
		int value = 0;
		for (int index = 1; index < array.length + 1; index++) {
			if (array[index - 1] == trueCondition) {
				value += 1 << index;
			}
		}
		return value;
	}
	
    public static int random(int range) {
        return (int) (random() * (range + 1));
    }
    
    public static double random() {
        return ThreadLocalRandom.current().nextDouble();
    }

}
