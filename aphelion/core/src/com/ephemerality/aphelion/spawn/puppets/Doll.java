package com.ephemerality.aphelion.spawn.puppets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.brashmonkey.spriter.Drawer;
import com.brashmonkey.spriter.Player;
import com.brashmonkey.spriter.LibGdx.LibGdxDrawer;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.DollInfo;

public class Doll {
	
	
	public Player player;
	public Drawer<Sprite> drawer;
	
	public void init(DollInfo info) {
		drawer = new LibGdxDrawer(info.loader, info.batch, info.renderer);
		player = new Player(info.data.getEntity(0));
	}
	
	public void update() {
		player.update();
	}
	
	
	public void setPosition(float x, float y) {
		player.setPosition(x, y);
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

	public void render(ScreenManager screen) {
		drawer.draw(player);
	}

}
