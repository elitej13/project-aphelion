package com.project.duo.spawn.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.project.duo.util.QuadTree;

public class EntityManager {
	
	private QuadTree quad;
	private Player player;
	private Entity dummy;
	public Vector2 deltaOffset;
	public List<Entity> renderable;
	
	
	public EntityManager(Vector2 mapPixelSize) {
		float x = Gdx.graphics.getWidth();
		float y = Gdx.graphics.getHeight();
		deltaOffset = new Vector2();
		renderable = new ArrayList<Entity>();
		quad = new QuadTree(mapPixelSize);
		
		player = new Player(x / 2, y / 2);
		dummy = new Entity(100, 100, 64, 64);
		
		addEntity(player);
		addEntity(dummy);
	}
	
	
	
	public void update() {
		float x = player.body.x;
		float y = player.body.y;
		player.update();
		x = player.body.x - x;
		y = player.body.y - y;
		deltaOffset.set(x, y);
	}
	
	//TODO: give conditionals to renderable add
	public void addEntity(Entity e) {
		renderable.add(e);
		quad.addEntity(e);
	}
	
	
	public void render(SpriteBatch sb, Vector2 c0, Vector2 c1) {
		for(Entity e : renderable) e.render(sb, c0, c1);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	
	
}
