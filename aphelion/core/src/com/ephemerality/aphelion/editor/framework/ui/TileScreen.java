package com.ephemerality.aphelion.editor.framework.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisWindow;

public class TileScreen extends VisWindow{
	
	public TileScreen(MapManager map, InputListener il) {
		super("Viewport");

		TableUtils.setSpacingDefaults(this);
		columnDefaults(0).left();
		
		
		int w = (int) Math.min(map.getMapSize().x, Gdx.graphics.getWidth() * 0.75f);
		int h = (int) Math.min(map.getMapSize().y, Gdx.graphics.getHeight() * 0.9f) + 25;
		
		
//		Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGB888);
//		pix.setColor(0, 0, 0, 0);
		
		
		setSize(w, h);
		setPosition(50, 0);
//		setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pix))));
		addListener(il);
		
		
		
//		pix.dispose();
		
	}

}
