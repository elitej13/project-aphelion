package com.ephemerality.aphelion.editor.framework.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ephemerality.aphelion.editor.framework.menu.MenuManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.VisUI.SkinScale;

public class GUI {
	
	
	final Table root;
	Stage stage;
	TilePane tPane;
	ScreenManager screen;
	MenuManager menu;
	MapManager map;
	TileScreen tScreen;
	boolean tUp, tDown, tLeft, tRight;
	
	public GUI (ScreenManager screen, MapManager map) {
		this.map = map;
		this.screen = screen;
		VisUI.load(SkinScale.X1);
		
		stage = new Stage(new ScreenViewport(), screen.getSpriteBatch());
		root = new Table();
		root.setFillParent(true);
		stage.addActor(root);
		
		Gdx.input.setInputProcessor(stage);
		
		initActors(map);
		initDebug();
		
	}
	
	
	public void initDebug() {
		stage.addListener(new InputListener() {
			boolean debug = false;

			@Override
			public boolean keyDown (InputEvent event, int keycode) {
				if (keycode == Keys.F12) {
					debug = !debug;
					root.setDebug(debug, true);
					for (Actor actor : stage.getActors()) {
						if (actor instanceof Group) {
							Group group = (Group) actor;
							group.setDebug(debug, true);
						}
					}
					return true;
				}

				return false;
			}
		});
	}
	
	/**
	 * Takes in the pixel precise position on viewport.
	 * Transforms it to map to a tile position given screen bounds offset.
	 * @param x
	 * @param y
	 */
	
	public void initActors(MapManager map) {
		menu = new MenuManager(stage, root, map);
		tPane = new TilePane();
		tScreen = new TileScreen(map);
		tScreen.addListener((InputListener) new TileScreenListener(tScreen, tPane));
		stage.addActor(tPane);
		stage.addActor(tScreen);
	}
	
	public void update() {
		tScreen.update();
	}
	

	public void render () {
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}
	public void resize(int width, int height) {
		if (width == 0 && height == 0) return; //see https://github.com/libgdx/libgdx/issues/3673#issuecomment-177606278
		stage.getViewport().update(width, height, true);
	}
	public void dispose () {
		VisUI.dispose();
		stage.dispose();
	}


	
}
