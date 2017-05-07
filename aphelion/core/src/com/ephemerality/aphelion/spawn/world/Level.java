package com.ephemerality.aphelion.spawn.world;

import java.nio.ByteBuffer;

import com.badlogic.gdx.math.Rectangle;
import com.ephemerality.aphelion.spawn.entities.tiles.Tile;

public class Level {
	
	public Rectangle[] collidable;
	public short[] tiles, entities;
	public int WIDTH, HEIGHT;

	public Level(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		tiles = new short[width * height];
		collidable = new Rectangle[WIDTH * HEIGHT];
//		for(short i = 0; i < tiles.length; i++) {
//			if(i % 5 == 0) tiles[i] = Tile.DIRT_ID; 
//			else if(i % 7 == 0) tiles[i] = Tile.BRICK_ID; 
//			else if(i % 9 == 0) tiles[i] = Tile.WOOD_ID; 
//			else tiles[i] = Tile.GRASS_ID;
//		}
//		for(int y = 0; y < HEIGHT; y++) {
//			for(int x = 0; x < WIDTH; x++) {
//				if(tiles[x + y * WIDTH] < 0) {
//					collidable[(WIDTH - x - 1) + ((HEIGHT - y - 1) * WIDTH)] = new Rectangle((WIDTH - x - 1) << 6, (WIDTH - y - 1) << 6, MapManager.tileSize, MapManager.tileSize);
//				}
//			}	
//		}
	}
	
	public Level(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		WIDTH = buffer.getInt();
		HEIGHT = buffer.getInt();
		tiles = new short[WIDTH * HEIGHT];
		collidable = new Rectangle[WIDTH * HEIGHT];
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				short current = buffer.getShort();
				tiles[x + y * WIDTH] = current;
				if(current < 0) {
//					collidable[(WIDTH - x - 1) + ((HEIGHT - y - 1) * WIDTH)] = new Rectangle((WIDTH - x - 1) << 6, (WIDTH - y - 1) << 6, MapManager.tileSize, MapManager.tileSize);
					collidable[x + y * WIDTH] = new Rectangle(x << 6, y << 6, MapManager.tileSize, MapManager.tileSize);
	}
			}
		}
	}
	
	public void editSize(int w, int h) {
		short[] buffer = new short[w * h];
		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				if(y < HEIGHT && x < WIDTH) {
					buffer[x + y * w] = tiles[x + y * WIDTH];
				}else {
					buffer[x + y * w] = Tile.GRASS_ID;
				}
			}
		}
		tiles = buffer;
		
	}
	
	public void editTile(int x, int y, short tileID) {
		if(x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT)
			return;
		tiles[x + y * WIDTH] = tileID;
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
			buffer.putShort(tiles[i]);
		}
		return data;
	}
	public void print() {
		System.out.println(WIDTH + ", " + HEIGHT);
	}
}
