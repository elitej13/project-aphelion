package com.ephemerality.aphelion.util;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.ephemerality.aphelion.spawn.world.Level;

public class FileManager {
	
	public static final String mapPath = "assets/maps/";
	
	public static void writeLevelToFile(String location, byte[] data) {
		try {
			FileHandle handle = Gdx.files.local(mapPath + location);
			if(!handle.exists()) {
				handle.file().createNewFile();
			}
			handle.writeBytes(data, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Level readLevelFromFile(String location) {
		FileHandle handle = Gdx.files.local(mapPath + location);
		System.out.println(handle.file().getAbsolutePath());;
		byte[] data = handle.readBytes();
		return new Level(data);
	}
	
	
}