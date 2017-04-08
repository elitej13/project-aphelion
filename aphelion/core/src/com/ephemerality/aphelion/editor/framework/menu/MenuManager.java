package com.ephemerality.aphelion.editor.framework.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ephemerality.aphelion.editor.framework.Editor;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.VisUI.SkinScale;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;

public class MenuManager  implements MenuBar.MenuBarListener{
	
	
	private Stage stage;
	private MenuBar menuBar;
	
	
	public MenuManager() {
		VisUI.load(SkinScale.X1);

		stage = new Stage(new ScreenViewport());
		final Table root = new Table();
		root.setFillParent(true);
		stage.addActor(root);

		Gdx.input.setInputProcessor(stage);

		menuBar = new MenuBar();
		menuBar.setMenuListener(this);
		root.add(menuBar.getTable()).expandX().fillX().row();
		root.add().expand().fill();

		createMenus();
		
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
	private void createMenus () {
		FileMenu fileMenu = new FileMenu();
		EditMenu editMenu = new EditMenu();
		WindowMenu windowMenu = new WindowMenu();
		Menu helpMenu = new Menu("Help");

		helpMenu.addItem(new MenuItem("about", new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				Dialogs.showOKDialog(stage, "About", "Aphelion Editor Version: " + Editor.version);
			}
		}));

		menuBar.addMenu(fileMenu.menu);
		menuBar.addMenu(editMenu.menu);
		menuBar.addMenu(windowMenu.menu);
		menuBar.addMenu(helpMenu);
	}
	
	
	
	public void update() {
		
	}
	



	@Override
	public void menuOpened(Menu menu) {
	}


	@Override
	public void menuClosed(Menu menu) {
		
	}
	

	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	public void dispose () {
		VisUI.dispose();
		stage.dispose();
	}
	public void resize(int width, int height) {
		if (width == 0 && height == 0) return; //see https://github.com/libgdx/libgdx/issues/3673#issuecomment-177606278
		stage.getViewport().update(width, height, true);
//		PopupMenu.removeEveryMenu(stage);
//		WindowResizeEvent resizeEvent = new WindowResizeEvent();
//		for (Actor actor : stage.getActors()) {
//			actor.fire(resizeEvent);
//		}
	}
}
