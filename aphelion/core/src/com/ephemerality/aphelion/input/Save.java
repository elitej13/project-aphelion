package com.ephemerality.aphelion.input;

import java.nio.ByteBuffer;

public class Save {

	public String name;
	public int hours, minutes, seconds;
	public int level;
	
	public Save(String name, int hours, int minutes, int seconds, int level) {
		this.name = name;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	public Save(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		int nameSize = buffer.get();
		byte[] nameBytes = new byte[nameSize * 2];
		buffer.get(nameBytes);
		name = new String(nameBytes);
		hours = buffer.getInt();
		minutes = buffer.getInt();
		seconds = buffer.getInt();
		level = buffer.getInt();
	}
	
	
	
	
	public byte[] toByteArray() {
		byte[] header = getHeader();
		byte[] player = getPlayerData();
		byte[] world = getWorldData();
		byte[] footer = getFooter();
		byte[] data = ByteBuffer.allocate(header.length + player.length + world.length + footer.length).array();
		ByteBuffer.wrap(data).put(header).put(player).put(world).put(footer);
		return data;
	}
	
	public byte[] getHeader() {
		int nameSize = name.length();
		byte[] header = ByteBuffer.allocate((nameSize * 2) + 1 + 4).array();
		ByteBuffer.wrap(header).put(name.getBytes()).putInt(hours).putInt(minutes).putInt(seconds).putInt(level);
		return header;
	}
	public byte[] getPlayerData() {
		return null;
	}
	public byte[] getWorldData() {
		return null;
	}
	public byte[] getFooter() {
		int handshake = 666;
		byte[] footer = ByteBuffer.allocate(Integer.BYTES).array();
		ByteBuffer.wrap(footer).putInt(handshake);
		return footer;
	}
	
}
