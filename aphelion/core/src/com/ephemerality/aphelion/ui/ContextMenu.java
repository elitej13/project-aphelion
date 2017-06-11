package com.ephemerality.aphelion.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.player.Player;

public class ContextMenu {
	
	
	private Texture bkgd;
	private Vector2 resolution;
	private Player player;
	
	
	public ContextMenu(Player player) {
		this.player = player;
		resolution = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Pixmap backDrop = new Pixmap(1, 1, Pixmap.Format.RGB888);
		backDrop.setColor(0.5f, 0.5f, 0.5f, 0.5f);
		backDrop.fillRectangle(0, 0, 1, 1);
		bkgd = new Texture(backDrop);
	}
	
	
	
	
	
	public void update() {
		
	}
	
	
	public void render(ScreenManager screen) {
//		int x = (int) (resolution.x * 0.125f);
//		int y = (int) (resolution.y * 0.125f);
		int x = 0;
		int y = 0;
//		int w = (int) (resolution.x * 0.75f);
//		int h = (int) (resolution.y * 0.75f);
		int w = (int) resolution.x;
		int h = (int) resolution.y;
		screen.getSpriteBatch().setColor(1f, 1f, 1f, 0.75f);
		screen.renderFixed(bkgd, x, y, w, h);
		screen.getSpriteBatch().setColor(1f, 1f, 1f, 1f);
		player.inventory.render(screen);
	}
}