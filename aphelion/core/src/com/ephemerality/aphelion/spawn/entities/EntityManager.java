package com.ephemerality.aphelion.spawn.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.effects.ParticleSpawner;
import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.Save;
import com.ephemerality.aphelion.persona.Equip;
import com.ephemerality.aphelion.persona.Stats;
import com.ephemerality.aphelion.spawn.entities.mob.Dummy;
import com.ephemerality.aphelion.spawn.entities.nob.items.Item;
import com.ephemerality.aphelion.spawn.entities.player.Player;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.ephemerality.aphelion.spawn.world.QuadTree;

public class EntityManager {
	
	public Vector2 deltaOffset;
	public List<Entity> entities;
//	public List<ParticleSpawner> particles;
	public List<Object> removed;
	
	public QuadTree quad;
	public Player player;
	
	
	public EntityManager(ScreenManager screen, LoadManager assets, MapManager map) {
		float x = Gdx.graphics.getWidth();
		float y = Gdx.graphics.getHeight();
		
		deltaOffset = new Vector2();
		entities = new ArrayList<>();
//		particles = new ArrayList<>();
		removed = new ArrayList<>();
		quad = new QuadTree(map);
		
		player = new Player(screen, assets, x / 2, y / 2);
		
		addEntity(player);
		addEntity(new Dummy(200, 200, assets, new Equip(new Stats())));
		addEntity(new Item(50, 50, 32, 32, Item.SWORD));
		addEntity(new Item(100, 50, 32, 32, Item.CHEST));
		
		//TODO : Fix the particle render/spawn bug.
//		particles.add(new ParticleSpawner(200f, 200f, 1000, 100, 50));
	}
	public EntityManager(ScreenManager screen, LoadManager assets, MapManager map, Save save) {
		float x = Gdx.graphics.getWidth();
		float y = Gdx.graphics.getHeight();
		
		deltaOffset = new Vector2();	
		entities = new ArrayList<>();
//		particles = new ArrayList<>();
		removed = new ArrayList<>();
		quad = new QuadTree(map);
		
		player = new Player(screen, assets, save.player);
	}
	public void refreshQuad(MapManager map) {
		quad = new QuadTree(map);
		for(Entity e : entities) 
			quad.addEntity(e);
	}
		
	public void update() {
		for(Entity e : entities)
			e.update();
		
		for(Entity e : entities)
			if(e.isRemoved)
				removed.add(e);
		
		entities.removeAll(removed);
		removed.clear();
		quad.update();
		
//		for(ParticleSpawner ps : particles) 
//			ps.update();
		Collections.sort(entities);
	}
	
//	TODO: give conditionals to entities for scripts...etc
	public void addEntity(Entity e) {
		quad.addEntity(e);
		entities.add(e);
	}
	
	public void render(ScreenManager screen) {
		quad.render(screen);
		for(Entity e : entities) 
			e.render(screen);
//		for(ParticleSpawner ps : particles) 
//			ps.render(screen);
		
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
