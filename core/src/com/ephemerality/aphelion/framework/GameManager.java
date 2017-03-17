package com.ephemerality.aphelion.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.spawn.entities.EntityManager;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.ephemerality.aphelion.ui.UIManager;

public class GameManager {
	
	private MapManager map;
	private EntityManager ent;
	private UIManager ui;
	private Vector2 resolution;
	private Vector2 c0, c1;
//	private Rectangle screen;
	private boolean isPaused;
	
	public GameManager() {
		resolution = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		screen = new Rectangle(0, 0, resolution.x, resolution.y);
		c0 = new Vector2(0, 0);
		c1 = new Vector2(resolution);
		map = new MapManager();
		ent = new EntityManager(map);
		ui = new UIManager(ent.getPlayer());
	}
	
	
	public void update() {
		if(!isPaused) {
			ent.update();
//			screen.setPosition(screen.x + ent.deltaOffset.x, screen.y + ent.deltaOffset.y);
			c0.add(ent.deltaOffset);
			c1.add(ent.deltaOffset);
		}
		if(InputManager.checkForPause()) {
			isPaused = !isPaused;
			ui.setPause(isPaused);
		}

		ui.update();			
	}
	
	
	/**
	 * @param c0 Bottom left corner of view port
	 * @param c1 Top right corner of view port
	 */
	public void render(SpriteBatch sb) {
		map.render(sb, c0, c1);
		ent.render(sb, c0, c1);
		ui.render(sb);
	}
	
}
