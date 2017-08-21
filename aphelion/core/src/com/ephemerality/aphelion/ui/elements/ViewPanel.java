package com.ephemerality.aphelion.ui.elements;

import com.badlogic.gdx.graphics.Color;

public class ViewPanel extends EphPanel {

	public ViewPanel(float x, float y, float w, float h, String title, Color color, float rowHeight) {
		super(x, y, w, h, title, color, false, rowHeight);
		initChildren(x, y, w, h);
	}

	private void initChildren(float x, float y, float w, float h) {
//		Color utilBarDefault = new Color(0.212f, 0.192f, 0.216f, 1f);
//		Color utilBarHighlight = new Color(0.114f, 0.102f, 0.118f, 1f);
//		Color windowPaneDefault = new Color(0.3f, 0.28f, 0.3f, 1f);
//		float xc = x;
//		float yc = y + h - rowHeight - rowHeight;
//		int rows = 0;
		
//		
//		children.put("New", new EphButton(xc, yc - (rowHeight * rows++), w, rowHeight, "New", utilBarDefault, utilBarHighlight) {
//			@Override
//			public void behavior() {
//				
//			}
//		});
//		children.put("Open", new EphButton(xc, yc - (rowHeight * rows++), w, rowHeight, "Open", utilBarDefault, utilBarHighlight) {
//			@Override
//			public void behavior() {
//				
//			}
//		});
//		children.put("Save As", new EphButton(xc, yc - (rowHeight * rows++), w, rowHeight, "Save As", utilBarDefault, utilBarHighlight) {
//			@Override
//			public void behavior() {
//				
//			}
//		});
//		children.put("Save", new EphButton(xc, yc - (rowHeight * rows++), w, rowHeight, "Save", utilBarDefault, utilBarHighlight) {
//			@Override
//			public void behavior() {
//				
//			}
//		});
		totalScroll = 0;
	}
}