package com.ephemerality.aphelion.spawn.entities.nob;

import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.spawn.entities.nob.items.Item;

public class Chest extends Nob{

	public Chest(float x, float y) {
		super(x, y, 32, 32, true, SpriteSheet.wood_box_0, Item.CHEST);
	}

}
