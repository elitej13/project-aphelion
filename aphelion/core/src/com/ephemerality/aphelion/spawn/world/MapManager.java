package com.ephemerality.aphelion.spawn.world;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.spawn.entities.tiles.Tile;
import com.ephemerality.aphelion.util.FileManager;

public class MapManager {
	
	public static int tileSize = 64;
	
	private Level bufferedLevel;
	private Level currentLevel;	
	public Vector2 mapPixelSize;
	
	public Rectangle offset;
	
	public MapManager() {
		currentLevel = new Level(12, 12);
		mapPixelSize = new Vector2(currentLevel.WIDTH * MapManager.tileSize, currentLevel.HEIGHT * MapManager.tileSize);
	}
	
	public void load(String location) {
		bufferedLevel = currentLevel;
		currentLevel = FileManager.readLevelFromFile(location);
	}
	public void save(String location) {
		FileManager.writeLevelToFile(location, currentLevel.toByteArray());
	}
	public void editTile(Vector2 position, short tileID) {
		currentLevel.editTile(position, tileID);
	}
	public void createNewLevel(int w, int h) {
		bufferedLevel = currentLevel;
		currentLevel = new Level(w, h);
	}
	public void editSize(int w, int h) {
		
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
	
	
//	public void render(ScreenManager screen) {
//		int tileSize = MapManager.tileSize;
//		Rectangle bounds = screen.getBounds();
//		int x0 = (int) Math.floor(bounds.x / tileSize);
//		int y0 = (int) Math.floor(bounds.y / tileSize);
//		int x1 = x0 + (int) Math.ceil(bounds.width / tileSize);
//		int y1 = y0 + (int) Math.ceil(bounds.height / tileSize);
//		for(int y = y0; y <= y1; y++) {
//			while(y < 0) y++;
//			if(y >= currentLevel.HEIGHT)
//				break;
//			for(int x = x0; x <= x1; x++) {
//				while(x < 0) x++;
//				if(x >= currentLevel.WIDTH)
//					continue;
//				int index = x + (y * currentLevel.WIDTH);
//				short currentPixel = currentLevel.tiles[index];
//				if(currentPixel == Tile.GRASS_ID) {
//					screen.render(SpriteSheet.default_grass_0, x * tileSize, y * tileSize);
//				}else if(currentPixel == Tile.DIRT_ID) {
//					screen.render(SpriteSheet.default_dirt_0, x * tileSize, y * tileSize);
//				}else if(currentPixel == Tile.BRICK_ID) {
//					screen.render(SpriteSheet.default_brick_0,  x * tileSize, y * tileSize);
//				}else if(currentPixel == Tile.WOOD_ID) {
//					screen.render(SpriteSheet.default_wood_0,  x * tileSize, y * tileSize);
//				}
//			}
//		}
//	}
	public void render(ScreenManager screen) {
		int tileSize = MapManager.tileSize;
		Rectangle bounds = screen.getBounds();
		int x0 = (int) Math.floor(bounds.x / tileSize);
		int y0 = (int) Math.floor(bounds.y / tileSize);
		int x1 = x0 + (int) Math.ceil(offset.width / tileSize);
		int y1 = y0 + (int) Math.ceil(offset.height / tileSize);
		for(int y = y0; y <= y1; y++) {
			while(y < 0) y++;
			if(y >= currentLevel.HEIGHT)
				break;
			for(int x = x0; x <= x1; x++) {
				while(x < 0) x++;
				if(x >= currentLevel.WIDTH)
					continue;
				int index = x + (y * currentLevel.WIDTH);
				short currentPixel = currentLevel.tiles[index];
				if(currentPixel == Tile.GRASS_ID) {
					screen.render(SpriteSheet.default_grass_0, x * tileSize + offset.x, y * tileSize + offset.y);
				}else if(currentPixel == Tile.DIRT_ID) {
					screen.render(SpriteSheet.default_dirt_0, x * tileSize + offset.x, y * tileSize + offset.y);
				}else if(currentPixel == Tile.BRICK_ID) {
					screen.render(SpriteSheet.default_brick_0,  x * tileSize + offset.x, y * tileSize + offset.y);
				}else if(currentPixel == Tile.WOOD_ID) {
					screen.render(SpriteSheet.default_wood_0,  x * tileSize + offset.x, y * tileSize + offset.y);
				}
			}
		}
	}
	
	public Vector2 getMapSize() {
		return mapPixelSize;
	}

	public void setOffset(Rectangle offset) {
		this.offset = offset;
		
	}

}
