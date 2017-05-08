package com.ephemerality.aphelion.spawn.puppets;

import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class MobPuppet extends Puppet{

	public Doll doll;
	
	public MobPuppet(float w, float h) {
		super(w, h);
		doll = new Doll();
	}
	
	@Override
	public void init(LoadManager assets) {
		doll.init(assets.getDollInfo(LoadManager.MONSTER_SCML));
	}
	@Override
	public void update() {
		doll.update();
	}

	@Override
	public void setPosition(float x, float y) {
		doll.setPosition(x, y);
	}

	@Override
	public void render(ScreenManager screen) {
		doll.render(screen);
	}

	
	
	
	
	

}
