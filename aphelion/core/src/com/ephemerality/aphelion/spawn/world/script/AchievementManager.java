package com.ephemerality.aphelion.spawn.world.script;

import java.util.HashSet;

import com.ephemerality.aphelion.framework.GameManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.nob.Item;

public class AchievementManager {

	GameManager game;
	HashSet<Achievement> achievements;
	HashSet<Achievement> removed;
	
	public AchievementManager(GameManager game) {
		this.game = game;
		
		achievements = new HashSet<>();
		removed = new HashSet<>();
		achievements.add(new Achievement("Getting a Sword") {
			@Override
			public void update(GameManager game) {
				if(game.ent.player.inventory.checkForItem(Item.SWORD)) {				
					isGot = true;
				}
			}
		});
		achievements.add(new Achievement("") {
			@Override
			public void update(GameManager game) {
				
			}
		});
		
	}
	
	public void update() {
		for(Achievement a : achievements) {
			a.update(game);
			if(a.finishedRender)
				removed.add(a);
		}
		achievements.removeAll(removed);
		removed.clear();
	}

	public void render(ScreenManager screen) {
		for(Achievement a : achievements)
			a.render(screen);
	}
}
