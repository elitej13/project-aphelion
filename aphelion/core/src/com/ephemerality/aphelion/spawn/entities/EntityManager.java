package com.ephemerality.aphelion.spawn.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.mob.Mob;
import com.ephemerality.aphelion.spawn.entities.player.Player;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.ephemerality.aphelion.util.QuadTree;

public class EntityManager {
	
	public Vector2 deltaOffset;
	public List<Entity> entities;

	private QuadTree quad;
	private Player player;
	
//	For DEBUGGING purposes
	private Mob dummy;
//	private Chest chest;
	
	
	
	public EntityManager(ScreenManager screen, MapManager map) {
		float x = Gdx.graphics.getWidth();
		float y = Gdx.graphics.getHeight();
		
		deltaOffset = new Vector2();
		entities = new ArrayList<>();
		quad = new QuadTree(map);
		player = new Player(screen, x / 2, y / 2);
		dummy = new Mob(200, 200, 128, 64, (short) 0);
//		chest = new Chest(200, 200);
		
		addEntity(player);
//		addEntity(dummy);
//		addEntity(chest);
	}
	public void refreshQuad(MapManager map) {
		quad = new QuadTree(map);
		for(Entity e : entities) 
			quad.addEntity(e);;
	}
	public void init(LoadManager assets) {
		for(Entity e : entities) {
			e.init(assets);
		}
	}
	
	public void update() {
		for(Entity e : entities) {
			e.update();
		}
		quad.update();
		Collections.sort(entities);
	}
	
//	TODO: give conditionals to entities for scripts...etc
	public void addEntity(Entity e) {
		quad.addEntity(e);
		entities.add(e);
	}
	
	
	public void render(ScreenManager screen) {
		quad.render(screen);
		for(Entity e : entities) e.render(screen);
//		DEBUGGINB purposes	//
		
	}
	
	public Player getPlayer() {
		return player;
	}
	
	
	
}
