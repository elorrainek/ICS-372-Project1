package edu.metrostate.ics372.project1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;
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
		GregorianCalendar date = new GregorianCalendar();
		
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
		GregorianCalendar date = new GregorianCalendar();
		
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
		GregorianCalendar date = new GregorianCalendar();
		Integer quantity = randIntBetween(100, 10_000);
		
		GroceryStore.clearInstance();
		GroceryStore gs = GroceryStore.instance();
		gs.addToInventory(productName, productId, price, quantity);
		gs.addToCart(memberId, date, productId, 1);
		
		assertEquals(quantity - 1, gs.getProductInfo(productName).getQuantity());
		assertEquals(1, gs.getProcessedOrders()
				.search(memberId, date)
				.getItemsInCart()
				.get(0)
				.getQuantity());
	}
	
	@Test
	@DisplayName("it should return a list of all low inventory items after cart add")
	void groceryStore_addToCart_lowInventory() {
		String memberId = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		String productName1 = getSaltString();
		Integer productId1 = randIntBetween(100, 10_000);
		Double price1 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity1 = 6;
		
		String productName2 = getSaltString();
		Integer productId2 = randIntBetween(100, 10_000);
		Double price2 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity2 = 6;
		
		String productName3 = getSaltString();
		Integer productId3 = randIntBetween(100, 10_000);
		Double price3 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity3 = randIntBetween(100, 10_000);
		
		GroceryStore.clearInstance();
		GroceryStore gs = GroceryStore.instance();
		gs.addToInventory(productName1, productId1, price1, quantity1);
		gs.addToInventory(productName2, productId2, price2, quantity2);
		gs.addToInventory(productName3, productId3, price3, quantity3);
		gs.addToCart(memberId, date, productId1, 1);
		gs.addToCart(memberId, date, productId2, 3);
		gs.addToCart(memberId, date, productId3, 3);
		
		assertTrue(!gs.getLowInventoryItems().isEmpty());
	}
	
	@Test
	@DisplayName("it should retrieve the member's cart")
	void groceryStore_retrieveCart() {
		String memberId = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		
		String productName1 = getSaltString();
		Integer productId1 = randIntBetween(100, 10_000);
		Double price1 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity1 = randIntBetween(100, 10_000);
		
		String productName2 = getSaltString();
		Integer productId2 = randIntBetween(100, 10_000);
		Double price2 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity2 = randIntBetween(100, 10_000);
		
		String productName3 = getSaltString();
		Integer productId3 = randIntBetween(100, 10_000);
		Double price3 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity3 = randIntBetween(100, 10_000);
		
		
		GroceryStore.clearInstance();
		GroceryStore gs = GroceryStore.instance();
		gs.addToInventory(productName1, productId1, price1, quantity1);
		gs.addToInventory(productName2, productId2, price2, quantity2);
		gs.addToInventory(productName3, productId3, price3, quantity3);
		
		gs.addToCart(memberId, date, productId1, 2);
		gs.addToCart(memberId, date, productId2, 2);
		gs.addToCart(memberId, date, productId3, 2);
		
		assertEquals(3, gs.retrieveCart(memberId, date).size());
	}
	
	@Test
	@DisplayName("it should retrieve processed carts between the specified dates for"
			+ "the specified member")
	void groceryStore_retrieveCartBetweenDates() {
		String memberId1 = getSaltString();
		String memberId2 = getSaltString();
		String memberId3 = getSaltString();
		GregorianCalendar start = new GregorianCalendar(1900, 1, 1);
		GregorianCalendar date1 = new GregorianCalendar(2000, 1, 1);
		GregorianCalendar date2 = new GregorianCalendar(2010, 1, 1);
		GregorianCalendar date3 = new GregorianCalendar(2015, 1, 1);
		GregorianCalendar end = new GregorianCalendar(2020, 1, 1);
		
		String productName1 = getSaltString();
		Integer productId1 = randIntBetween(100, 10_000);
		Double price1 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity1 = randIntBetween(100, 10_000);
		
		String productName2 = getSaltString();
		Integer productId2 = randIntBetween(100, 10_000);
		Double price2 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity2 = randIntBetween(100, 10_000);
		
		String productName3 = getSaltString();
		Integer productId3 = randIntBetween(100, 10_000);
		Double price3 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity3 = randIntBetween(100, 10_000);
		
		
		GroceryStore.clearInstance();
		GroceryStore gs = GroceryStore.instance();
		gs.addToInventory(productName1, productId1, price1, quantity1);
		gs.addToInventory(productName2, productId2, price2, quantity2);
		gs.addToInventory(productName3, productId3, price3, quantity3);
		
		gs.addToCart(memberId1, date1, productId1, 2);
		gs.addToCart(memberId2, date2, productId2, 2);
		gs.addToCart(memberId3, date3, productId3, 2);
		gs.addToCart(memberId1, date2, productId2, 2);
		gs.addToCart(memberId1, date3, productId3, 2);
		
		assertEquals(3, gs.retrieveTransactions(memberId1, start, end).size());
	}
	
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
