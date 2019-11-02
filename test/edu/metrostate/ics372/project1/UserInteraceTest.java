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
	
	@Test
	@DisplayName("it should return a string value for helping users with the user interface")
	void userInterface_help() {
		String expected = "Enter a number between 0 and 12 as explained below:\n";
		expected += "0 to Exit\n";
		expected += "1 to retrieve all member names\n";
		expected += "2 to add a member\n";
		expected += "3 to remove a member\n";
		expected += "4 to retrieve inventory information\n";
		expected += "5 to add a product in inventory\n";
		expected += "6 to edit product price\n";
		expected += "7 to retrieve product info\n";
		expected += "8 to add a product to a member's cart\n";
		expected += "9 to remove a product from a member's cart\n";
		expected += "10 to remove a product\n";
		expected += "11 save all information to disk\n";
		
		assertEquals(expected, UserInterface.instance().help());
	}
	
//	@Test
//	@DisplayName("")

}
