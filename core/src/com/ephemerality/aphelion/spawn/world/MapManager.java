package com.ephemerality.aphelion.spawn.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.Sprite;

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
	
	public static final int col_grass = 0x4A7108;
	public static final int col_dirt = 0x7B4F03;
	public static final int col_brick = 0x282828;
	public static final int col_wood = 0xFFB47918;
	
	//TODO: Set these as ID's with the respective tile object
	public void render(SpriteBatch sb, Vector2 c0, Vector2 c1) {
		int tileSize = MapManager.tileSize;
		for(int y = (int) c0.y; y < c1.y + 64; y += tileSize) {
			if(y < 0 || y >> 6 >= currentLevel.HEIGHT) continue;
			for(int x = (int) c0.x; x < c1.x + 64; x += tileSize) {
				if(x < 0 || x >> 6 >= currentLevel.WIDTH) continue;
				int index = (int) (Math.floor(x >> 6) + (Math.floor(y >> 6) * 64));
				int currentPixel = currentLevel.tiles[index];
				if(currentPixel == col_grass) {
					sb.draw(Sprite.default_grass_0, (x - (x % 64)) - c0.x, (y - (y % 64)) - c0.y);
				}else if(currentPixel == col_dirt) {
					sb.draw(Sprite.default_dirt_0, (x - (x % 64)) - c0.x, (y - (y % 64)) - c0.y);
				}else if(currentPixel == col_brick) {
					sb.draw(Sprite.default_brick_0, (x - (x % 64)) - c0.x, (y - (y % 64)) - c0.y);
				}else if(currentPixel == col_wood) {
					sb.draw(Sprite.default_wood_0, (x - (x % 64)) - c0.x, (y - (y % 64)) - c0.y);
				}
			}
		}
	}
	
	
	public Vector2 getMapSize() {
		return mapPixelSize;
	}
}
