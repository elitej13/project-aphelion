package com.ephemerality.aphelion.editor;

import com.badlogic.gdx.Gdx;

public class Mouse  {
	
	int x, y;
	int rawX, rawY;
	
	public Mouse() {
	}
	
	
	
	
	public void update() {
		if(Gdx.input.justTouched()) {
			setRaw();
		}
	}
	public void setRaw() {
		x = rawX;
		y = rawY;
	}
	
}
