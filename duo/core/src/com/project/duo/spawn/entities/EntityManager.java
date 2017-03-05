package com.project.duo.spawn.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.project.duo.input.InputManager;

public class EntityManager {
	
	private Player player;
	public Vector2 deltaOffset;
	
	public EntityManager() {
		player = new Player(0, 0);
		deltaOffset = new Vector2();
	}
	
	
	
	public void update(InputManager input) {
		float x = player.position.x;
		float y = player.position.y;
		player.update(input);
		x -= player.position.x;
		y -= player.position.y;
		deltaOffset.set(x, y);
	}
	
	
	public void render(SpriteBatch sb, Vector2 c0, Vector2 c1) {
		
	}
	
	
}
