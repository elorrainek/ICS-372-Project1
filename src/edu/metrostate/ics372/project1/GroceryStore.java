package edu.metrostate.ics372.project1;

import java.util.HashMap;
import java.util.Map;

//TODO: create main collection class for all collections
public class GroceryStore {
	private Map<String, Product> inventory;
	private Map<String, Product> cart;
	private Map<String, Member> members;
	
	public GroceryStore() {
		this.inventory = new HashMap<>();
		this.cart = new HashMap<>();
		this.members = new HashMap<>();
	}
}
