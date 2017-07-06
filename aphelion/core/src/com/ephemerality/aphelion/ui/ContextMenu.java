package com.ephemerality.aphelion.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.player.Player;

public class ContextMenu {
	
	public final static int FONT_SIZE = 14;
	
	Texture bkgd;
	Vector2 resolution;
	Player player;
	BitmapFont stats;
	float fontheight, fontwidth;
	
	public ContextMenu(Player player) {
		this.player = player;
		resolution = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Pixmap backDrop = new Pixmap(1, 1, Pixmap.Format.RGB888);
		backDrop.setColor(0.5f, 0.5f, 0.5f, 0.5f);
		backDrop.fillRectangle(0, 0, 1, 1);
		bkgd = new Texture(backDrop);
		

		//Font Generations
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/inconsolata/Inconsolata-Bold.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		
		parameter.size = FONT_SIZE;
		parameter.color = Color.WHITE;
		stats = generator.generateFont(parameter);
		
		
		//Font Metrics
		GlyphLayout glyphLayout = new GlyphLayout();
		String item = "D";
		
		glyphLayout.setText(stats,item);
		fontheight = glyphLayout.height + 2f;
		fontwidth = glyphLayout.width;
		
		
	}
	
	
	
	
	
	public void update() {
		
	}
	
	
	public void render(ScreenManager screen) {
//		int x = (int) (resolution.x * 0.125f);
//		int y = (int) (resolution.y * 0.125f);
		int x = 0;
		int y = 0;
//		int w = (int) (resolution.x * 0.75f);
//		int h = (int) (resolution.y * 0.75f);
		int w = (int) resolution.x;
		int h = (int) resolution.y;
		screen.getSpriteBatch().setColor(1f, 1f, 1f, 0.75f);
		screen.renderFixed(bkgd, x, y, w, h);
		screen.getSpriteBatch().setColor(1f, 1f, 1f, 1f);
		player.inventory.render(screen);
		x = (int) (resolution.x * 0.75f);
		y = (int) (resolution.y * 0.05f);
		w = (int) (resolution.x * 0.2f);
		h = (int) (resolution.y * 0.9f);
//		screen.renderFixed(bkgd, x, y, w, h);
		x = x + 5;
		y = y + h - 5;
		int offset = 1;
		int padding = 5;
		screen.renderFixedString(stats, "Health: " + player.equip.getFormattedHealth(), x, y - (fontheight + padding) * offset++);
		screen.renderFixedString(stats, "Level: " + player.equip.getLevel(), x, y - (fontheight + padding) * offset++);
		screen.renderFixedString(stats, "MD: " + player.equip.getMagicalDamage(), x, y - (fontheight + padding) * offset++);
		screen.renderFixedString(stats, "PD: " + player.equip.getPhysicalDamage(), x, y - (fontheight + padding) * offset++);
		screen.renderFixedString(stats, "MR: " + player.equip.getMagicalResistance(), x, y - (fontheight + padding) * offset++);
		screen.renderFixedString(stats, "PR: " + player.equip.getPhysicalResistance(), x, y - (fontheight + padding) * offset++);
		screen.renderFixedString(stats, "Stun: " + player.equip.getStun(), x, y - (fontheight + padding) * offset++);
//		stats.draw(screen.getSpriteBatch(), "Health: " + player.equip.getFormattedHealth(), x, y, w * 0.5f, 0, false);
	}
}