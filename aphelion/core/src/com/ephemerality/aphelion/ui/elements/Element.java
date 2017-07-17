package com.ephemerality.aphelion.ui.elements;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class Element {
	
	public Rectangle body;
	public String title;
	public boolean active;
	public boolean pressed;
	
	public Element(float x, float y, float w, float h, String title) {
		body = new Rectangle(x, y, w, h);
		this.title = title;
	}
	public boolean checkActive(Vector2 vect) {
		if(body.contains(vect)) {
			active = true;
			return true;
		}
		else {
			active = false;
			return false;
		}
	}
	public void behavior() {}
	public void render(ScreenManager screen) {}
}
