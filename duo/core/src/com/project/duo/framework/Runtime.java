package com.project.duo.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.duo.spawn.Puppet;
import com.project.duo.util.Direction;
public class Runtime extends ApplicationAdapter {
	
	private Game game;
	private MainMenu menu;
	private SpriteBatch sb;
	private Puppet puppet;
	@Override
	public void create () {
		menu = new MainMenu();
		sb = new SpriteBatch();
		puppet = new Puppet();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 0, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.enableBlending();
		
		sb.begin();
		puppet.render(sb);
		sb.end();
		
		
		if(Gdx.input.isButtonPressed(Buttons.FORWARD)) {
			puppet.move(Direction.NORTH);
			System.out.println("Going north");
		}
		if(Gdx.input.isButtonPressed(Buttons.RIGHT))
			puppet.move(Direction.EAST);
		if(Gdx.input.isButtonPressed(Buttons.BACK))
			puppet.move(Direction.SOUTH);
		if(Gdx.input.isButtonPressed(Buttons.LEFT))
			puppet.move(Direction.WEST);
	}
	
	@Override
	public void dispose () {
	}
}
