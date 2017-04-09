package com.ephemerality.aphelion.editor.framework.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.kotcrab.vis.ui.widget.MenuBar;

public class MenuManager  {
	
	
	MenuBar menuBar;
	
	public MenuManager(Table root, MapManager map) {

		menuBar = new MenuBar();
		root.add(menuBar.getTable()).expandX().fillX().row();
		root.add().expand().fill();

		createMenus(map);
		
		
		
	}
	private void createMenus (MapManager map) {
		FileMenu fileMenu = new FileMenu(map);
		EditMenu editMenu = new EditMenu();
		WindowMenu windowMenu = new WindowMenu();
		HelpMenu helpMenu = new HelpMenu();


		menuBar.addMenu(fileMenu.menu);
		menuBar.addMenu(editMenu.menu);
		menuBar.addMenu(windowMenu.menu);
		menuBar.addMenu(helpMenu.menu);
	}

}
