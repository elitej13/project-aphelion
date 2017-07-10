package com.ephemerality.aphelion.util;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class FileManager {
	
//	public static final String mapPath = "maps/";
	public static final int RECTANGLE_BYTES = 16;
	public static final int VECTOR2_BYTES = 8;
	
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
	
	public static byte[] toByteArray(Rectangle rect) {
		return ByteBuffer.allocate(Float.BYTES * 4).putFloat(rect.x).putFloat(rect.y).putFloat(rect.width).putFloat(rect.height).array();
	}
	public static Rectangle rectangleFromByteArray(byte[] data) {
		return new Rectangle(ByteBuffer.wrap(data).getFloat(0), ByteBuffer.wrap(data).getFloat(1), ByteBuffer.wrap(data).getFloat(2), ByteBuffer.wrap(data).getFloat(3));
	}
	public static byte[] toByteArray(Vector2 vect) {
		return ByteBuffer.allocate(Float.BYTES * 2).putFloat(vect.x).putFloat(vect.y).array();
	}
	public static Vector2 vector2FromByteArray(byte[] data) {
		return new Vector2(ByteBuffer.wrap(data).getFloat(0), ByteBuffer.wrap(data).getFloat(1));
	}
}