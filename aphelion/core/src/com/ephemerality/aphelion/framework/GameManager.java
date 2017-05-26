package com.ephemerality.aphelion.framework;

import com.badlogic.gdx.math.Rectangle;
import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.spawn.entities.EntityManager;
import com.ephemerality.aphelion.spawn.entities.player.Player;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.ephemerality.aphelion.spawn.world.Warp;
import com.ephemerality.aphelion.ui.UIManager;

public class GameManager {
	
	private MapManager map;
	private EntityManager ent;
	private UIManager ui;
	public boolean isPaused;
	
	static boolean requestedWarp;
	static Warp warpTo;
	
	public GameManager(ScreenManager screen, LoadManager assets) {
		map = new MapManager();
		ent = new EntityManager(screen, assets, map);
		ui = new UIManager(ent.getPlayer());
	}
	
	public void update() {
		if(!isPaused) {
			ent.update();
			if(requestedWarp) {
				warp(warpTo);
				requestedWarp = false;
			}
		}
		if(InputManager.checkForPause()) {
			isPaused = !isPaused;
			ui.setPause(isPaused);
		}
		ui.update();
	}
	public static void requestWarp(Warp warp) {
		requestedWarp = true;
		warpTo = warp;
	}
	public Warp warp(Warp warp) {
		if(!warp.inLevel) {
			String name = warp.getDestination();
			loadLevel(name, "maps/" + name + ".bin", false);
		}
		Player player = ent.getPlayer();
		warp.positionBody(player.body);
		player.screen.setPosition(player.body.x, player.body.y);
		return warp;
	}
	public void loadLevel(String name, String path, boolean absolutepath) {
		map.load(name, path, absolutepath);
		ent.refreshQuad(map);
	}
	public void resizeLevel(int w, int h) {
		map.resize(w, h);
		ent.refreshQuad(map);
	}
	/**
	 * @param c0 Bottom left corner of view port
	 * @param c1 Top right corner of view port
	 */
	public void render(ScreenManager screen) {
		map.render(screen);
		ent.render(screen);
		ui.render(screen);
	}
	
	
	public void dispose() {
		
	}
	
}
