package com.ephemerality.aphelion.persona.inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class InventoryItem {
	
	TextureRegion texture;
	short ID;
	int count;
	int x, y;
	
	public InventoryItem(TextureRegion texture, short ID, int tilex, int tiley) {
		this.texture = texture;
		this.ID = ID;
		count = 1;
		x = tilex;
		y = tiley;
	}
	
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void increment() {
		count++;
	}
	
	
	public void render(ScreenManager screen, float xOffset, float yOffset, float padding, float scale) {
		screen.renderFixed(texture, (x * scale) + (x * padding) + xOffset, (y * scale) + (y * padding) + yOffset);
//		TODO: render count above icon
	}
	
}
