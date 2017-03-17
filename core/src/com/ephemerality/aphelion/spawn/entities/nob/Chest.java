package com.ephemerality.aphelion.spawn.entities.nob;

import com.ephemerality.aphelion.graphics.Sprite;
import com.ephemerality.aphelion.spawn.puppets.NobPuppet;

public class Chest extends Nob{

	public Chest(float x, float y) {
		super(x, y, 64, 64, true);
		puppet = new NobPuppet(64, 64, Sprite.wood_box_0);
	}

}
