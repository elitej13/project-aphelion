package com.ephemerality.aphelion.ui.elements;

import com.badlogic.gdx.graphics.Color;
import com.ephemerality.aphelion.customizer.GUIManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.util.Util;

public class InfoPanel extends EphPanel {

	public InfoPanel(float x, float y, float w, float h, String title, Color color, float rowHeight) {
		super(x, y, w, h, title, color, false, rowHeight);
	}

	
	
	
	
	public void render(ScreenManager screen) {
		super.render(screen);
		short ID = GUIManager.active;
		float x = body.x + 120f;
		screen.renderFixed(SpriteSheet.fetchTextureRegionFromEntityID(ID), body.x + 120f, body.y + 4f, 64f, 64f);
		x += 64f + 8f;
		if(Util.isTile(ID)) {
			screen.renderFixedString("Type: Tile", x , body.y + 48f);			
		}else if(Util.isEnv(ID)) {
			screen.renderFixedString("Type: ENV", x , body.y + 48f);			
		}else {
			screen.renderFixedString("Type: ", x , body.y + 48f);			
		}
		
		x += 120f;
		
		if(ID < 0) {
			screen.renderFixedString("Collidable: TRUE", x , body.y + 48f);			
		}else if(ID > 0) {
			screen.renderFixedString("Collidable: FALSE", x , body.y + 48f);			
		}else {
			screen.renderFixedString("Collidable: ", x , body.y + 48f);			
		}
		
		x += 150f;
		
		screen.renderFixedString("Zoom: " + screen.oc.zoom, x, body.y + 48f);
		
	}
}
