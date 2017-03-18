package com.ephemerality.aphelion.spawn.entities.nob;

import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.spawn.puppets.NobPuppet;

public class Chest extends Nob{

	public Chest(float x, float y) {
		super(x, y, 64, 64, true);
		puppet = new NobPuppet(64, 64, SpriteSheet.wood_box_0);
	}

}
