package com.ephemerality.aphelion.spawn.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.ephemerality.aphelion.util.QuadTree;

public class EntityManager {
	
	public Vector2 deltaOffset;
	public List<Entity> entities;

	private QuadTree quad;
	private Player player;
	
//	For DEBUGGING purposes
//	private Mob dummy;
//	private Chest chest;
	
	
	
	public EntityManager(ScreenManager screen, MapManager map) {
		float x = Gdx.graphics.getWidth();
		float y = Gdx.graphics.getHeight();
		
		deltaOffset = new Vector2();
		entities = new ArrayList<Entity>();
		quad = new QuadTree(map);
		
		player = new Player(screen, x / 2, y / 2);
//		dummy = new Mob(screen, 100, 100, 32, 32);
//		chest = new Chest(200, 200);
		
		addEntity(player);
//		addEntity(dummy);
//		addEntity(chest);
	}
	
	
	
	public void update() {
		player.update();
		quad.update();
		Collections.sort(entities);
	}
	
//	TODO: give conditionals to entities for scripts...etc
	public void addEntity(Entity e) {
		entities.add(e);
		quad.addEntity(e);
	}
	
	
	public void render(ScreenManager screen) {
		for(Entity e : entities) e.render(screen);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	
	
}
