package com.ephemerality.aphelion.spawn.world;

import java.nio.ByteBuffer;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.spawn.entities.tiles.Tile;

public class Level {
	
	public Rectangle[] collidable;
	public short[] tiles, entities;
	public int WIDTH, HEIGHT;

	public Level(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		tiles = new short[width * height];
		for(short i = 0; i < tiles.length; i++) {
			tiles[i] = Tile.BRICK_ID; 
		}
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
				if(current == 255) {
					collidable[(WIDTH - x - 1) + ((HEIGHT - y - 1) * WIDTH)] = new Rectangle((WIDTH - x - 1) << 6, (WIDTH - y - 1) << 6, MapManager.tileSize, MapManager.tileSize);
				}
			}
		}
	}
	
	
	public void editTile(Vector2 position, short tileID) {
		if(position.x < 0 || position.x >= WIDTH || position.y < 0 || position.y >= HEIGHT)
			return;
		tiles[(int) (position.x + position.y * WIDTH)] = tileID;
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
