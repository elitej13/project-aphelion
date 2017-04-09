package com.ephemerality.aphelion.editor.framework.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.ephemerality.aphelion.editor.framework.Editor;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;

public class HelpMenu extends ChangeListener {

	
	Menu menu;
	MenuItem about;
	
	public HelpMenu() {
		
		about = new MenuItem("about", this);
		menu = new Menu("Help");
		menu.addItem(about);
	
	}
	@Override
	public void changed(ChangeEvent event, Actor actor) {
		Dialogs.showOKDialog(actor.getStage(), "About", "Aphelion Editor Version: " + Editor.version);
	}

}
