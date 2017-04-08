package com.ephemerality.aphelion.editor.framework.menu;


import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class WindowMenu extends ChangeListener{
	
	Menu menu;
	MenuItem fullscreen;
	
	public WindowMenu() {
		menu = new Menu("Window");
		fullscreen = new MenuItem("FullScreen",  this).setShortcut("f11");
		

		menu.addItem(fullscreen);
	}

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		if(actor.equals(fullscreen)) {
			Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		}
		
	}



	
	
}
