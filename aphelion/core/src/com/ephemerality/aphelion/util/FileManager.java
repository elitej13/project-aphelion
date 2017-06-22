package com.ephemerality.aphelion.util;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class FileManager {
	
//	public static final String mapPath = "maps/";
	
	public static boolean writeToFile(String location, byte[] data, boolean absolutepath) {
		try {
			FileHandle handle;
			if(absolutepath) handle = Gdx.files.absolute(location);
			else handle = Gdx.files.local(location);
			System.out.println(handle.path());
			handle.file().createNewFile();
			handle.writeBytes(data, false);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static byte[] readFromFile(String location, boolean absolutepath) {
		FileHandle handle;
		if(absolutepath) handle = Gdx.files.absolute(location);
		else handle = Gdx.files.local(location);
		byte[] data = handle.readBytes();
		return data;
	}
	
	
	public static boolean writeToFile(String location, String data, boolean absolutepath, boolean append) {
		try {
			FileHandle handle;
			if(absolutepath) handle = Gdx.files.absolute(location);
			else handle = Gdx.files.local(location);
			handle.file().createNewFile();
			handle.writeString(data, append);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}