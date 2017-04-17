package com.ephemerality.aphelion.spawn.entities.player.inventory;

import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.graphics.SpriteSheet;

public class Item {
	
	TextureRegion texture;
	short ID;
	int count;
	int x, y;
	
	public Item(short ID, int count, int tilex, int tiley) {
		this.ID = ID;
		this.count = count;
		texture = SpriteSheet.fetchTextureRegionFromTileID(ID);
		x = tilex;
		y = tiley;
	}
	
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void increcement() {
		count++;
	}
	
	
	public void render(ScreenManager screen, float xOffset, float yOffset, float padding, float scale) {
		screen.render(texture, (x * scale) + (x * padding) + xOffset, (y * scale) + (y * padding) + yOffset);
//		TODO: render count above icon
	}
	
}
