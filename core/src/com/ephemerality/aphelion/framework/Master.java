package com.ephemerality.aphelion.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.Drawer;
import com.brashmonkey.spriter.Player;
import com.brashmonkey.spriter.SCMLReader;
import com.brashmonkey.spriter.LibGdx.LibGdxDrawer;
import com.brashmonkey.spriter.LibGdx.LibGdxLoader;
import com.ephemerality.aphelion.input.InputManager;


public class Master extends ApplicationAdapter {
	
	//-1 Exiting, 0 Main menu, 1 Game
	
	static int state;
	GameManager game;
	MenuManager main;
	InputManager input;
	SpriteBatch sb;
	
	
	OrthographicCamera cam;
	ShapeRenderer renderer;
	FPSLogger fpslog;
	Drawer<Sprite> drawer;
	Player player;
	LibGdxLoader loader;
	
	
	
	@Override
	public void create () {
		super.create();
		
		input = new InputManager();
		game = new GameManager();
		main = new MenuManager();
		sb = new SpriteBatch();
		
		
		

		cam = new OrthographicCamera();
		cam.zoom = 1f;
		renderer = new ShapeRenderer();
		FileHandle handle = Gdx.files.internal("monster/basic_002.scml");
		Data data = new SCMLReader(handle.read()).getData();

		loader = new LibGdxLoader(data);
		loader.load(handle.file());

		drawer = new LibGdxDrawer(loader, sb, renderer);

		player = new Player(data.getEntity(0));
		player.setAnimation(1);
		fpslog = new FPSLogger();
	}

	
	public void update() {		
		input.update();
		if(Master.state == -1) {
			Gdx.app.exit();
		}else if(Master.state == 0) {
			main.update();
		}else if(Master.state == 1) {
			game.update();
		}
	}
	
	public static void setState(int state){
		if(state >= -1 && state <= 1) 
			Master.state = state;
		else 
			System.out.println("Error changing state.");
	}
	
	
	
	
//	Start of Experimental	//
	public void resize(int width, int height) {
		super.resize(width, height);
		
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0, 0, 0f);
		cam.update();
		renderer.setProjectionMatrix(cam.combined);
		sb.setProjectionMatrix(cam.combined);
	}
//	End of Experimental	//
	
	
	
	@Override
	public void render () {
//	TODO : Have update on a fixed timer		
//		update();

		player.update();
//		Gdx.gl.glClearColor(0, 0, 0, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		sb.enableBlending();

		fpslog.log();
		sb.begin();
//		if(state == 0) {
//			main.render(sb);
//		}else if(state == 1) {
//			game.render(sb);
//		}
		
		

		drawer.draw(player);
		sb.end();
		
		
	}
	
	@Override
	public void dispose () {
		
//		game.dispose(); //do stuff in place of thiss
		main.dispose();
		sb.dispose();
	}
}
