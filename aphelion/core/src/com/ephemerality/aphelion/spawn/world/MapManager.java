package com.ephemerality.aphelion.spawn.world;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.spawn.entities.tiles.Tile;
import com.ephemerality.aphelion.util.FileManager;

public class MapManager {
	
	public static int tileSize = 64;
	private boolean recentlyReloaded;
	
	//Change this back to private later
	private Level bufferedLevel;
	public Level level;	
	
	public Vector2 mapPixelSize;
	public Vector2 offset;
	
	public MapManager() {
		level = new Level(12, 12);
		mapPixelSize = new Vector2(level.WIDTH * MapManager.tileSize, level.HEIGHT * MapManager.tileSize);
		offset = new Vector2(0, 0);
	}
	
	public void load(String location) {
		bufferedLevel = level;
		level = new Level(FileManager.readFromFile(location));
		mapPixelSize = new Vector2(level.WIDTH * MapManager.tileSize, level.HEIGHT * MapManager.tileSize);
		offset = new Vector2(0, 0);
		recentlyReloaded = true;
	}
	public void save(String location) {
		FileManager.writeToFile(location, level.toByteArray());
	}
	public void editTile(int x, int y, short tileID) {
		level.editTile((int)((offset.x / MapManager.tileSize) + x), (int)((offset.y / MapManager.tileSize) + y), tileID);
	}
	public void createNewLevel(int w, int h) {
		bufferedLevel = level;
		level = new Level(w, h);
	}
	public void editSize(int w, int h) {
		level.editSize(w, h);
	}
	public boolean hasRecentlyReloaded() {
		if(recentlyReloaded) {
			recentlyReloaded = false;
			return true;
		}
		return false;
	}
	
	public Rectangle[] getSurroundingTiles(Vector2 vector) {
		int x = (int)vector.x >> 6;
		int y = (int)vector.y >> 6;
		Rectangle[] tiles = new Rectangle[9];
		int w = level.WIDTH;
		int h = level.HEIGHT;
		for(int yi = -1; yi <= 1; yi++) {
			for(int xi = -1; xi <= 1; xi++) {
				if(x + xi >= 0 && y + yi >= 0 && x + xi < w && y + yi < h) {
					tiles[(xi + 1) + ((yi + 1) * 3)] = level.collidable[(x + xi) + ((y + yi) * w)];
				}else {
					tiles[(xi + 1) + ((yi + 1) * 3)] = new Rectangle((x + xi) * MapManager.tileSize, (y + yi) * MapManager.tileSize, MapManager.tileSize, MapManager.tileSize);
				}
			}
		}
		return tiles;
	}
	
	
	public void render(ScreenManager screen) {
		int tileSize = MapManager.tileSize;
		Rectangle bounds = screen.getBounds();
		int x0 = (int) Math.floor(bounds.x / tileSize);
		int y0 = (int) Math.floor(bounds.y / tileSize);
		int x1 = x0 + (int) Math.ceil(bounds.width / tileSize);
		int y1 = y0 + (int) Math.ceil(bounds.height / tileSize);
		for(int y = y0; y <= y1; y++) {
			while(y < 0) y++;
			if(y >= level.HEIGHT)
				break;
			for(int x = x0; x <= x1; x++) {
				while(x < 0) x++;
				if(x >= level.WIDTH)
					continue;
				int index = x + (y * level.WIDTH);
				short currentPixel = level.tiles[index];
				if(currentPixel == Tile.GRASS_ID) {
					screen.render(SpriteSheet.default_grass_0, x * tileSize, y * tileSize);
				}else if(currentPixel == Tile.DIRT_ID) {
					screen.render(SpriteSheet.default_dirt_0, x * tileSize, y * tileSize);
				}else if(currentPixel == Tile.BRICK_ID) {
					screen.render(SpriteSheet.default_brick_0,  x * tileSize, y * tileSize);
				}else if(currentPixel == Tile.WOOD_ID) {
					screen.render(SpriteSheet.default_wood_0,  x * tileSize, y * tileSize);
				}
			}
		}
	}
	
	public Vector2 getMapSize() {
		return mapPixelSize;
	}

}
