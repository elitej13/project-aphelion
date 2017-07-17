package com.ephemerality.aphelion.ui.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class Button extends Element{
	
	public Texture image, highlightedImage;
	public float vTextInset, hTextInset;
	
	public Button(float x, float y, float w, float h, String title, Color color, Color 	highLightedColor) {
		super(x, y, w, h, title);
		Pixmap pix = new Pixmap(1, 1, Format.RGBA8888);
		pix.setColor(color);
		pix.fillRectangle(0, 0, 1, 1);
		image = new Texture(pix);
		pix.setColor(highLightedColor);
		pix.fillRectangle(0, 0, 1, 1);
		highlightedImage = new Texture(pix);
		pix.dispose();
		
		GlyphLayout gl = new GlyphLayout();
		gl.setText(ScreenManager.font, title);
		hTextInset = (w - gl.width) / 2f;
		vTextInset = (h - gl.height) / 2f + gl.height;
	}
	
	public void render(ScreenManager screen) {
		if(!active)	screen.renderFixed(image, body);
		else screen.renderFixed(highlightedImage, body);
		screen.renderFixedString(title, body.x + hTextInset, body.y + vTextInset);
	}
}
