package com.ephemerality.aphelion.spawn.entities.tiles;

import com.ephemerality.aphelion.spawn.entities.nob.Nob;

public class Tile extends Nob{

	
	public final int ID;
	
	
	public Tile(float x, float y, int w, int h, int id) {
		super(x, y, w, h, true);
		this.ID = id;
	}

}
