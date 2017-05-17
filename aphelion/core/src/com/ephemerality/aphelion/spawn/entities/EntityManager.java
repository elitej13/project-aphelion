package com.ephemerality.aphelion.spawn.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.mob.Mob;
import com.ephemerality.aphelion.spawn.entities.nob.items.Item;
import com.ephemerality.aphelion.spawn.entities.player.Player;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.ephemerality.aphelion.util.QuadTree;

public class EntityManager {
	
	public Vector2 deltaOffset;
	public List<Entity> entities;

	private QuadTree quad;
	private Player player;
	
	
	public EntityManager(ScreenManager screen, LoadManager assets, MapManager map) {
		float x = Gdx.graphics.getWidth();
		float y = Gdx.graphics.getHeight();
		
		deltaOffset = new Vector2();
		entities = new ArrayList<>();
		quad = new QuadTree(map);
		player = new Player(screen, assets, x / 2, y / 2);
		addEntity(player);
		addEntity(new Mob(200, 200, 128, 64, (short) 30000, assets));
		addEntity(new Item(50, 50, 32, 32, Item.SWORD));
	}
	
	public void refreshQuad(MapManager map) {
		quad = new QuadTree(map);
		for(Entity e : entities) 
			quad.addEntity(e);;
	}
		
	public void update() {
		for(Entity e : entities) {
			e.update();
		}
		if(quad.update()) {
			entities.removeAll(quad.removed);
			quad.removed.clear();
		}
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
