package com.ephemerality.aphelion.customizer;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.Entity;
import com.ephemerality.aphelion.spawn.entities.EntityManager;
import com.ephemerality.aphelion.spawn.entities.nob.EnvNob;
import com.ephemerality.aphelion.spawn.entities.nob.Environment;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.ephemerality.aphelion.util.Util;

public class GameManager {
	
	public MapManager map;
	public EntityManager ent;
	
	public GameManager() {
		map = new MapManager();
		ent = new EntityManager(map);
	}
	
	public void update() {
		
	}
	
	/**
	 * 
	 * @param x in tile map coordinates
	 * @param y in tile map coordinates
	 * @param ID of the tile being placed
	 * @param type of the tile being placed: Tile, Env
	 */
	public void editMap(ScreenManager screen, int x, int y, short ID) {
		if(GUIManager.active == -2) {
			Vector2 v = new Vector2(x, y);
			removeEnvs(v);
		}else {
			if(Util.isTile(ID)) {
				map.editTile(x / MapManager.tileSize, y / MapManager.tileSize, ID);
			}else if(Util.isEnv(ID)) {
				EnvNob env = Environment.instantiateEnvNob(x, y, ID);
				if(Util.isBodyWithinBounds(env.body, Vector2.Zero, map.mapPixelSize)) {
					map.editEnv(x, y, ID);
					ent.addEntity(env);				
				}
			}			
		}
	}
	public void removeEnvs(Vector2 v) {
		ArrayList<Entity> removedEnts = new ArrayList<>();
		ArrayList<Vector2> removedVects = new ArrayList<>();
		for(Entity e : ent.entities) {
			if(e instanceof EnvNob) {
				if(e.body.contains(v)) {
					removedEnts.add(e);
					removedVects.add(e.body.getPosition(new Vector2()));
				}
			}
		}
		ent.entities.removeAll(removedEnts);
		for(Vector2 vect : removedVects) {
			map.level.env.remove(vect);
		}
	}
	public void render(ScreenManager screen) {
		map.renderBackGround(screen);
		ent.render(screen);
	}
	public void resize(int width, int height) {
		
	}
	public boolean saveMap(String name) {
		return map.save(name);
	}
	public boolean loadMap(String name) {
		if(!map.load(name)) return false;
		ent.resetMapInitEnvs();
		return true;
	}
}
