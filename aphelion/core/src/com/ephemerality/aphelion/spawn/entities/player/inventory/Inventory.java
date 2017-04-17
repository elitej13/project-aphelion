package com.ephemerality.aphelion.spawn.entities.player.inventory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.Entity;
import com.ephemerality.aphelion.spawn.entities.nob.Chest;

public class Inventory {
	
	HashMap<Short, Item> items;
	int maxPerRow = 4;
	int filledSlots;
	int maxSlots;
	
	public Inventory() {
		this(16);
		addEntity(new Chest(0, 0));
		addEntity(new Chest(0, 0));
	}
	
	public Inventory(int maxSlots) {
		this.maxSlots = maxSlots;	
		items = new HashMap<>();
	}
	
	
	
	public void addEntity(Entity e) {
		Item item = items.get(e.getWrappedID());
		if(item != null) {
			item.increcement();
		}else {
			int y = filledSlots / maxSlots;
			int x = filledSlots - y * maxSlots;
			item = new Item(e.getID(), 1, x, y);
			filledSlots++;
		}
				
	}
	
	public void resize() {
//		TODO: this!!!
//		Iterator iter = items.entrySet().iterator();
//		while(iter.hasNext()) {
//			Item item = (Item) iter.next();
//			if(item.x > maxPerRow) {
//				
//			}
//			
//		}
	}
	
	public void render(ScreenManager screen) {
		float xOffset = 0;
		float yOffset = 0;
		float padding = 10;
		float scale = 0;
		Iterator<Entry<Short, Item>> iter = items.entrySet().iterator();
		while(iter.hasNext()) {
			Item item = iter.next().getValue();
			item.render(screen, xOffset, yOffset, padding, scale);
		}
	}
	
}
