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
		expected += "1 to add a member\n";
		expected += "2 to remove a member\n";
		expected += "3 to retrieve a member's information\n";
		expected += "4 to add a product in inventory\n";
		expected += "5 to checkout a member's cart\n";
		expected += "6 to retrieve a product's information\n";
		expected += "7 to process a shipment\n";
		expected += "8 to adjust the price of an item\n";
		expected += "9 to print all transactions for a member\n";
		expected += "10 to display all members\n";
		expected += "11 to display all iventory\n";		
		expected += "12 to save all data to disk\n";
		// only display retrieve option in first menu		
		expected += "14 to display list of commands\n";
		
		assertEquals(expected, UserInterface.instance().help());
	}
	
//	@Test
//	@DisplayName("")

}
