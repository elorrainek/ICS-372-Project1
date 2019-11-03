package edu.metrostate.ics372.project1;

import java.util.GregorianCalendar;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * @author Group: Don Nguyen, Sadeq Husein, Elise Kurtz, Mohamed Abullahi, Ibsa Tilahun
 *         
 *		GROCERY STORE CLASS
 */
public class GroceryStore implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProductList inventory;
	private CartList processedOrders;
	private MemberList members;
	private static GroceryStore groceryStore;
	int membersInList;

	/**
	 * GroceryStore Constructor. 
	 */
	private GroceryStore() {
		inventory = ProductList.instance();
		members = MemberList.instance();
		processedOrders = CartList.instance();
	}
	
	/**
	 * GroceryStore instance Method. 
	 * @return GroceryStore instance
	 */
	public static GroceryStore instance() {
		if (groceryStore == null) {
			MemberIdServer.instance();
			groceryStore = new GroceryStore();
		}
		
		return groceryStore;
	}
	
	/**
	 * add to Inventory Method. 
	 * @param productName
	 * @param productId
	 * @return returns boolean if the operation was successful 
	 */
	public boolean addToInventory(Integer productId, Integer quantity) {
		return inventory.addNewProduct(productId, quantity);
	}

	/**
	 * add to Inventory Method. 
	 * @param productName
	 * @param productId
	 * @param price
	 * @return returns boolean if the operation was successful 
	 */
	public boolean addToInventory(String productName, Integer productId, Double price) {
		return inventory.addNewProduct(productName, productId, price);
	}
	
	/**
	 * Add to Inventory Method.  
	 * @param productName
	 * @param productId
	 * @param price
	 * @param quantity
	 * @return returns boolean if the operation was successful
	 */
	public boolean addToInventory(String productName, Integer productId, Double price, 
			Integer quantity) {
		return inventory.addNewProduct(productName, productId, price, quantity);
	}

	/**
	 * Remove from Inventory Method. 
	 * @param item
	 * @return boolean if the item has been removed from the inventory ProductList instance. 
	 */
	public boolean removeFromInventory(Product item) {
		if (item != null) {
			return inventory.removeProduct(item);
		} else {
			return false;
		}
	}

	/**
	 * Add to the Cart Method. 
	 * @param memberId
	 * @param date
	 * @param productId
	 * @param quantity
	 * @return the processedOrders CartList instance, that is adding to the cart. 
	 */
	public boolean addToCart(String memberId, GregorianCalendar date, 
			Integer productId, Integer quantity) {
		Product product = inventory.search(productId);
		inventory.adjustQuantity(product, quantity);
		Product itemAddedToCart = new Product(product.getProductName(),
				product.getProductId(), product.getPrice(), quantity);
		
		if (inventory.checkLowInventory(product)) {
			inventory.addToLowInventoryItemsList(product);
		}
		
		return processedOrders.addToCart(memberId, date, itemAddedToCart);
	}
	
	/**
	 * Adding to the MemberList Method.
	 * @param name
	 * @param phoneNumber
	 * @param address
	 * @param date
	 * @param feePaid
	 * @return the members MemberList instance, that is adding a new member.
	 */
	public boolean addToMemberList(String name, String address, 
			GregorianCalendar date, boolean feePaid) {
		return members.addNewMember(new Member(name, address, date, feePaid));
	}
	
	/**
	 * Adjust the Product Price Method. 
	 * @param productId
	 * @param price
	 * @return inventory ProductList instance that is adjusting the product price. 
	 */
	public boolean adjustProductPrice(Integer productId, double price) {
		return inventory.adjustProductPrice(productId, price);
	}
	
	/**
	 * Getter for the product info, using the product name. 
	 * @param productName
	 * @return inventory ProductList instance that is searching for the product name. 
	 */
	public Product getProductInfo(String productName) {
		return inventory.search(productName);
	}
	
	/**
	 * Getter for the product info, using the product name. 
	 * @param productName
	 * @return inventory ProductList instance that is searching for the product name. 
	 */
	public Product getProductInfo(Integer productId) {
		return inventory.search(productId);
	}

	/**
	 * Removing from Member List Method. 
	 * @param memberId
	 * @return the members MemberList instance that is removing the member. 
	 */
	public boolean removeFromMemberList(String memberId) {
		return members.removeMember(memberId);
	}
	
	/**
	 *Retrieve all Members Method.
	 * @return the members MemberList instance that gets all members. 
	 */
	public String retrieveAllMembers() {
		return members.getAllMembers();
	}
	
	/**
	 * Retrieve All Products
	 * @return inventory ProductList instance that gets all products.
	 */
	public List<Product> retrieveAllProducts() {
		return inventory.getAllProducts();
	}
	
	/**
	 * Retrieve All items in the cart
	 * @return list of the member's cart
	 */
	public List<Product> retrieveCart(String memberId, GregorianCalendar date) {
		return processedOrders.search(memberId, date).getItemsInCart();
	}
	
	public List<Cart> retrieveTransactions(String memberId, GregorianCalendar start, GregorianCalendar end) {
		return processedOrders.retrieveTransactions(memberId, start, end);
	}
	
	public List<Product> getLowInventoryItems() {
		return inventory.getLowInventoryItems();
	}
	
	/**
	 * Clear Instance Method (for testing)
	 * 
	 */
	protected static void clearInstance() {
		groceryStore = null;
	}
	
	/**
	 * Getter for the Processed Orders. 
	 * 
	 * @return the processedOrders CartList instance. 
	 */
	public CartList getProcessedOrders() {
		return this.processedOrders;
	}
	
	public static GroceryStore retrieve() {
		try {
            FileInputStream file = new FileInputStream("LibraryData");
            ObjectInputStream input = new ObjectInputStream(file);
            groceryStore = (GroceryStore) input.readObject();
            MemberIdServer.retrieve(input);
			return groceryStore;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}	
	
	public static boolean save() {
		try {
            FileOutputStream file = new FileOutputStream("GroceryStoreDate");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(groceryStore);
            output.writeObject(MemberIdServer.instance());
            file.close();
            return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
	
	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
		output.defaultWriteObject();
		output.writeObject(groceryStore);
	}
	
	private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
		input.defaultReadObject();
		if (groceryStore == null) {
			groceryStore = (GroceryStore) input.readObject();
		}
		else {
			input.readObject();
		}
	}
}