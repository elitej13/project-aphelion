package com.ephemerality.aphelion.spawn.entities.nob.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ephemerality.aphelion.graphics.LoadManager;

public class Sword extends Item{

	public Sword(float x, float y, LoadManager assets) {
		super(x, y, 32, 32, true, new TextureRegion(assets.getTexture(LoadManager.ITEM_SHEET), 0, 32, 32, 32), Item.SWORD);
	}

}
