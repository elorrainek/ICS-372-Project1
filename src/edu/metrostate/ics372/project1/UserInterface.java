package edu.metrostate.ics372.project1;

public class UserInterface {
	private static UserInterface userInterface;
	private UserInterface() {}
	
	public static UserInterface instance() {
		UserInterface instance = null;
		if (userInterface == null) {
			instance = new UserInterface();
		} else {
			instance = userInterface;
		}
		return instance;
	}
}
