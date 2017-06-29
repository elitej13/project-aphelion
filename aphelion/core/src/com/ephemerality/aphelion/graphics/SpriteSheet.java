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
import com.ephemerality.aphelion.spawn.entities.nob.items.Item;
import com.ephemerality.aphelion.spawn.entities.tiles.Tile;

public class SpriteSheet {

	
	
	public static Texture pixel;
// 	Tile Sprites	//
	public static TextureRegion default_grass_0;
	public static TextureRegion default_dirt_0;
	public static TextureRegion default_brick_0;
	public static TextureRegion default_wood_0;
	
//	Item Sprites	//
	public static TextureRegion wood_box_0;
	public static TextureRegion sword;
	
	public static TextureRegion default_void_0;
	
//	UI Sprites	//
	public static Texture minimap_mask;
	
	//For the game
	public static void init(AssetManager assets) {
		Texture tilesheet = assets.get(LoadManager.TILE_SHEET, Texture.class);
		Texture itemsheet = assets.get(LoadManager.ITEM_SHEET, Texture.class);
		minimap_mask = assets.get(LoadManager.UI_SHEET, Texture.class);
		minimap_mask.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		init(tilesheet, itemsheet);
//		tilesheet.dispose();
//		entitysheet.dispose();
//		itemsheet.dispose();
	}
	//For the editor
	public static void init() {
		Texture tilesheet = new Texture(Gdx.files.internal(LoadManager.TILE_SHEET));
		Texture itemsheet = new Texture (Gdx.files.internal(LoadManager.ITEM_SHEET));
		init(tilesheet, itemsheet);
	}
	
	public static void init(Texture tilesheet, Texture itemsheet) {
		Pixmap map = new Pixmap(1, 1, Format.RGBA8888);
		map.setColor(Color.BLACK);
		map.fillRectangle(1, 1, 1, 1);
		pixel = new Texture(map);
		
//	 	Tile Sprites	//
		default_grass_0 = new TextureRegion(tilesheet, 	0, 0, 	64, 64);
		default_dirt_0 = new TextureRegion(tilesheet, 	0, 64, 	64, 64);
		default_brick_0 = new TextureRegion(tilesheet, 	0, 128, 64, 64);
		default_wood_0 = new TextureRegion(tilesheet, 	0, 192, 64, 64);
		
//		Item Sprites	//
		wood_box_0 = new TextureRegion(itemsheet, 	0, 0, 	32, 32);
		sword = new TextureRegion(itemsheet, 		0, 32, 	32, 32);
		
		
//		DEBUGGING Sprites	//
		default_void_0 = new TextureRegion(new Texture(new Pixmap(64, 64, Format.RGB565)));
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
		}
		
		else if(ID == Item.CHEST) {
			return wood_box_0;
		}else if(ID == Item.SWORD) {
			return sword;
		}
		return default_void_0;
	}
	public static TextureRegion fetchPixelFromEntityID(short ID, int w, int h) {
		return new TextureRegion(fetchTextureRegionFromEntityID(ID), 0, 0, w - 1, h - 1);
	}
	public static Image fetchImageFromEntityID(short ID) {
		Image image = null;
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
		}
		return image;
	}


}
