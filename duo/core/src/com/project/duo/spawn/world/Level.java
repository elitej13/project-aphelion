package com.project.duo.spawn.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Level {
	
	
	String tilePath, entityPath;
	int[] tiles, entities;
	int WIDTH, HEIGHT;

	
	public Level(String level) {
		tilePath = "maps/" + level + "/tiles.png";
		entityPath = "maps/" + level + "/entities.png";
		loadTiles();
//		loadEntities();
	}
	
	//Pixmap.getPixel gives an RGBA8888 value shifting it right gives the rgb value with 8 bits per value and the remaining 8 bits at 0
	public void loadTiles() {
		Texture texture = new Texture(Gdx.files.internal(tilePath));
		texture.getTextureData().prepare();
		Pixmap map = texture.getTextureData().consumePixmap();
		WIDTH = texture.getWidth();
		HEIGHT = texture.getHeight();
		tiles = new int[WIDTH * HEIGHT];
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				tiles[x + (y * WIDTH)] =  (map.getPixel(x, y) >> 8);
			}
		}
	}
	
	public void loadEntities() {
		Texture texture = new Texture(Gdx.files.internal(entityPath));
		Pixmap map = texture.getTextureData().consumePixmap();
		WIDTH = texture.getWidth();
		HEIGHT = texture.getHeight();
		entities = new int[WIDTH * HEIGHT];
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				entities[x + (y * WIDTH)] =  (map.getPixel(x, y) >> 8);
			}
		}
	}
	
	
}
