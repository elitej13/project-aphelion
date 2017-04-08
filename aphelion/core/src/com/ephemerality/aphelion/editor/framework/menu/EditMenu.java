package com.ephemerality.aphelion.editor.framework.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;

public class EditMenu extends ChangeListener{
	
	Menu menu;
	
	public EditMenu() {
		menu = new Menu("Edit");
		MenuItem preferences= new MenuItem("Preferences").setShortcut("f9");
		preferences.addListener(this);
		

		menu.addItem(preferences);
	}

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		
	}

	
	
}