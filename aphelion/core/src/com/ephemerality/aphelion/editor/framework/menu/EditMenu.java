package com.ephemerality.aphelion.editor.framework.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.ephemerality.aphelion.editor.framework.ui.TileScreen;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.kotcrab.vis.ui.util.Validators;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.util.dialog.InputDialogAdapter;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;

public class EditMenu extends ChangeListener{
	
	Stage stage;
	Menu menu;
	MenuItem preferences;
	MenuItem resizeTiles;
	MapManager map;
	int width, height;
	
	public EditMenu(Stage stage, MapManager map) {
		this.stage = stage;
		this.map = map;
		
		menu = new Menu("Edit");
		preferences= new MenuItem("Preferences").setShortcut("f5");
		preferences.addListener(this);
		resizeTiles= new MenuItem("Resize Level").setShortcut("f6");
		resizeTiles.addListener(this);

		menu.addItem(preferences);
		menu.addItem(resizeTiles);
	}

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		if(actor.equals(preferences)) {
			
		}else if(actor.equals(resizeTiles)) {
			Dialogs.showInputDialog(stage, "Enter Width", "W:", Validators.INTEGERS, new InputDialogAdapter() {
				@Override
				public void finished (String input) {
					Dialogs.showInputDialog(stage, "Enter Height", "H:", Validators.INTEGERS, new InputDialogAdapter() {
						@Override
						public void finished (String input) {
							height = Integer.parseInt(input);
							map.editSize(width, height);
							for(Actor actor : stage.getActors()) {
								if(actor instanceof TileScreen) {
									TileScreen tScreen = (TileScreen) actor;
									int w = (int) Math.min(map.getMapSize().x, Gdx.graphics.getWidth() * 0.75f);
									int h = (int) Math.min(map.getMapSize().y, Gdx.graphics.getHeight() * 0.9f) + 25;
									tScreen.setSize(w, h);
								}
							}
						}
					});
					width = Integer.parseInt(input);
				}
			});
		}
	}

	
	
}