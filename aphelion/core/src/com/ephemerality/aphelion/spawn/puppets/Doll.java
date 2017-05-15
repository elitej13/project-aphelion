package com.ephemerality.aphelion.spawn.puppets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.brashmonkey.spriter.Animation;
import com.brashmonkey.spriter.Drawer;
import com.brashmonkey.spriter.Mainline.Key;
import com.brashmonkey.spriter.Player;
import com.brashmonkey.spriter.Player.PlayerListener;
import com.brashmonkey.spriter.LibGdx.LibGdxDrawer;
import com.ephemerality.aphelion.input.DollInfo;

public class Doll implements PlayerListener{
	
	
	public Player player;
	public Drawer<Sprite> drawer;
	public boolean finished;
	
	public Doll(DollInfo info) {
		drawer = new LibGdxDrawer(info.loader, info.batch, info.renderer);
		player = new Player(info.data.getEntity(0));
		player.setScale(0.3f);
		player.setAnimation("idle");
		player.speed = 20;
		player.addListener(this);
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

	public void render() {
		drawer.draw(player);
	}

	@Override
	public void animationFinished(Animation animation) {
		finished = true;
	}

	@Override
	public void animationChanged(Animation oldAnim, Animation newAnim) {
		
	}

	@Override
	public void preProcess(Player player) {
		
	}

	@Override
	public void postProcess(Player player) {
		
	}

	@Override
	public void mainlineKeyChanged(Key prevKey, Key newKey) {
		
	}

}
