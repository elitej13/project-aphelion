package com.ephemerality.aphelion.spawn.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.mob.Dummy;
import com.ephemerality.aphelion.spawn.entities.nob.items.Item;
import com.ephemerality.aphelion.spawn.entities.player.Player;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.ephemerality.aphelion.util.QuadTree;
import com.ephemerality.aphelion.util.Stats;

public class EntityManager {
	
	public Vector2 deltaOffset;
	public List<Entity> entities;
	List<Entity> removed;
	
	private QuadTree quad;
	private Player player;
	
	
	public EntityManager(ScreenManager screen, LoadManager assets, MapManager map) {
		float x = Gdx.graphics.getWidth();
		float y = Gdx.graphics.getHeight();
		
		deltaOffset = new Vector2();
		entities = new ArrayList<>();
		removed = new ArrayList<>();
		quad = new QuadTree(map);
		player = new Player(screen, assets, x / 2, y / 2);
		addEntity(player);
		addEntity(new Dummy(200, 200, assets, new Stats()));
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
		for(Entity e : entities) {
			if(e.isRemoved) {
				removed.add(e);
			}
		}
		entities.removeAll(removed);
		removed.clear();
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
