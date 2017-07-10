package com.ephemerality.aphelion.spawn.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.spawn.entities.EntityManager;
import com.ephemerality.aphelion.spawn.entities.nob.Tile;
import com.ephemerality.aphelion.util.FileManager;

public class MapManager {
	
	public static int tileSize = 64;
	public boolean recentlyReloaded;
	public float mapWidth = 128f;
	public float mapHeight = 128f;
	public float scale = 0.0625f;
	public float mapSimulatedWidth = mapWidth / scale;
	public float mapSimulatedHeight = mapHeight / scale;
	
	//TODO: finish implementing thiss
	Level bufferedLevel;
	
	public Level level;	
	public Vector2 mapPixelSize;
	public Vector2 offset;
	
	public MapManager() {
		level = new Level("tst_01", FileManager.readFromFile("maps/tst_01" + Level.EXTENSION, false));
//		level = new Level(12,12);
		mapPixelSize = new Vector2(level.WIDTH * MapManager.tileSize, level.HEIGHT * MapManager.tileSize);
		offset = new Vector2(0, 0);
	}
	public void load(String name, String location, boolean absolutepath) {
		bufferedLevel = level;
		level = new Level(name, FileManager.readFromFile(location, absolutepath));
		mapPixelSize = new Vector2(level.WIDTH * MapManager.tileSize, level.HEIGHT * MapManager.tileSize);
		offset = new Vector2(0, 0);
		recentlyReloaded = true;
	}
	public void save(String location, boolean absolutepath) {
		FileManager.writeToFile(location, level.toByteArray(), absolutepath);
	}
	public Warp getWarp(Rectangle rect) {
		for(Warp w : level.warps){
			if(rect.overlaps(w.local));
				return w;
		}
		return null;
	}
	public void renderBackGround(ScreenManager screen) {
		renderTiles(screen);
		//----------Debugging Purposes------------------//
		for(Warp warp : level.warps) {
			screen.renderRectangle(warp.local, Color.BLUE);
			screen.renderString(Color.BLUE, warp.level, warp.local.x, warp.local.y - 5f);
		}
	}
	public void renderAlphaMask(ScreenManager screen) {
		Gdx.gl.glColorMask(false, false, false, true);
		screen.sb.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);
		screen.renderFixed(SpriteSheet.minimap_mask, Gdx.graphics.getWidth() - mapWidth, Gdx.graphics.getHeight() - mapHeight, SpriteSheet.minimap_mask.getWidth(), SpriteSheet.minimap_mask.getHeight());
		screen.sb.flush();
	}
	public void renderForeGround(ScreenManager screen, EntityManager ent) {
		Gdx.gl.glColorMask(true, true, true, true);
		screen.sb.setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_ONE_MINUS_DST_ALPHA);
		Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
		Gdx.gl.glScissor(Gdx.graphics.getWidth() - (int) mapWidth, Gdx.graphics.getHeight() - (int) mapHeight, SpriteSheet.minimap_mask.getWidth(), SpriteSheet.minimap_mask.getHeight());
		renderMap(screen, ent);
		Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
	}
	public void renderTiles(ScreenManager screen) {
		int tileSize = MapManager.tileSize;
		int x0 = (int) Math.floor(screen.bounds.x / tileSize);
		int y0 = (int) Math.floor(screen.bounds.y / tileSize);
		int x1 = x0 + (int) Math.ceil(screen.bounds.width / tileSize);
		int y1 = y0 + (int) Math.ceil(screen.bounds.height / tileSize);
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
				}else if(currentPixel == Tile.SAND_ID) {
					screen.render(SpriteSheet.default_sand_0,  x * tileSize, y * tileSize);
				}else if(currentPixel == Tile.WATER_ID) {
					screen.render(SpriteSheet.default_water_0,  x * tileSize, y * tileSize);
				}else if(currentPixel == Tile.GRAVEL_ID) {
					screen.render(SpriteSheet.default_gravel_0,  x * tileSize, y * tileSize);
				}
			}
		}		
	}
	public void renderMap(ScreenManager screen, EntityManager ent) {
		float playerX = ent.player.body.x;
		float playerY = ent.player.body.y;
		for(int y = (int) ((playerY - (mapSimulatedHeight / 2)) / tileSize); y <= (playerY + mapSimulatedHeight) / tileSize; y++) {//((playerY + (mapSimulatedHeight / 2)) / tileSize) + 1; y++) {
			for(int x = (int)((playerX - (mapSimulatedWidth / 2)) / tileSize); x <= (playerX + mapSimulatedWidth) / tileSize; x++) {
				short currentPixel = Tile.VOID_ID;
				if(x > 0 && y > 0 && x < level.WIDTH && y < level.HEIGHT)
					currentPixel = level.tiles[x + (y * level.WIDTH)];
				renderOnMiniMap(screen, SpriteSheet.fetchIconFromEntityID(currentPixel), x - ((playerX - (mapSimulatedWidth / 2)) / tileSize), y  - ((playerY - (mapSimulatedHeight / 2)) / tileSize));
			}
		}
	}
	public void renderOnMiniMap(ScreenManager screen, TextureRegion texture, float globalX, float globalY) {
		screen.renderFixed(texture, Gdx.graphics.getWidth() - mapWidth + globalX * texture.getRegionWidth(), Gdx.graphics.getHeight() - mapHeight + globalY * texture.getRegionHeight(), texture.getRegionWidth(), texture.getRegionHeight());
	}
	public Vector2 getMapSize() {
		return mapPixelSize;
	}
	
	
	
	
//-----------------------------Editor Functionality-----------------------------------------------------------//
	public void editTile(int x, int y, short tileID) {
		int xx = (int)(offset.x / tileSize) + x;
		int yy = (int)(offset.y / tileSize) + y;
		if(xx < 0 || xx >= level.WIDTH || yy < 0 || yy >= level.HEIGHT)
			return;
		level.tiles[xx + yy * level.WIDTH] = tileID;
	}
	public void createNewLevel(int w, int h) {
		bufferedLevel = level;
		level = new Level(w, h);
	}
	public void resize(int w, int h) {
		short[] buffer = new short[w * h];
		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				if(y < level.HEIGHT && x < level.WIDTH) {
					buffer[x + y * w] = level.tiles[x + y * level.WIDTH];
				}else {
					buffer[x + y * w] = Tile.GRASS_ID;
				}
			}
		}
		level.tiles = buffer;
		level.HEIGHT = h;
		level.WIDTH = w;
		mapPixelSize = new Vector2(level.WIDTH * MapManager.tileSize, level.HEIGHT * MapManager.tileSize);
		System.out.println("Resizing: " + level.WIDTH + ", " + level.HEIGHT);
	}
	public boolean hasRecentlyReloaded() {
		if(recentlyReloaded) {
			recentlyReloaded = false;
			return true;
		}
		return false;
	}
//----------------------------------END EDITOR FUNCTIONS---------------------------------------//	
}