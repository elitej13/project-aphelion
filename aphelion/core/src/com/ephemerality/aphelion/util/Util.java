package com.ephemerality.aphelion.util;

public class Util {
	public static float clamp(float val, float min, float max) {
	    return Math.max(min, Math.min(max, val));
	}
}
