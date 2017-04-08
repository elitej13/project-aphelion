package com.ephemerality.aphelion.util.debug;

public enum DebugType {
	
	Controller ("CONTROLLER DEBUG");
	
	private final String string;
	
	private DebugType(String s) {
		this.string = s;
	}
	
	public String toString() {
		return string;
	}
	
	
}