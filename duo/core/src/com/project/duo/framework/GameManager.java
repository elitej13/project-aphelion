package com.project.duo.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.project.duo.spawn.entities.EntityManager;
import com.project.duo.spawn.world.MapManager;

public class GameManager {
	
	private MapManager map;
	private EntityManager ent;
	private Vector2 resolution;
	private Vector2 c0, c1;
	private Rectangle bounds;
	
	
	public GameManager() {
		resolution = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		c0 = new Vector2(0, 0);
		c1 = new Vector2(resolution);
		map = new MapManager();
		ent = new EntityManager();
	}
	
	
	public void update() {
		ent.update();
		//TODO: \/ may have to change this if it's wrong
		c0.add(ent.deltaOffset);
		c1.add(ent.deltaOffset);
//		System.out.println("Offset: " + c0.toString());

	}
	/**
	 * @param c0 Bottom left corner of view port
	 * @param c1 Top right corner of view port
	 */
	public void render(SpriteBatch sb) {
		map.render(sb, c0, c1);
		ent.render(sb, c0, c1);
	}
	
}
