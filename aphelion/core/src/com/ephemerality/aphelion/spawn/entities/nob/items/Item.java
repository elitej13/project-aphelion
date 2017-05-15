package com.ephemerality.aphelion.spawn.entities.nob.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ephemerality.aphelion.spawn.entities.nob.Nob;

public class Item extends Nob {
	
	public static final short CHEST = 20000;
	public static final short SWORD = 20001;
	
	
	public Item(float x, float y, int w, int h, boolean renderable, TextureRegion texture, short ID) {
		super(x, y, w, h, renderable, texture, ID);
	}

}
