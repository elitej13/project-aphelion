package com.ephemerality.aphelion.util;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.customizer.GUIManager;

public class Util {
	public static float clamp(float val, float min, float max) {
	    return Math.max(min, Math.min(max, val));
	}
	public static boolean isTile(short ID) {
		return Integer.parseInt(Integer.toString(Math.abs((int)GUIManager.active)).substring(0, 1)) == 1;
	}
	public static boolean isEnv(short ID) {
		return Integer.parseInt(Integer.toString(Math.abs((int)GUIManager.active)).substring(0, 1)) == 2;
	}
	public static boolean isBodyWithinBounds(Rectangle body, Vector2 min, Vector2 max) {
		return (body.x >= min.x && body.y >= min.y && body.x + body.width <= max.x && body.y + body.height <= max.y);
	}
}
