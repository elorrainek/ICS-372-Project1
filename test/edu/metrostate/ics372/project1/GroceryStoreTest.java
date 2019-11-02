package edu.metrostate.ics372.project1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class GroceryStoreTest {
	private final static long SEED = 20191015001L;
	private final static Random RAND = new Random(SEED);
	
	@Test
	@DisplayName("it should return an instance of GroceryStore")
	void groceryStore_instance() {
		assertTrue(GroceryStore.instance() instanceof GroceryStore);
	}
	
	@Test
	@DisplayName("it should return true if product added to inventory is not null")
	void groceryStore_addToInventory_true() {
		String productName = getSaltString();
		int productId = randIntBetween(100, 10_000);
		int quantity = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		
		GroceryStore gs = GroceryStore.instance();
		
		assertTrue(gs.addToInventory(productName, productId, price, quantity));
	}
	
	@Test
	@DisplayName("it should return false if product removed from inventory is null")
	void groceryStore_removeFromInventory_false() {
		Product product = null;
		GroceryStore gs = GroceryStore.instance();
		
		assertFalse(gs.removeFromInventory(product));
	}
	
	@Test
	@DisplayName("it should return false if product removed from inventory does not exist")
	void groceryStore_removeFromInventory_product_dne() {
		String productName = getSaltString();
		int productId = randIntBetween(100, 10_000);
		int quantity = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		
		Product product = new Product(productName, productId, price, quantity);
		GroceryStore gs = GroceryStore.instance();
		
		assertFalse(gs.removeFromInventory(product));
	}
	
	@Test
	@DisplayName("it should return true if product removed from inventory is not null and exists"
			+ "in inventory")
	void groceryStore_removeFromInventory_true() {
		String productName = getSaltString();
		int productId = randIntBetween(100, 10_000);
		int quantity = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		
		Product product = new Product(productName, productId, price, quantity);
		GroceryStore gs = GroceryStore.instance();
		gs.addToInventory(productName, productId, price, quantity);
		
		assertTrue(gs.removeFromInventory(product));
	}
	
	@Test
	@DisplayName("it should create a new cart if one doesn't exist for products added")
	void groceryStore_addToCart_createNewCart() {
		String memberId = getSaltString();
		Integer productId = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Date date = new Date();
		
		GroceryStore.clearInstance();
		GroceryStore gs = GroceryStore.instance();
		gs.addToInventory(getSaltString(), productId, price);
		
		assertTrue(gs.getProcessedOrders().getListOfCarts().isEmpty());
		
		gs.addToCart(memberId, date, productId, 1);
		assertEquals(1, gs.getProcessedOrders().getListOfCarts().size());
	}
	
	@Test
	@DisplayName("it should add to an existing cart if one exists")
	void groceryStore_addToCart_addToExistingCart() {
		String memberId = getSaltString();
		Integer productId = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity = randIntBetween(100, 10_000);
		Date date = new Date();
		
		GroceryStore.clearInstance();
		CartList.clearInstance();
		GroceryStore gs = GroceryStore.instance();
		gs.addToInventory(getSaltString(), productId, price, quantity);
		
		gs.addToCart(memberId, date, productId, 1);
		gs.addToCart(memberId, date, productId, 1);
		
		assertEquals(1, gs.getProcessedOrders().getListOfCarts().size());
	}
	
	@Test
	@DisplayName("it should adjust the product quantity from inventory after adding"
			+ "to cart")
	void groceryStore_addToCart_adjustInventory() {
		String memberId = getSaltString();
		String productName = getSaltString();
		Integer productId = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Date date = new Date();
		Integer quantity = randIntBetween(100, 10_000);
		
		GroceryStore.clearInstance();
		GroceryStore gs = GroceryStore.instance();
		gs.addToInventory(productName, productId, price, quantity);
		gs.addToCart(memberId, date, productId, 1);
		
		assertEquals(quantity - 1, gs.getProductInfo(productName).getQuantity());
	}
	
//	@Test
//	@DisplayName("it should return false if product added to cart is null")
//	void groceryStore_addToCart_false() {
//		Product product = null;
//		GroceryStore gs = GroceryStore.instance();
//		assertFalse(gs.removeFromCart(product));
//	}
	
//	@Test
//	@DisplayName("it should return true if product added to cart is not null")
//	void groceryStore_addToCart_true() {
//		String productName = getSaltString();
//		int productId = randIntBetween(100, 10_000);
//		int quantity = randIntBetween(100, 10_000);
//		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
//		
//		Product product = new Product(productName, productId, price, quantity);
//		GroceryStore gs = GroceryStore.instance();
//		gs.addToInventory(product);
//		
//		assertTrue(gs.addToCart(product));
//	}
	
	private int randIntBetween(int min, int max) {
		return min + RAND.nextInt(max - min);
	}
	
	private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}
}
