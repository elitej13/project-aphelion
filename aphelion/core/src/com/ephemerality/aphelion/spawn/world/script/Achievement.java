package com.ephemerality.aphelion.spawn.world.script;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.ephemerality.aphelion.framework.GameManager;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class Achievement {
	
	public String name;
	public boolean isGot;
	public boolean finishedRender;
	public float renderTime;
	
	public Achievement(String name) {
		this.name = name;
	}
	public void update(GameManager game) {	}
	public void render(ScreenManager screen) {
		if(isGot) {
			screen.renderFixedString(Color.BLACK, "Achievement Got: " + name, Gdx.graphics.getWidth() / 2f - 100f, Gdx.graphics.getHeight() - 50f);
			
			if(renderTime >= 100f)
				finishedRender = true;
			else renderTime += Gdx.graphics.getDeltaTime();
		}
	}
	
}
