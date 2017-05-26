package com.ephemerality.aphelion.spawn.entities.mob.projectiles;

import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.spawn.entities.mob.Mob;
import com.ephemerality.aphelion.util.Stats;

public class Projectile extends Mob {

	public Projectile(float x, float y, float xx, float yy, int w, int h, short ID, LoadManager assets, String asset, Stats stats) {
		super(x, y, w, h, ID, assets, asset, stats);
	}

	@Override
	public void behavior() {
		
	}

}
