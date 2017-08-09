package com.ephemerality.aphelion.spawn;

import com.ephemerality.aphelion.spawn.entities.Entity;

public interface Interactable {
	
	/**
	 * @param e The entity that is doing the interaction.
	 */
	public void interact(Entity e);
	
}
