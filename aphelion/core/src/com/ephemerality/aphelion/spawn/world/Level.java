package com.ephemerality.aphelion.spawn.world;

import java.nio.ByteBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Level {
	
	
	public String tilePath, entityPath;
	public Rectangle[] collidable;
	public int[] tiles, entities;
	public int WIDTH, HEIGHT;

	public Level(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		tiles = new int[width * height];
		for(int i = 0; i < tiles.length; i++) {
			tiles[i] = 0xFFFF00 + i; 
		}
	}
	
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
		collidable = new Rectangle[WIDTH * HEIGHT];
		int tileSize = MapManager.tileSize;
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				tiles[(WIDTH - x - 1) + ((HEIGHT - y - 1) * WIDTH)] =  (map.getPixel(x, y) >> 8);
//				System.out.println(Integer.toHexString(map.getPixel(x, y) >> 8));
				int current = map.getPixel(x, y) & 0xFF;
				if(current == 255) {
					collidable[(WIDTH - x - 1) + ((HEIGHT - y - 1) * WIDTH)] = new Rectangle((WIDTH - x - 1) << 6, (WIDTH - y - 1) << 6, tileSize, tileSize);;
				}
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
	
	public Level(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		WIDTH = buffer.getInt();
		HEIGHT = buffer.getInt();
		tiles = new int[WIDTH * HEIGHT];
		collidable = new Rectangle[WIDTH * HEIGHT];
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				int current = buffer.getInt();
				tiles[x + y * WIDTH] = current;
				if(current == 255) {
					collidable[(WIDTH - x - 1) + ((HEIGHT - y - 1) * WIDTH)] = new Rectangle((WIDTH - x - 1) << 6, (WIDTH - y - 1) << 6, MapManager.tileSize, MapManager.tileSize);
				}
			}
		}
	}
	
	/**
	 * First 8 bytes for WIDTH and HEIGHT ints.
	 * All remaining bytes for tile array.
	 * @return
	 */
	public byte[] toByteArray() {
		byte[] data = ByteBuffer.allocate(4 * (WIDTH * HEIGHT + 2)).array();
		ByteBuffer buffer = ByteBuffer.wrap(data).putInt(WIDTH).putInt(HEIGHT);
		for(int i = 0; i < WIDTH * HEIGHT; i++) {
			buffer.putInt(tiles[i]);
		}
		return data;
	}
	public void print() {
		System.out.println(WIDTH + ", " + HEIGHT);
	}
}
