package com.project.duo.spawn.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EntityManager {
	
	private Player player;
	public Vector2 deltaOffset;
	
	public EntityManager() {
		float x = Gdx.graphics.getWidth();
		float y = Gdx.graphics.getHeight();
		player = new Player(x / 2, y / 2);
		deltaOffset = new Vector2();
	}
	
	
	
	public void update() {
		float x = player.position.x;
		float y = player.position.y;
//		System.out.println("Pre pos: " + player.position.toString());
		player.update();
//		System.out.println("Post pos: " + player.position.toString());
		x = player.position.x - x;
		y = player.position.y - y;
		deltaOffset.set(x, y);
	}
	
	
	public void render(SpriteBatch sb, Vector2 c0, Vector2 c1) {
		player.render(sb, c0, c1);
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
