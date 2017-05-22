package com.ephemerality.aphelion.spawn.entities.nob.items;

import com.ephemerality.aphelion.spawn.entities.nob.Nob;

public class Item extends Nob {
	
	public static final short CHEST = 20000;
	public static final short SWORD = 20001;
	
	
	public Item(float x, float y, int w, int h, short ID) {
		super(x, y, w, h, true, ID);
	}

}
