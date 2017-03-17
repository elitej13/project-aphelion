package com.project.duo.spawn.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.project.duo.spawn.entities.mob.Mob;
import com.project.duo.spawn.entities.nob.Chest;
import com.project.duo.spawn.world.MapManager;
import com.project.duo.util.QuadTree;

public class EntityManager {
	
	public Vector2 deltaOffset;
	public List<Entity> renderable;

	private QuadTree quad;
	private Player player;
	
//	For DEBUGGING purposes
	private Mob dummy;
	private Chest chest;
	
	
	
	public EntityManager(MapManager map) {
		float x = Gdx.graphics.getWidth();
		float y = Gdx.graphics.getHeight();
		deltaOffset = new Vector2();
		renderable = new ArrayList<Entity>();
		quad = new QuadTree(map);
		
		player = new Player(x / 2, y / 2);
		dummy = new Mob(100, 100, 32, 32);
		chest = new Chest(200, 200);
		
		addEntity(player);
		addEntity(dummy);
		addEntity(chest);
	}
	
	
	
	public void update() {
		float x = player.body.x;
		float y = player.body.y;
		player.update();
		x = player.body.x - x;
		y = player.body.y - y;
		deltaOffset.set(x, y);
		
		quad.update();
		Collections.sort(renderable);
	}
	
	//TODO: give conditionals to renderable for scripts...etc
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
