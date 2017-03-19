package com.ephemerality.aphelion.spawn.world;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.graphics.SpriteSheet;

public class MapManager {
	
	public static int tileSize = 64;
	
	private Level currentLevel;	
	public Vector2 mapPixelSize;
	
	public MapManager() {
		currentLevel = new Level("0");
		mapPixelSize = new Vector2(currentLevel.WIDTH * MapManager.tileSize, currentLevel.HEIGHT * MapManager.tileSize);
	}
	
	
	
	public void update() {
	}
	
	
	public Rectangle[] getSurroundingTiles(Vector2 vector) {
		int x = (int)vector.x >> 6;
		int y = (int)vector.y >> 6;
		Rectangle[] tiles = new Rectangle[9];
		int w = currentLevel.WIDTH;
		int h = currentLevel.HEIGHT;
		for(int yi = -1; yi <= 1; yi++) {
			for(int xi = -1; xi <= 1; xi++) {
				if(x + xi >= 0 && y + yi >= 0 && x + xi < w && y + yi < h) {
					tiles[(xi + 1) + ((yi + 1) * 3)] = currentLevel.collidable[(x + xi) + ((y + yi) * w)];
				}else {
					tiles[(xi + 1) + ((yi + 1) * 3)] = new Rectangle((x + xi) * MapManager.tileSize, (y + yi) * MapManager.tileSize, MapManager.tileSize, MapManager.tileSize);
				}
			}
		}
		return tiles;
	}
	
	
//	TODO: Set these as ID's with the respective tile object
	public static final int col_grass = 0x4A7108;
	public static final int col_dirt = 0x7B4F03;
	public static final int col_brick = 0x282828;
	public static final int col_wood = 0xFFB47918;
	
	
	public void render(ScreenManager screen) {
		int tileSize = MapManager.tileSize;
		Rectangle bounds = screen.getBounds();
		int x0 = (int) Math.floor(bounds.x / tileSize);
		int y0 = (int) Math.floor(bounds.y / tileSize);
		int x1 = x0 + (int) Math.ceil(bounds.width / tileSize);
		int y1 = y0 + (int) Math.ceil(bounds.height / tileSize);
		for(int y = y0; y <= y1; y++) {
			while(y < 0) y++;
			if(y >= currentLevel.HEIGHT)
				break;
			for(int x = x0; x <= x1; x++) {
				while(x < 0) x++;
				if(x >= currentLevel.WIDTH)
					continue;
				int index = x + (y * currentLevel.WIDTH);
				int currentPixel = currentLevel.tiles[index];
				if(currentPixel == col_grass) {
					screen.getSpriteBatch().draw(SpriteSheet.default_grass_0, x * tileSize, y * tileSize);
				}else if(currentPixel == col_dirt) {
					screen.getSpriteBatch().draw(SpriteSheet.default_dirt_0, x * tileSize, y * tileSize);
				}else if(currentPixel == col_brick) {
					screen.getSpriteBatch().draw(SpriteSheet.default_brick_0,  x * tileSize, y * tileSize);
				}else if(currentPixel == col_wood) {
					screen.getSpriteBatch().draw(SpriteSheet.default_wood_0,  x * tileSize, y * tileSize);
				}
			}
		}
	}
	
	public Vector2 getMapSize() {
		return mapPixelSize;
	}
}
