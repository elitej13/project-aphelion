package com.ephemerality.aphelion.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ephemerality.aphelion.framework.Master;

public class GameRuntime {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Aphelion";
		config.backgroundFPS = 0;
		config.width = 1000;
		config.height = 800;
		new LwjglApplication(new Master(), config);
	}
}
