package edu.metrostate.ics372.project1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class CartListTest {
	private final static long SEED = 20191015001L;
	private final static Random RAND = new Random(SEED);

	@Test
	@DisplayName("it should return an instance of CartList")
	void cartList_instance() {
		assertTrue(CartList.instance() instanceof CartList);
	}
	
	@Test
	@DisplayName("it should return the list of carts")
	void cartList_getListOfCarts() {
		CartList.clearInstance();
		CartList cl = CartList.instance();
		
		assertTrue(cl.getListOfCarts().isEmpty());
	}
	
	@Test
	@DisplayName("it should return null if the cart does not exist in the list")
	void cartList_search_null() {
		CartList.clearInstance();
		CartList cl = CartList.instance();
		Product product = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), randIntBetween(100, 10_000));
		
		cl.addToCart(getSaltString(), new Date(), product);
		
		assertNull(cl.search(getSaltString(), new Date()));
	}
	
	@Test
	@DisplayName("it should return a cart object if found in list")
	void cartList_search() {
		String memberId = getSaltString();
		Date date = new Date();
		CartList.clearInstance();
		CartList cl = CartList.instance();
		
		Cart cart = new Cart(memberId, date);
		cl.addNewCart(memberId, date);
		
		assertTrue(cart.equals(cl.search(memberId, date)));
	}
	
	@Test
	@DisplayName("it should create a new cart if one doesn't exist")
	void cartList_addToCart() {
		String memberId = getSaltString();
		Date date = new Date();
		Product product = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), randIntBetween(100, 10_000));
		
		CartList.clearInstance();
		CartList cl = CartList.instance();
		assertTrue(cl.getListOfCarts().isEmpty());
		
		cl.addToCart(memberId, date, product);
		assertEquals(1, cl.getListOfCarts().size());
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
