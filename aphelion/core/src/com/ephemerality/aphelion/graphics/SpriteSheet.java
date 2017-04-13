package com.ephemerality.aphelion.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ephemerality.aphelion.spawn.entities.tiles.Tile;

public class SpriteSheet {

	public static Texture tilesheet = new Texture(Gdx.files.local("textures/tilesheet.png"));
	public static Texture entitysheet = new Texture(Gdx.files.local("textures/entitysheet.png"));
	public static Texture itemsheet = new Texture(Gdx.files.local("textures/itemsheet.png"));
	
//	Character Sprites	//
	public static TextureRegion default_head_idle = new TextureRegion(SpriteSheet.entitysheet, 	0, 0, 	32, 32);
	public static TextureRegion default_torso_idle = new TextureRegion(SpriteSheet.entitysheet, 	0, 32, 	32, 32);
	public static TextureRegion default_arms_idle = new TextureRegion(SpriteSheet.entitysheet, 	0, 64, 	32, 32);
	public static TextureRegion default_legs_idle = new TextureRegion(SpriteSheet.entitysheet, 	0, 96, 	32, 32);
	public static TextureRegion default_hat_idle = new TextureRegion(SpriteSheet.entitysheet, 	0, 128, 32, 32);
	
// 	Tile Sprites	//
	public static TextureRegion default_grass_0 = new TextureRegion(SpriteSheet.tilesheet, 	0, 0, 64, 64);
	public static TextureRegion default_dirt_0 = new TextureRegion(SpriteSheet.tilesheet, 	0, 64, 64, 64);
	public static TextureRegion default_brick_0 = new TextureRegion(SpriteSheet.tilesheet, 	0, 128, 64, 64);
	public static TextureRegion default_wood_0 = new TextureRegion(SpriteSheet.tilesheet, 	0, 192, 64, 64);
	
//	Item Sprites	//
	public static TextureRegion wood_box_0 = new TextureRegion(SpriteSheet.itemsheet, 	0, 0, 64, 64);
	
	
//	DEBUGGING Sprites	//
	public static TextureRegion rectangle = new TextureRegion(SpriteSheet.tilesheet, 576, 0, 64, 64);
	public static TextureRegion default_void_0 = new TextureRegion(new Texture(new Pixmap(64, 64, Format.RGB565)));
	
	
	public static Image fetchImageFromTileID(short tileID) {
		Image image = null;
		if(tileID == Tile.VOID_ID) {
			image = new Image(default_void_0);
			image.setUserObject(new Short(Tile.VOID_ID));
		}else if(tileID == Tile.GRASS_ID) {
			image = new Image(default_grass_0);
			image.setUserObject(new Short(Tile.GRASS_ID));
		}else if(tileID == Tile.DIRT_ID) {
			image = new Image(default_dirt_0);
			image.setUserObject(new Short(Tile.DIRT_ID));
		}else if(tileID == Tile.BRICK_ID) {
			image = new Image(default_brick_0);
			image.setUserObject(new Short(Tile.BRICK_ID));
		}else if(tileID == Tile.WOOD_ID) {
			image = new Image(default_wood_0);
			image.setUserObject(new Short(Tile.WOOD_ID));
		}
		return image;
	}
	
}
