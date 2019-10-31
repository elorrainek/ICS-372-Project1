package edu.metrostate.ics372.project1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class UserInteraceTest {

	@Test
	@DisplayName("it should return an instance of UserInterface")
	void userInterface_instance() {
		assertTrue(UserInterface.instance() instanceof UserInterface);
	}
	
//	@Test
//	@DisplayName("")

}
