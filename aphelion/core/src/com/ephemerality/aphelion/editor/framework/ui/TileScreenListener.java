package com.ephemerality.aphelion.editor.framework.ui;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TileScreenListener extends InputListener{
	
	TileScreen tScreen;
	TilePane tPane;
	
	public TileScreenListener(TileScreen tScreen, TilePane tPane) {
		this.tScreen = tScreen;
		this.tPane = tPane;
	}
	
	@Override
	public void touchDragged (InputEvent event, float x, float y, int pointer) {
		tScreen.editTile(x, y, tPane.getSelected());
	}
	@Override
	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		tScreen.editTile(x, y, tPane.getSelected());
		return true;
	}
	@Override
	public boolean keyDown (InputEvent event, int keycode) {
		if(keycode == Keys.W || keycode == Keys.UP) tScreen.up = true;
		if(keycode == Keys.A || keycode == Keys.LEFT) tScreen.left = true;
		if(keycode == Keys.S || keycode == Keys.DOWN) tScreen.down = true;
		if(keycode == Keys.D || keycode == Keys.RIGHT) tScreen.right = true;
		return true;
	}
	@Override
	public boolean keyUp (InputEvent event, int keycode) {
		if(keycode == Keys.W || keycode == Keys.UP) tScreen.up = false;
		if(keycode == Keys.A || keycode == Keys.LEFT) tScreen.left = false;
		if(keycode == Keys.S || keycode == Keys.DOWN) tScreen.down = false;
		if(keycode == Keys.D || keycode == Keys.RIGHT) tScreen.right = false;
		return true;
	}
}
