package com.ephemerality.aphelion.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class MenuManager {
	
	private Stage stage;
	private Skin skin;
	
	public MenuManager() {
		stage = new Stage();
		skin = new Skin();
		Gdx.input.setInputProcessor(stage);
		initSkin();
		initTable();
	}
	private void initSkin() {
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		skin.add("default", new BitmapFont());

		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
//		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
	}
	private void initTable() {
		Table table = new Table();
		table.setFillParent(true);
		table.setLayoutEnabled(true);
		stage.addActor(table);

		final TextButton start = new TextButton(" Start ", skin);
		final TextButton exit = new TextButton(" Exit ", skin);
		
		table.add(start);
		table.add(exit);
				
		start.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				start.setText(" Starting... ");
				Master.setState(1);
			}
		});
		exit.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				exit.setText(" Exiting... ");
				Master.setState(-1);
			}
		});
//	table.add(new Image(skin.newDrawable("white", Color.RED))).size(256);
	}
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

	
	
	public void update() {
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
	}
	
	public void render(ScreenManager screen) {
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}
}