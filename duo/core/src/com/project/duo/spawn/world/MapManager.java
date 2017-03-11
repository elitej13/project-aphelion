package com.project.duo.spawn.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.project.duo.graphics.Sprite;

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
	
	
	
	
	public static final int col_grass = 0x4A7108;
	public static final int col_dirt = 0x7B4F03;
	
	//TODO: Set these as ID's with the respective tile object
	public void render(SpriteBatch sb, Vector2 c0, Vector2 c1) {
		int tileSize = MapManager.tileSize;
		for(int y = (int) c0.y; y < c1.y + 64; y += tileSize) {
			if(y < 0 || y / 64 >= currentLevel.HEIGHT) continue;
			for(int x = (int) c0.x; x < c1.x + 64; x += tileSize) {
				if(x < 0 || x / 64 >= currentLevel.WIDTH) continue;
				int index = (int) (Math.floor(x / 64) + (Math.floor(y / 64) * 64));
				int currentPixel = currentLevel.tiles[index];
				if(currentPixel == col_grass) {
					sb.draw(Sprite.default_grass_0, (x - (x % 64)) - c0.x, (y - (y % 64)) - c0.y);
				}else if(currentPixel == col_dirt) {
					sb.draw(Sprite.default_dirt_0, (x - (x % 64)) - c0.x, (y - (y % 64)) - c0.y);
				}
			}
		}
	}
	
	
	public Vector2 getMapSize() {
		return mapPixelSize;
	}
}
