package com.ephemerality.aphelion.spawn.entities.player.inventory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.Entity;
import com.ephemerality.aphelion.spawn.entities.nob.Chest;

public class Inventory {
	
	public Set<Entity> queue;

	HashMap<Short, InventoryItem> items;
	int maxPerRow = 4;
	int filledSlots;
	int maxSlots;
	float xOffset;
	float yOffset;
	
	public Inventory() {
		this(16);
	}
	
	public Inventory(int maxSlots) {
		this.maxSlots = maxSlots;	
		items = new HashMap<>();
		queue = new HashSet<>();
		xOffset = Gdx.graphics.getWidth() * 0.125f;
		yOffset = Gdx.graphics.getHeight() * 0.875f;
	}
	
	public void update() {
		for(Entity e : queue) {
			addEntity(e);
		}
		queue.clear();
	}
	
	public void addEntity(Entity e) {
		InventoryItem item = items.get(e.getWrappedID());
		if(item != null) {
			item.increment();
		}else {
			int y = filledSlots / maxSlots;
			int x = filledSlots - y * maxSlots;
			item = new InventoryItem(e.getIcon(), e.getID(), x, y);
			items.put(e.getWrappedID(), item);
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
		float padding = 10f;
		float scale = 32f;
		Iterator<Entry<Short, InventoryItem>> iter = items.entrySet().iterator();
		while(iter.hasNext()) {
			InventoryItem item = iter.next().getValue();
			item.render(screen, xOffset, yOffset, padding, scale);
		}
	}
	
}
