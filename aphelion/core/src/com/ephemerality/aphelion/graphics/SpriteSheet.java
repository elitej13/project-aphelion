package com.ephemerality.aphelion.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ephemerality.aphelion.spawn.entities.nob.Environment;
import com.ephemerality.aphelion.spawn.entities.nob.Item;
import com.ephemerality.aphelion.spawn.entities.nob.Tile;

public class SpriteSheet {

	
	public final static int iconWidthAndHeight = 2;
	
	public static Texture pixel;
//----------------------------------Tiles---------------------------------//
	public static TextureRegion default_grass_0, default_grass_0_ico;
	public static TextureRegion default_dirt_0, default_dirt_0_ico;
	public static TextureRegion default_brick_0, default_brick_0_ico;
	public static TextureRegion default_wood_0, default_wood_0_ico;
	public static TextureRegion default_sand_0, default_sand_0_ico;
	public static TextureRegion default_water_0, default_water_0_ico;
	public static TextureRegion default_gravel_0, default_gravel_0_ico;
	
//-------------------------------Environment-------------------------------//
	public static TextureRegion default_house, default_house_ico;
	public static TextureRegion default_fence;
	public static TextureRegion default_gate;
	public static TextureRegion default_tree;
	public static TextureRegion default_bridge;
	
//----------------------------------Items----------------------------------//
	public static TextureRegion wood_box_0;
	public static TextureRegion sword;
	
	public static TextureRegion default_void_0, default_void_0_ico;
	
//------------------------------------UI------------------------------------//
	public static Texture minimap_mask;
	
	//For the game
	public static void init(AssetManager assets) {
		Texture tilesheet = assets.get(LoadManager.TILE_SHEET, Texture.class);
		Texture environmentsheet = assets.get(LoadManager.ENVIRONMENT_SHEET, Texture.class);
		Texture itemsheet = assets.get(LoadManager.ITEM_SHEET, Texture.class);
		Texture uisheet = assets.get(LoadManager.UI_SHEET, Texture.class);
		initTiles(tilesheet);
		initEnvironment(environmentsheet);
		initItems(itemsheet);
		initUI(uisheet);
	}
	//For the editor
	public static void init() {
		Texture tilesheet = new Texture(Gdx.files.internal(LoadManager.TILE_SHEET));
		Texture environmentsheet = new Texture (Gdx.files.internal(LoadManager.ENVIRONMENT_SHEET));
		Texture itemsheet = new Texture (Gdx.files.internal(LoadManager.ITEM_SHEET));
		initTiles(tilesheet);
		initEnvironment(environmentsheet);
		initItems(itemsheet);
	}
	
	public static void initTiles(Texture tilesheet) {
		Pixmap map = new Pixmap(1, 1, Format.RGBA8888);
		map.setColor(Color.BLACK);
		map.fillRectangle(1, 1, 1, 1);
		pixel = new Texture(map);
		
		default_grass_0 = new TextureRegion(tilesheet, 0, 0, 64, 64);
		default_grass_0_ico = new TextureRegion(default_grass_0, 0, 0, iconWidthAndHeight, iconWidthAndHeight);
		
		default_dirt_0 = new TextureRegion(tilesheet, 0, 64, 64, 64);
		default_dirt_0_ico = new TextureRegion(default_dirt_0, 0, 0, iconWidthAndHeight, iconWidthAndHeight);
		
		default_brick_0 = new TextureRegion(tilesheet, 0, 128, 64, 64);
		default_brick_0_ico = new TextureRegion(default_brick_0, 0, 0, iconWidthAndHeight, iconWidthAndHeight);
		
		default_wood_0 = new TextureRegion(tilesheet, 0, 192, 64, 64);
		default_wood_0_ico = new TextureRegion(default_wood_0, 0, 0, iconWidthAndHeight, iconWidthAndHeight); 
		
		default_sand_0 = new TextureRegion(tilesheet, 0, 256, 64, 64);
		default_sand_0_ico = new TextureRegion(default_sand_0, 0, 0, iconWidthAndHeight, iconWidthAndHeight); 
		
		default_water_0 = new TextureRegion(tilesheet, 0, 320, 64, 64);
		default_water_0_ico = new TextureRegion(default_water_0, 0, 0, iconWidthAndHeight, iconWidthAndHeight); 
		
		default_gravel_0 = new TextureRegion(tilesheet, 0, 384, 64, 64);
		default_gravel_0_ico = new TextureRegion(default_gravel_0, 0, 0, iconWidthAndHeight, iconWidthAndHeight); 
		
		
		default_void_0 = new TextureRegion(new Texture(new Pixmap(64, 64, Format.RGB565)));
		default_void_0_ico = new TextureRegion(default_void_0, 0, 0, iconWidthAndHeight, iconWidthAndHeight); 
	}
	public static void initEnvironment(Texture environmentsheet) {
		default_house = new TextureRegion(environmentsheet, 0, 0, 192, 192);
		default_fence = new TextureRegion(environmentsheet, 256, 0, 64, 64);
		default_gate = new TextureRegion(environmentsheet, 320, 0, 64, 64);
		default_tree = new TextureRegion(environmentsheet, 384, 0, 128, 128);
		default_bridge = new TextureRegion(environmentsheet, 192, 64, 192, 64);
	}
	public static void initItems(Texture itemsheet) {
		wood_box_0 = new TextureRegion(itemsheet, 	0, 0, 	32, 32);
		sword = new TextureRegion(itemsheet, 		0, 32, 	32, 32);
		
	}
	public static void initUI(Texture uisheet) {		
		minimap_mask = uisheet;
		minimap_mask.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
	}
	
	public static TextureRegion fetchTextureRegionFromEntityID(short ID) {
		if(ID == Tile.VOID_ID) {
			return default_void_0;
		}else if(ID == Tile.GRASS_ID) {
			return default_grass_0;
		}else if(ID == Tile.DIRT_ID) {
			return default_dirt_0;
		}else if(ID == Tile.BRICK_ID) {
			return default_brick_0;
		}else if(ID == Tile.WOOD_ID) {
			return default_wood_0;
		}else if(ID == Tile.SAND_ID) {
			return default_sand_0;
		}else if(ID == Tile.WATER_ID) {
			return default_water_0;
		}else if(ID == Tile.GRAVEL_ID) {
			return default_gravel_0;
		}
		
		else if(ID == Item.CHEST) {
			return wood_box_0;
		}else if(ID == Item.SWORD) {
			return sword;
		}
		return default_void_0;
	}
	public static TextureRegion fetchIconFromEntityID(short ID) {
		if(ID == Tile.VOID_ID) {
			return default_void_0_ico;
		}else if(ID == Tile.GRASS_ID) {
			return default_grass_0_ico;
		}else if(ID == Tile.DIRT_ID) {
			return default_dirt_0_ico;
		}else if(ID == Tile.BRICK_ID) {
			return default_brick_0_ico;
		}else if(ID == Tile.WOOD_ID) {
			return default_wood_0_ico;
		}else if(ID == Tile.SAND_ID) {
			return default_sand_0_ico;
		}else if(ID == Tile.WATER_ID) {
			return default_water_0_ico;
		}else if(ID == Tile.GRAVEL_ID) {
			return default_gravel_0_ico;
		}
		return default_void_0_ico;
	}
	public static Image fetchImageFromEntityID(short ID) {
		Image image = null;
//---------------------TILES----------------------------------//
		if(ID == Tile.VOID_ID) {
			image = new Image(default_void_0);
			image.setUserObject(new Short(Tile.VOID_ID));
		}else if(ID == Tile.GRASS_ID) {
			image = new Image(default_grass_0);
			image.setUserObject(new Short(Tile.GRASS_ID));
		}else if(ID == Tile.DIRT_ID) {
			image = new Image(default_dirt_0);
			image.setUserObject(new Short(Tile.DIRT_ID));
		}else if(ID == Tile.BRICK_ID) {
			image = new Image(default_brick_0);
			image.setUserObject(new Short(Tile.BRICK_ID));
		}else if(ID == Tile.WOOD_ID) {
			image = new Image(default_wood_0);
			image.setUserObject(new Short(Tile.WOOD_ID));
		}else if(ID == Tile.SAND_ID) {
			image = new Image(default_sand_0);
			image.setUserObject(new Short(Tile.SAND_ID));
		}else if(ID == Tile.WATER_ID) {
			image = new Image(default_water_0);
			image.setUserObject(new Short(Tile.WATER_ID));
		}else if(ID == Tile.GRAVEL_ID) {
			image = new Image(default_gravel_0);
			image.setUserObject(new Short(Tile.GRAVEL_ID));
		}
		
//----------------------ENVIRONMENT-----------------------------//
		else if(ID == Environment.House.ID) {
			image = new Image(default_house);
			image.setUserObject(new Short(Environment.House.ID));
		}else if(ID == Environment.Fence.ID) {
			image = new Image(default_fence);
			image.setUserObject(new Short(Environment.Fence.ID));
		}else if(ID == Environment.Gate.ID) {
			image = new Image(default_gate);
			image.setUserObject(new Short(Environment.Gate.ID));
		}else if(ID == Environment.Bridge.ID) {
			image = new Image(default_bridge);
			image.setUserObject(new Short(Environment.Bridge.ID));
		}else if(ID == Environment.Tree.ID) {
			image = new Image(default_tree);
			image.setUserObject(new Short(Environment.Tree.ID));
		}
		return image;
	}


}
