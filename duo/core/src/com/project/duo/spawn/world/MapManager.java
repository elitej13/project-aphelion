package com.project.duo.spawn.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.project.duo.graphics.Sprite;

public class MapManager {
	
	private Level currentLevel;	
	
	
	public MapManager() {
		currentLevel = new Level("0");
	}
	
	
	
	public void update() {
		
	}
	
	
	
	
	public static final int col_grass = 0xFF4A7108;
	public static final int col_dirt= 0xFF7B4F03;
	
	//TODO: Set these as ID's with the respective tile object
	public void render(SpriteBatch sb, Vector2 c0, Vector2 c1) {
		int tileSize = 64;
		int[] tiles = currentLevel.tiles;
		for(int y = 0; y < currentLevel.HEIGHT; y ++) {
			for(int x = 0; x < currentLevel.WIDTH; x ++) {
				int currentPixel = tiles[x + y * currentLevel.WIDTH];
				if(currentPixel == col_grass) {
					sb.draw(Sprite.default_grass_0, x * tileSize, y * tileSize);
				}else if(currentPixel == col_dirt) {
					sb.draw(Sprite.default_dirt_0, x * tileSize, y * tileSize);
				}else {
					
				}
			}	
		}
	}
}
