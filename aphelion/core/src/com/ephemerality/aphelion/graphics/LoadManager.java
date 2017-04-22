package com.ephemerality.aphelion.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.ephemerality.aphelion.framework.Master;
import com.ephemerality.aphelion.util.debug.Debug;

public class LoadManager {
	
	AssetManager assets;
	private final int TOTAL_ASSETS;
	private int loadedAssets;
	private final ArrayList<Texture> loadFrames;
	private float timer;
	private int currentFrame;
	private float textX, textY;
	private float animX, animY;
	private float animScale;

	private BitmapFont font;
	private int FONT_SIZE = 40;
	
	public LoadManager() {
		loadFrames = new ArrayList<Texture>();
		int frames = 31;
		for(int i = 0; i < frames; i++) 
			loadFrames.add(new Texture(Gdx.files.internal("load/frame_" + i + "_delay-0.03s.png")));
		currentFrame = 0;
		
		animScale = 0.5f;
		animX = Gdx.graphics.getWidth() / 2 - loadFrames.get(0).getWidth() * animScale / 2;
		animY = Gdx.graphics.getHeight() / 2 - loadFrames.get(0).getHeight() * animScale / 2;
		
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/style-7_thin-pixel-7/thin_pixel-7.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = FONT_SIZE;
		parameter.color = Color.WHITE;
		font= generator.generateFont(parameter);
		generator.dispose();
		
		
		
		GlyphLayout glyphLayout = new GlyphLayout();
		String item = "Loading assets 0/12";
		glyphLayout.setText(font,item);
		float w = glyphLayout.width;
		textX = Gdx.graphics.getWidth() / 2 - (w) / 2;
		textY = animY - 10;
		
		
		assets = new AssetManager();
		Texture.setAssetManager(assets);
		load();
		TOTAL_ASSETS = assets.getQueuedAssets();
	}
	
	public void load() {
		assets.load("textures/tilesheet.png", Texture.class);
		assets.load("textures/entitysheet.png", Texture.class);
		assets.load("textures/itemsheet.png", Texture.class);
		assets.load(null);
	}
	
	
	
	public void update() {
		assets.update();
		Debug.pushToConsole("Loading assets " + loadedAssets + "/" + TOTAL_ASSETS);
		loadedAssets = assets.getLoadedAssets();
		timer += Gdx.graphics.getDeltaTime();
		if(timer >= 3/100) {
			timer = 0;
			currentFrame++;
			if(currentFrame > 30)
				currentFrame = 0;			
		}
		if(assets.getLoadedAssets() == TOTAL_ASSETS) {
			Debug.pushToConsole("Done loading assets");
			Master.setState(1);
		}
	}
	
	
	public void render(ScreenManager screen) {
		int percent = (int)(assets.getProgress() * 100);
		screen.renderFixed(loadFrames.get(currentFrame), animX, animY, animScale);
		font.draw(screen.getSpriteBatch(), "Loading assets " + percent + "%", textX, textY);
	}
	
	public void dispose() {
		assets.dispose();
	}
}
