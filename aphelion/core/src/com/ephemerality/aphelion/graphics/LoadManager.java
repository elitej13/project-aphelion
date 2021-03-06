package com.ephemerality.aphelion.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.ephemerality.aphelion.framework.Master;
import com.ephemerality.aphelion.input.DollInfo;
import com.ephemerality.aphelion.input.SCMLLoader;
import com.ephemerality.aphelion.input.SCMLLoader.DollParameter;
import com.ephemerality.aphelion.util.State;
import com.ephemerality.aphelion.util.debug.Debug;

public class LoadManager {
	
	AssetManager assets;
	private final int TOTAL_ASSETS;
	private int loadedAssets;
	private final ArrayList<Texture> loadFrames;
	private float timer;
	private int currentFrame;
	private float textX, textY;
	private float titleX, titleY;
	private float animX, animY;
	private float animScale;

	private BitmapFont font;
	private int FONT_SIZE = 40;
	
	
	public static final String MENU_FRAME = "textures/ui/menu/menuframe.png";
	
	
	public static final String ITEM_SHEET = "textures/itemsheet.png";
	public static final String TILE_SHEET = "textures/tilesheet.png";
	public static final String ENVIRONMENT_SHEET = "textures/environmentsheet.png";
	public static final String UI_SHEET = "textures/ui/mask.png";
	

	public static final String MONSTER_SCML = "characters/monster/basic_002.scml";
	public static final String BRAWLER_SCML = "characters/brawler/brawler.scml";
	public static final String IMP_SCML = "characters/imp/imp.scml";
	public static final String MAGE_SCML = "characters/mage/mage.scml";
	public static final String ORC_SCML = "characters/orc/orc.scml";

	
	
	
	public LoadManager(ScreenManager screen) {
		loadFrames = new ArrayList<>();
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
		item = "NecroHero";
		glyphLayout.setText(font,item);
		w = glyphLayout.width;
		titleX = Gdx.graphics.getWidth() / 2 - (w) / 2;
		titleY = animY + 10;
		
		assets = new AssetManager();
		assets.setLoader(DollInfo.class, new SCMLLoader(new InternalFileHandleResolver()));
		Texture.setAssetManager(assets);
		load(screen);
		TOTAL_ASSETS = assets.getQueuedAssets();
	}
	
	
	
	
	public void load(ScreenManager screen) {
		//UI Assets
		
		//Menu
		assets.load(MENU_FRAME, Texture.class);
		
		//Game Assets
		
		//Character SCML Data
		DollParameter param = new DollParameter();
		param.batch = screen.sb;

		assets.load(MONSTER_SCML, DollInfo.class, param);
//		assets.load(BRAWLER_SCML, Doll.class, param);
//		assets.load(IMP_SCML, Doll.class, param);
//		assets.load(MAGE_SCML, Doll.class, param);
//		assets.load(ORC_SCML, Doll.class, param);
		
		//Spritesheets
		assets.load(ENVIRONMENT_SHEET, Texture.class);
		assets.load(ITEM_SHEET, Texture.class);
		assets.load(TILE_SHEET, Texture.class);
		assets.load(UI_SHEET, Texture.class);
	}
	
	
	
	
	
	public Texture getTexture(String name) {
		return assets.get(name, Texture.class);
	}
	public DollInfo getDollInfo(String name) {
		return assets.get(name, DollInfo.class);
	}
	
	public void update() {
		assets.update();
		Debug.pushToConsole("Loading assets " + loadedAssets + "/" + TOTAL_ASSETS, false);
		loadedAssets = assets.getLoadedAssets();
		timer += Gdx.graphics.getDeltaTime();
		if(timer >= 3/100) {
			timer = 0;
			currentFrame++;
			if(currentFrame > 30)
				currentFrame = 0;			
		}
		if(assets.getLoadedAssets() == TOTAL_ASSETS && assets.getProgress() == 1f) {
			SpriteSheet.init(assets);
			Debug.pushToConsole("Done loading assets", false);
			Master.setState(State.MAIN_MENU);
		}
	}
	
	
	public void render(ScreenManager screen) {
		int percent = (int)(assets.getProgress() * 100);
		screen.renderFixed(loadFrames.get(currentFrame), animX, animY, animScale);
		screen.renderFixedString(font, "Loading assets " + percent + "%", textX, textY);
		screen.renderFixedString(font, "NecroHero", titleX, titleY);
	}
	
	public void dispose() {
		assets.dispose();
	}
}
