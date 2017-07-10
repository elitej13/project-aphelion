package com.ephemerality.aphelion.spawn.world;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.util.FileManager;

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
	public static final int STRING_SIZE = 6;
	
	public String name;
	public HashSet<Warp> warps;
	public HashMap<Vector2, Short> env;
	public short[] tiles, mobs;
	public int WIDTH, HEIGHT;
	
	public Level(int width, int height) {
		this.name = "DEFAULT";
		WIDTH = width;
		HEIGHT = height;
		tiles = new short[WIDTH * HEIGHT];
		mobs = new short[WIDTH * HEIGHT];
		env = new HashMap<>();
		warps = new HashSet<>();
	}
	
	public Level(String name, byte[] data) {
		this.name = name;
		tiles = new short[WIDTH * HEIGHT];
		mobs = new short[WIDTH * HEIGHT];
		env = new HashMap<>();
		warps = new HashSet<>();
		
		ByteBuffer buffer = ByteBuffer.wrap(data);
		WIDTH = buffer.getInt();
		HEIGHT = buffer.getInt();
		int eSize = buffer.getInt();
		int wSize = buffer.getInt();
		//-------------Tiles-------------------//
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				short current = buffer.getShort();
				tiles[x + y * WIDTH] = current;
			}
		}
		//------------EnvNobs-----------------//
		for(int i = 0; i < eSize; i++) {
			short ID = buffer.getShort();
			float x = buffer.getFloat();
			float y = buffer.getFloat();
			env.put(new Vector2(x, y), ID);
		}
		//-----------Warps---------------------//
		for(int i = 0; i < wSize; i++) {
			byte[] w = new byte[Warp.BYTES];
			buffer.get(w);
			warps.add(new Warp(w));
		}
	}

	
	/**
	 * WIDTH, HEIGHT, ENV_SIZE, WARP_SIZE.
	 * All remaining bytes for tile array, env hashmap, and warps hashset.
	 * @return
	 */
	public byte[] toByteArray() {
		int dataSize = Integer.BYTES * 4;
		dataSize += Short.BYTES * WIDTH * HEIGHT;
		dataSize += (Short.BYTES + FileManager.VECTOR2_BYTES) * env.size();
		dataSize += Warp.BYTES * warps.size(); 
		byte[] data = ByteBuffer.allocate(dataSize).array();
		ByteBuffer buffer = ByteBuffer.wrap(data).putInt(WIDTH).putInt(HEIGHT).putInt(env.size()).putInt(warps.size());		
		//-------------Tiles-------------------//
		for(int i = 0; i < WIDTH * HEIGHT; i++) {
			buffer.putShort(tiles[i]);
		}
		//------------EnvNobs-----------------//
		Iterator<Vector2> vIter = env.keySet().iterator();
		while(vIter.hasNext()) {
			Vector2 vect = vIter.next();
			buffer.putShort(env.get(vect));
			buffer.put(FileManager.toByteArray(vect));
		}
		//-----------Warps---------------------//
		Iterator<Warp> wIter = warps.iterator();
		while(wIter.hasNext()) {
			buffer.put(wIter.next().tobytes());
		}
		return data;
	}
}
