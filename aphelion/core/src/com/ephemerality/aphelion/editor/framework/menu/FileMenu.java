package com.ephemerality.aphelion.editor.framework.menu;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;

public class FileMenu implements EventListener{
	
	Menu menu;
	
	public FileMenu() {
		menu = new Menu("File");
		MenuItem newFile = new MenuItem("New").setShortcut("f1");
		newFile.addListener(this);
		MenuItem saveAs = new MenuItem("Save As").setShortcut("ctrl + shift + s");
		saveAs.addListener(this);
		MenuItem save = new MenuItem("Save").setShortcut("ctrl + s");
		save.addListener(this);
		MenuItem load = new MenuItem("Load").setShortcut("f2");
		load.addListener(this);
		MenuItem exit = new MenuItem("Exit").setShortcut("alt + f4");
		exit.addListener(this);
		
		menu.addItem(newFile);
		menu.addItem(saveAs);
		menu.addItem(save);
		menu.addItem(load);
		menu.addItem(exit);
		
	}

	@Override
	public boolean handle(Event event) {
		return false;
	}
	
	
}
