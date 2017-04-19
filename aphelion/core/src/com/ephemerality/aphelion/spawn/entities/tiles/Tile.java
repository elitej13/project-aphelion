package com.ephemerality.aphelion.spawn.entities.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ephemerality.aphelion.spawn.entities.nob.Nob;

public class Tile extends Nob{

	public final static short VOID_ID	 = 10000;
	public final static short GRASS_ID	 = 10001;
	public final static short DIRT_ID	 = 10002;
	public final static short BRICK_ID 	 = 10003;
	public final static short WOOD_ID 	 = 10004;
	
	public Tile(float x, float y, int w, int h, TextureRegion texture, short ID) {
		super(x, y, w, h, true, texture, ID);
	}

}
