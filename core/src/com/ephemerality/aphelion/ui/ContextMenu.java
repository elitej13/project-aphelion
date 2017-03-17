package com.ephemerality.aphelion.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ContextMenu {
	
	
	private Texture bkgd;
	private Vector2 resolution;
	
	public ContextMenu() {
		resolution = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Pixmap backDrop = new Pixmap(10, 10, Pixmap.Format.RGB888);
		backDrop.setColor(0.5f, 0.5f, 0.5f, 0.75f);
		backDrop.fillRectangle(0, 0, 10, 10);
		bkgd = new Texture(backDrop);
	}
	
	
	
	
	
	public void update() {
	}
	
	
	public void render(SpriteBatch sb) {
		int x = (int) (resolution.x * 0.05f);
		int y = (int) (resolution.y * 0.05f);
		int w = (int) (resolution.x * 0.9f);
		int h = (int) (resolution.y * 0.9f);
		sb.draw(bkgd, x, y, w, h);
	}
}