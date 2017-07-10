package com.ephemerality.aphelion.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class Overlay {
	
	Pixmap healthBar;
	Texture health;
	float fill;
	
	public Overlay() {
		healthBar = new Pixmap(1, 1, Pixmap.Format.RGB888);
		healthBar.setColor(1f, 0, 0, 1f);
		healthBar.fillRectangle(0, 0, 1, 1);
		health = new Texture(healthBar);
		fill = 1f;
	}
	
	
	public void render(ScreenManager screen) {
		int x = 10;
		int y = (int) Gdx.graphics.getHeight() - 30;
		screen.renderFixed(health, x, y, (int)(200 * fill), 10);
	}
	
}
