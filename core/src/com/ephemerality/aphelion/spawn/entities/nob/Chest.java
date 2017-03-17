package com.project.duo.spawn.entities.nob;

import com.project.duo.graphics.Sprite;
import com.project.duo.spawn.puppets.NobPuppet;

public class Chest extends Nob{

	public Chest(float x, float y) {
		super(x, y, 64, 64, true);
		puppet = new NobPuppet(64, 64, Sprite.wood_box_0);
	}

}
