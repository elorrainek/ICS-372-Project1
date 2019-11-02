package edu.metrostate.ics372.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

public class UserInterface {
	private static UserInterface userInterface;
	private static GroceryStore groceryStore;
	private static final String EXIT = "0";	
	private static final String ADD_MEMBER = "1";
	private static final String REMOVE_MEMBER = "2";
	private static final String GET_MEMBER_INFO = "3"; // implement
	private static final String ADD_PRODUCT = "4"; 
	private static final String CHECKOUT_MEMBER = "5";
	private static final String PRODUCT_INFO = "6";
	private static final String PROCESS_SHIPMENT = "7"; // reuse 4? implement
	private static final String ADJUST_PRICE = "8"; 
	private static final String PRINT_TRANSACTIONS = "9"; //implement
	private static final String PRINT_MEMBER_DETAILS = "10"; //implement
	private static final String INVENTORY_INFO = "11";
	private static final String SAVE = "12";
	private static final String RETRIEVE = "13";
	private static final String HELP = "14";
	private static final String INVALID = "Invalid entry";
	
	private UserInterface() {
		groceryStore = GroceryStore.instance();
	}
	
	public static UserInterface instance() {
		UserInterface instance = null;
		if (userInterface == null) {
			instance = new UserInterface();
		} else {
			instance = userInterface;
		}
		return instance;
	}
	
	public String help() {
		String message = "Enter a number between 0 and 12 as explained below:\n";
		
		message += "0 to Exit\n";
		message += "1 to add a member\n";
		message += "2 to remove a member\n";
		message += "3 to retrieve a member's information\n";
		message += "4 to add a product in inventory\n";
		message += "5 to checkout a member's cart\n";
		message += "6 to retrieve a product's information\n";
		message += "7 to process a shipment\n";
		message += "8 to adjust the price of an item\n";
		message += "9 to print all transactions for a member\n";
		message += "10 to display all members\n";
		message += "11 to display all iventory\n";		
		message += "12 to save all data to disk\n";
		// only display retrieve option in first menu		
		message += "14 to display list of commands\n";
		
		return message;
	}
	
	public String getInput(BufferedReader br) {
		String input = "";
		
		try {
			input = br.readLine();
		} catch(IOException exception) {
			
		}
		
		return input;
	}
	
	public String prompt(String message, BufferedReader br) {
		System.out.println(message);
		String input = getInput(br);
		
		return input;
	}
	
	public boolean yesOrNo(String message, BufferedReader br) {
		boolean isYes = false;
		boolean continueLoop = true;
		String option = null;
		
		do {
			option = prompt(message, br);
			if (option.equalsIgnoreCase("yes") || option.equalsIgnoreCase("y")) {
				isYes = true;
				continueLoop = false;
			} else if (option.equalsIgnoreCase("no") || option.equalsIgnoreCase("n")) {
				continueLoop = false;
			} else {
				System.out.println(INVALID);
			}
		}while (continueLoop);
		
		return isYes;
	}
	
	public void removeMember(BufferedReader br) {
		String message = "Enter the memberId (include leading 'M'): ";
		do {
			String memberId = prompt(message, br);
			boolean wasSuccessful = groceryStore.removeFromMemberList(memberId);
			
			if (wasSuccessful) {
				System.out.println(memberId + " was removed successfully");
				message = "Remove another member? ";
			} else {
				System.out.println("Unable to remove " + memberId);
			}
		} while(yesOrNo(message, br));
	}
	
	public void addNewMember(BufferedReader br) {
		do {
			String name = prompt("Enter the new member's name: ", br);
			String address = prompt("Enter the new member's address: ", br);
			boolean feePaid = yesOrNo("Will the new member be paying the joining fee today? (y/n) ", 
					br);
			GregorianCalendar date = new GregorianCalendar();
			
			boolean wasSuccessful = groceryStore.addToMemberList(name, address, date, feePaid);
			
			if (wasSuccessful) {
				System.out.println(name + " was successfully added");
			} else {
				System.out.println("Error adding " + name + " to the system.");
			}
		} while(yesOrNo("Add another member? ", br));
	}
	
	private int validInt(String message, BufferedReader br) {
		int validInt = 0;
		boolean loop = true;
		
		while(loop) {
			try {
				validInt = Integer.parseInt(prompt(message, br));
				loop = false;
			} catch(NumberFormatException error) {
				System.out.println("Invalid number");
				validInt(message, br);
			}
		}
		
		return validInt;
	}
	
	private double validDouble(String message, BufferedReader br) {
		double validDouble = 0.0;
		boolean loop = true;
		
		while(loop) {
			try {
				validDouble = Double.parseDouble(prompt(message, br));
				loop = false;
			} catch(NumberFormatException error) {
				System.out.println("Invalid number");
				validDouble(message, br);
			}
		}
		
		return validDouble;
	}
	
	public void addNewProduct(BufferedReader br) {
		do {
			String productName = prompt("Enter the new product name: ", br);
			Integer productId = validInt("Enter the new product id: ", br);
			Double productPrice = validDouble("Enter the product price: ", br);
			Integer quantity = null;
			if(yesOrNo("Do you know the product quantity? (y/n) ", br)) {
				quantity = validInt("Enter the product quantity: ", br); 
			}
			
			boolean wasSuccessful = groceryStore.addToInventory(productName, productId, 
					productPrice, quantity);
			
			if (wasSuccessful) {
				System.out.println(productName + " was successfully added");
			} else {
				System.out.println("Error adding " + productName + " to the system.");
			}
		} while(yesOrNo("Add another product? ", br));
	}
	
	public void adjustPrice(BufferedReader br) {
		do {
			Integer productId = validInt("Enter the product id: ", br);
			Double productPrice = validDouble("Enter the adjusted price: ", br);
			
			boolean wasSuccessful = groceryStore.adjustProductPrice(productId, productPrice);
			
			if (wasSuccessful) {
				System.out.println("Price successfully adjusted");
			} else {
				System.out.println("Error adjust the price");
			}
		} while(yesOrNo("Adjust another product price? (y/n) ", br));
	}
	
	public void getProductInfo(BufferedReader br) {
		do {
			String productName = prompt("Enter the product name: ", br);			
			Product product = groceryStore.getProductInfo(productName);
			
			if (product != null) {
				System.out.println(product);
			} else {
				System.out.println("Could not find " + productName + " in the system");
			}
			
		} while(yesOrNo("Search for another product? (y/n)", br));
	}
	
	public void checkoutMember(BufferedReader br) {
		do {
			Integer productId = validInt("Enter the product id", br);
		}while(yesOrNo("Process more items? (y/n) ", br));
	}
	
	private void save() {
		if(GroceryStore.save()) {
			System.out.println("The Grocery Store data has been saved to file 'GroceryStoreData'");
		}
		else {
			System.out.println("Error in saving");
		}
	}
	
	private void retrieve() {
		try {
			GroceryStore tempGroceryStore = GroceryStore.retrieve();
			if ( tempGroceryStore != null) {
				System.out.println("The Grocery Store was retrieved from file 'GroceryStoreData'");
				groceryStore = tempGroceryStore;
			}
			else {
				System.out.println("File doesn't exist; creating a new Grocery Store");
				groceryStore = GroceryStore.instance();
			}

	public void displayOptions() {
		String input;
		System.out.println(help());
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		while(!(input = getInput(br).toLowerCase()).equals(EXIT)) {
			switch(input) {
			case HELP:
				System.out.println("\n\n" + help());
				break;
			case ADJUST_PRICE: // 8
				adjustPrice(br);
				break;
			case ADD_MEMBER: // 1
				addNewMember(br);
				break;
			case REMOVE_MEMBER: // 2
				removeMember(br);
				break;
			case GET_MEMBER_INFO: // 3
				//implement
				break;
			case ADD_PRODUCT: // 4
				addNewProduct(br);
				break;
			case CHECKOUT_MEMBER: // 5
				checkoutMember(br);
				break;
			case PRODUCT_INFO: // 6
				getProductInfo(br);
				break;
			case PROCESS_SHIPMENT: // 7
				//implement
				break;
			case PRINT_TRANSACTIONS: //9
				//implement
				break;
				break;
			case PRINT_MEMBER_DETAILS: //10
				System.out.println(groceryStore.retrieveAllMembers());
				System.out.println("\n\n" + help());
				break;
			case INVENTORY_INFO: // 11
				System.out.println(groceryStore.retrieveAllProducts());
				System.out.println("\n\n" + help());
			case SAVE: // 12
				save();
				break;	
			case RETRIEVE: //13
				retreive();
				break;
			
			default:
				System.out.println(INVALID);
				System.out.println("\n\n" + help());
				break;
					
			}
		}
		
		System.out.println("Closing application");
	}
	
	public static void main(String[] args) {
		UserInterface.instance().displayOptions();
	}
}