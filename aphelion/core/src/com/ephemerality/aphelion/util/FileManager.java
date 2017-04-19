package com.ephemerality.aphelion.util;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class FileManager {
	
//	public static final String mapPath = "maps/";
	
	public static void writeToFile(String location, byte[] data) {
		try {
			FileHandle handle = Gdx.files.absolute(location);
			handle.file().createNewFile();
			handle.writeBytes(data, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static byte[] readFromFile(String location) {
		FileHandle handle = Gdx.files.absolute(location);
		byte[] data = handle.readBytes();
		return data;
	}
	
}