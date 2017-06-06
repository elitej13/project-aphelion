package com.ephemerality.aphelion.spawn.equipment.weapon;

import com.ephemerality.aphelion.spawn.entities.nob.items.Item;

public class Weapon extends Item {
	
	private float strength;
	
	public Weapon(float x, float y, int w, int h, short ID) {
		super(x, y, w, h, ID);
	}
	
	
	public float getStrength() {
		return strength;
	}
}
