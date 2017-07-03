package com.ephemerality.aphelion.util.debug;

public enum DebugType {
	
	Controller ("CONTROLLER DEBUG"),
	Uncaught_Exception ("Uncaught Exception Error: You done fucked up fool. Now don't be a bitch and fix it"),
	Loader_Doll_Missing_Batch ("LoadManager Error: DollParameter is missing a spritebatch"),
	
	Console_Unrecognized_Command ("Console Error: unrecognized command, try \"help\""),
	Console_Expecting_Number ("Console Error: args expecting number, try \"help\""),
	Console_Expecting_Args ("Console Error: not enough args, try \"help\""),
	Console_Logging ("Started Logging"),
	
	Game_save_Successful ("Game save successful"),
	Game_save_Unsuccessful ("Game save unsuccessful");
	
	
	private final String string;
	
	private DebugType(String s) {
		this.string = s;
	}
	
	@Override
	public String toString() {
		return string;
	}
	
	
}
