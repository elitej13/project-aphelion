package com.ephemerality.aphelion.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ephemerality.aphelion.editor.framework.Editor;

public class EditorRuntime {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Aphelion Editor";
		config.backgroundFPS = 0;
		config.width = 1800;
		config.height = 900;
		new LwjglApplication(new Editor(), config);
	}
}
