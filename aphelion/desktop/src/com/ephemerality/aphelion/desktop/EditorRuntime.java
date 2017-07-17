package com.ephemerality.aphelion.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ephemerality.aphelion.customizer.Editor;

public class EditorRuntime {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Aphelion Editor" + " v" + Editor.version;
		config.backgroundFPS = 0;
		config.width = 1366;
		config.height = 720;
		new LwjglApplication(new Editor(), config);
	}
}
