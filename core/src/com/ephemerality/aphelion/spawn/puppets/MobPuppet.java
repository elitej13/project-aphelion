package com.ephemerality.aphelion.spawn.puppets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.Drawer;
import com.brashmonkey.spriter.Player;
import com.brashmonkey.spriter.SCMLReader;
import com.brashmonkey.spriter.LibGdx.LibGdxDrawer;
import com.brashmonkey.spriter.LibGdx.LibGdxLoader;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class MobPuppet extends Puppet{

	Player player;
	Drawer<Sprite> drawer;
	ShapeRenderer renderer;
	
	public MobPuppet(ScreenManager screen, int w, int h) {
		super(w, h);

		FileHandle handle = Gdx.files.internal("monster/basic_002.scml");
		Data data = new SCMLReader(handle.read()).getData();
		LibGdxLoader loader = new LibGdxLoader(data);
		
		renderer = new ShapeRenderer();
		loader.load(handle.file());
		drawer = new LibGdxDrawer(loader, screen.getSpriteBatch(), renderer);
		
		player = new Player(data.getEntity(0));
		player.setScale(0.35f);
	}
	
	
	public void setAnimation(String anim) {
		player.setAnimation(anim);
	}
	public void flipX() {
		player.flipX();
	}
	public boolean flippedX() {
		if(player.flippedX() == 1)
			return false;
		return true;
	}
	
	
	@Override
	public void update() {
		player.update();
	}
	
	public void setPosition(float x, float y) {
		player.setPosition(x, y);
	}
	
	
	public void render(ScreenManager screen) {
		drawer.draw(player);
	}

}
