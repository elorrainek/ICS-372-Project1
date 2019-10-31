package edu.metrostate.ics372.project1;

import java.util.ArrayList;

//TODO: create main collection class for all collections
public class GroceryStore {
	private ArrayList<ProductList> inventory;
	private ArrayList<Product> cart;
	private ArrayList<MemberList> members;
	private final boolean SuccessOfOperation = true;
	int inventoryCount;
	int cartCount;
	int membersInList;

	public GroceryStore() {
		this.inventory = new ArrayList<ProductList>();
		this.cart = new ArrayList<Product>();
		this.members = new ArrayList<MemberList>();
	}

	public boolean addToInventory(ProductList product) {
		if (product != null) {
			inventory.add(product);
			inventoryCount++;
			System.out.println("PRODUCT HAS BEEN ADDED TO INVENTORY: " + product);
			System.out.println("Inventory Stock: " + inventoryCount);
			return SuccessOfOperation;
		} else {
			return false;
		}
	}

	public boolean removeFromInventory(ProductList item) {
		if (item != null) {
			inventory.remove(item);
			inventoryCount--;
			System.out.println("PRODUCT HAS BEEN REMOVED FROM INVENTORY: " + item);
			System.out.println("Inventory Stock: " + inventoryCount);
			return SuccessOfOperation;
		} else {
			return false;
		}
	}

	public boolean addToCart(Product item) {
		if (item != null) {
			cart.add(item);
			cartCount++;
			System.out.println("PRODUCT HAS BEEN ADDED TO CART: " + item);
			System.out.println("Cart Items: " + cartCount);
			return SuccessOfOperation;
		} else {
			return false;
		}
	}

	public boolean removeFromCart(Product item) {
		if (item != null) {
			cart.remove(item);
			cartCount--;
			System.out.println("PRODUCT HAS BEEN REMOVED FROM CART: " + item);
			System.out.println("Cart Items: " + cartCount);
			return SuccessOfOperation;
		} else {
			return false;
		}

	}

	public boolean addToMemberList(MemberList member) {
		if (member != null) {
			members.add(member);
			membersInList++;
			System.out.println("MEMBER HAS BEEN ADDED: " + member);
			System.out.println("Members in List: " + membersInList);
			return SuccessOfOperation;
		} else {
			return false;
		}

	}

	public boolean removeFromMemberList(MemberList member) {
		if (member != null) {
			members.remove(member);
			membersInList--;
			System.out.println("MEMBER HAS BEEN REMOVED: " + member);
			System.out.println("Members in List: " + membersInList);
			return SuccessOfOperation;
		} else {
			return false;
		}
	}

}