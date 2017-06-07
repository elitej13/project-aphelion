package com.ephemerality.aphelion.spawn.world;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.ephemerality.aphelion.spawn.entities.tiles.Tile;

public class Level {
	
	public String name;
	public Rectangle[] collidable;
	public ArrayList<Warp> warps;
	public short[] tiles, entities;
	public int WIDTH, HEIGHT;

	public Level(int width, int height) {
		this.name = "DEFAULT";
		WIDTH = width;
		HEIGHT = height;
		tiles = new short[width * height];
		collidable = new Rectangle[WIDTH * HEIGHT];
	}
	
	public Level(String name, byte[] data) {
		this.name = name;
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
		

		warps = new ArrayList<>();	
		warps.add(new Warp("hut", "test", new Rectangle(0, 20, 130, 64), new Rectangle(256, 20, 64, 64)));
		
	}
	
	
//	EDITOR FUNCTION
	public void resize(int w, int h) {
		short[] buffer = new short[w * h];
		Rectangle[] rectbuffer = new Rectangle[w * h];
		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				if(y < HEIGHT && x < WIDTH) {
					buffer[x + y * w] = tiles[x + y * WIDTH];
					rectbuffer[x + y * w] = collidable[x + y * WIDTH];
				}else {
					buffer[x + y * w] = Tile.GRASS_ID;
				}
			}
		}
		tiles = buffer;
		
		collidable = rectbuffer;
		HEIGHT = h;
		WIDTH = w;
	}
//	END of EDITOR FUNCITON
	
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
