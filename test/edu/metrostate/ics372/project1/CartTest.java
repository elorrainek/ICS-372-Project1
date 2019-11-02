package edu.metrostate.ics372.project1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class CartTest {
	private final static long SEED = 20191015001L;
	private final static Random RAND = new Random(SEED);

	@Test
	@DisplayName("it should instantiate with the memberId and process date")
	void cart_instantiate() {
		String memberId = getSaltString();
		Date date = new Date(); 
		
		Cart cart = new Cart(memberId, date);
		assertEquals(memberId, cart.getMemberId());
		assertEquals(date, cart.getProcessDate());
	}
	
	@Test
	@DisplayName("it should return an empty cart")
	void cart_getItemsInCart_empty() {
		String memberId = getSaltString();
		Date date = new Date(); 
		
		Cart cart = new Cart(memberId, date);
		assertTrue(cart.getItemsInCart().isEmpty());
	}
	
	@Test
	@DisplayName("it should add an item to the cart")
	void cart_addToCart() {
		String memberId = getSaltString();
		Date date = new Date(); 
		
		Product product1 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		Product product2 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		Product product3 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		Cart cart = new Cart(memberId, date);
		
		cart.addToCart(product1);
		cart.addToCart(product2);
		cart.addToCart(product3);
		
		assertTrue(cart.getItemsInCart().contains(product1));
		assertTrue(cart.getItemsInCart().contains(product2));
		assertTrue(cart.getItemsInCart().contains(product3));
	}
	
	@Test
	@DisplayName("it should return the total price of items in cart")
	void cart_getTotal() {
		String memberId = getSaltString();
		Date date = new Date(); 
		
		Product product1 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		Product product2 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		Product product3 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		Cart cart = new Cart(memberId, date);
		
		cart.addToCart(product1);
		cart.addToCart(product2);
		cart.addToCart(product3);
		
		Double expected = ((product1.getPrice() * product1.getQuantity()) + 
				(product2.getPrice() * product2.getQuantity()) + (product3.getPrice() 
						* product3.getQuantity()));
		
		assertEquals(expected, cart.getTotal());
	}
	
	@Test
	@DisplayName("it should return false if the carts are not equal")
	void cart_equals_false() {
		String memberId1 = getSaltString();
		String memberId2 = getSaltString();
		Date date = new Date(); 
		
		Cart cart1 = new Cart(memberId1, date);
		Cart cart2 = new Cart(memberId2, date);
		
		assertFalse(cart1.equals(cart2));
	}
	
	@Test
	@DisplayName("it should return true if the carts are equal")
	void cart_equals_true() {
		String memberId1 = getSaltString();
		Date date = new Date(); 
		
		Cart cart1 = new Cart(memberId1, date);
		Cart cart2 = new Cart(memberId1, date);
		
		assertTrue(cart1.equals(cart2));
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
