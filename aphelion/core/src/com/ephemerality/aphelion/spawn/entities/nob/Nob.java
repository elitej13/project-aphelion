package com.ephemerality.aphelion.spawn.entities.nob;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ephemerality.aphelion.spawn.entities.Entity;
import com.ephemerality.aphelion.spawn.puppets.NobPuppet;

public class Nob extends Entity{

	public Nob(float x, float y, int w, int h, boolean renderable, TextureRegion texture, short ID) {
		super(x, y, w, h, renderable, ID);
		this.puppet = new NobPuppet(w, h, texture);
	}

}
