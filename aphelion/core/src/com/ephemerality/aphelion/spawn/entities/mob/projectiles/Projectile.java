package com.ephemerality.aphelion.spawn.entities.mob.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.persona.Stats;

public class Projectile {
	
	Vector2 position;
	Vector2 velocity;
	
	public Projectile(float x, float y, float xx, float yy, short ID, Stats stats) {
		position = new Vector2(x, y);
		
	}

	public void update() {
		position.add(velocity);
	}
	
	public void render(ScreenManager screen) {
		//TODO: fill this in
	}

}
