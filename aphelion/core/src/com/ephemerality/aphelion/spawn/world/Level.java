package com.ephemerality.aphelion.spawn.world;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * The local fields of the Level class should include:
 * <br> -Tiles
 * <br> -Environmental(env) elements
 * <br> -Entities spawns
 * <br> -Warps
 * <br> -Events ??
 * 
 * @author Josh
 */

public class Level {
	
	public static final String EXTENSION = ".lvl";
	
	public String name;
	public ArrayList<Warp> warps;
	public short[] tiles, env, mobs;
	public int WIDTH, HEIGHT;
	
	public Level(int width, int height) {
		this.name = "DEFAULT";
		WIDTH = width;
		HEIGHT = height;
		tiles = new short[WIDTH * HEIGHT];
		env = new short[WIDTH * HEIGHT];
		mobs = new short[WIDTH * HEIGHT];
		warps = new ArrayList<>();
	}
	
	public Level(String name, byte[] data) {
		this.name = name;
		ByteBuffer buffer = ByteBuffer.wrap(data);
		WIDTH = buffer.getInt();
		HEIGHT = buffer.getInt();
		tiles = new short[WIDTH * HEIGHT];
		env = new short[WIDTH * HEIGHT];
		mobs = new short[WIDTH * HEIGHT];
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				short current = buffer.getShort();
				tiles[x + y * WIDTH] = current;
			}
		}
		warps = new ArrayList<>();
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
}
