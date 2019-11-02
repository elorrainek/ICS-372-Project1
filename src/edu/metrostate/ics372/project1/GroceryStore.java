package edu.metrostate.ics372.project1;

import java.util.Date;
import java.util.List;
import java.io.FileInputStream;

public class GroceryStore implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProductList inventory;
	private CartList processedOrders;
	private MemberList members;
	private static GroceryStore groceryStore;
	int membersInList;

	private GroceryStore() {
		inventory = ProductList.instance();
		members = MemberList.instance();
		processedOrders = CartList.instance();
	}
	
	public static GroceryStore instance() {
		if (groceryStore == null) {
			MemberIdServer.instance();
			groceryStore = new GroceryStore();
		}
		
		return groceryStore;
	}

	public boolean addToInventory(String productName, Integer productId, Double price) {
		return inventory.addNewProduct(productName, productId, price);
	}
	
	public boolean addToInventory(String productName, Integer productId, Double price, 
			Integer quantity) {
		return inventory.addNewProduct(productName, productId, price, quantity);
	}

	public boolean removeFromInventory(Product item) {
		if (item != null) {
			return inventory.removeProduct(item);
		} else {
			return false;
		}
	}

	public boolean addToCart(String memberId, Date date, Integer productId, 
			Integer quantity) {
		Product product = inventory.search(productId);
		inventory.adjustQuantity(product, quantity);
		
		return processedOrders.addToCart(memberId, date, product);
	}
//
//	public boolean removeFromCart(Product item) {
//		if (item != null) {
//			cart.remove(item);
//			return SuccessOfOperation;
//		} else {
//			return false;
//		}
//
//	}
	
	//String name, String address, Date date, boolean feePaid
	public boolean addToMemberList(String name, String address, Date date, boolean feePaid) {
		return members.addNewMember(new Member(name, address, date, feePaid));
	}
	
	public boolean adjustProductPrice(Integer productId, double price) {
		return inventory.adjustProductPrice(productId, price);
	}
	
	public Product getProductInfo(String productName) {
		return inventory.search(productName);
	}

	public boolean removeFromMemberList(String memberId) {
		return members.removeMember(memberId);
	}
	
	public String retrieveAllMembers() {
		return members.getAllMembers();
	}
	
	public List<Product> retrieveAllProducts() {
		return inventory.getAllProducts();
	}
	
	public static void clearInstance() {
		groceryStore = null;
	}
	
	public CartList getProcessedOrders() {
		return this.processedOrders;
	}
	
	public static GroceryStore retrieve() {
		try {
			FileInputStream uploadFile = new FileInputStream("GroceryStoreData");
			ObjectInputSteam input = new ObjectInputStream(uploadFile);
			input.readObject();
			MemberIdServer.retrieve(input);
			return groceryStore;
		} catch (IOException ioe) {
			ioe.PrintStackStrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.PrintStackTrace();
			return null;
		}
	}	
	
	public static boolean save() {
		ObjectOutputStream saveData = null;
		try {
			FileOutputStream saveFile = new FileOutputStream("GroceryStoreData");
			saveData = new OutputObjectStream(saveFile);
			saveData.writeObject(groceryStore);
			saveData.writeObject(MemberIdServer.instance());
			saveData.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
	
	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
		output.defaultWriteObject();
		output.writeobject(groceryStore);
	}
	
	private void readObject(java.io.ObjectInputStream input) throws IOException {
		input.defaultReadObject();
		if (groceryStore == null) {
			groceryStore = (groceryStore) input.readObject();
		}
		else {
			input.readObject();
		}
	}

}
