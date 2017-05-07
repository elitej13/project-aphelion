package com.ephemerality.aphelion.util.debug;

public enum DebugType {
	
	Controller ("CONTROLLER DEBUG"),
	Console_Unrecognized_Command ("Console Error: unrecognized command, try \"help\""),
	Console_Expecting_Number ("Console Error: args expecting number, try \"help\""),
	Console_Expecting_Args ("Console Error: not enough args, try \"help\"");
	
	private final String string;
	
	private DebugType(String s) {
		this.string = s;
	}
	
	public String toString() {
		return string;
	}
	
	
}
