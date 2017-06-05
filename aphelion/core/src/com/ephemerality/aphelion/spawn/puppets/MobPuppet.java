package com.ephemerality.aphelion.spawn.puppets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.util.Direction;

public class MobPuppet extends Puppet{

	public Doll doll;
	
	public MobPuppet(float x, float y, float w, float h, LoadManager assets, String string) {
		super(x, y, w, h);
		doll = new Doll(assets.getDollInfo(string));
		doll.setPosition(x, y);
	}
	@Override
	public void update() {
		doll.update();
	}
	public void updatePosition(float x, float y) { 
		this.x = x;
		this.y = y;
		doll.setPosition(x, y);
	}
	public void updateAnim(Direction dir) {
		if(dir == Direction.EAST && doll.flippedX())
			doll.flipX();
		if(dir == Direction.WEST && !doll.flippedX())
			doll.flipX();
	}

	@Override
	public void render(ScreenManager screen) {
		doll.render();
	}

	public boolean hasAnimationFinished() {
		if(doll.finished) {
			doll.finished = false;
			return true;
		}
		return false;
	}
	@Override
	public TextureRegion getIcon() {
		return null;
	}
	@Override
	public void anim(float speed) {
		// TODO Auto-generated method stub
		
	}
	

}
