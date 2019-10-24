package edu.metrostate.ics372.project1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class ProductTest {
	private final static long SEED = 20191015001L;
	private final static Random RAND = new Random(SEED);
	final String productName = "productName";

	@Test
	@DisplayName("it should return the productName using the getProductName method")
	void product_getProductName() {
		String productName = getSaltString();
		int productId = randIntBetween(100, 10_000);
		int quantity = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		
		Product product = new Product(productName, productId, price, quantity);
		
		assertEquals(productName, product.getProductName());
	}
	
	@Test
	@DisplayName("it should adjust the productName using the setProductName method")
	void product_setProductName() {
		String productName = getSaltString();
		String adjustedProductName = getSaltString();
		int productId = randIntBetween(100, 10_000);
		int quantity = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		
		Product product = new Product(productName, productId, price, quantity);
		product.setProductName(adjustedProductName);
		
		assertEquals(adjustedProductName, product.getProductName());
	}
	
	@Test
	@DisplayName("it should return the productId using the getProductId method")
	void product_getProductId() {
		String productName = getSaltString();
		int productId = randIntBetween(100, 10_000);
		int quantity = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		
		Product product = new Product(productName, productId, price, quantity);
		
		assertEquals(productId, product.getProductId());
	}
	
	@Test
	@DisplayName("it should adjust the productId using the setProductId method")
	void product_setProductId() {
		String productName = getSaltString();
		int productId = randIntBetween(100, 10_000);
		int adjustedProductId = randIntBetween(100, 10_000);
		int quantity = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		
		Product product = new Product(productName, productId, price, quantity);
		product.setProductId(adjustedProductId);
		
		assertEquals(adjustedProductId, product.getProductId());
	}
	
	@Test
	@DisplayName("it should return the product price using the getPrice method")
	void product_getPrice() {
		String productName = getSaltString();
		int productId = randIntBetween(100, 10_000);
		int quantity = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		
		Product product = new Product(productName, productId, price, quantity);
		
		assertEquals(price, product.getPrice());
	}
	
	@Test
	@DisplayName("it should adjust the product price using the setPrice method")
	void product_setPrice() {
		String productName = getSaltString();
		int productId = randIntBetween(100, 10_000);
		int quantity = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Double adjustedPrice = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		
		Product product = new Product(productName, productId, price, quantity);
		product.setPrice(adjustedPrice);
		
		assertEquals(adjustedPrice, product.getPrice());
	}
	
	@Test
	@DisplayName("it should return the product quantity using the getQuantity method")
	void product_getQuantity() {
		String productName = getSaltString();
		int productId = randIntBetween(100, 10_000);
		int quantity = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		
		Product product = new Product(productName, productId, price, quantity);
		
		assertEquals(quantity, product.getQuantity());
	}
	
	@Test
	@DisplayName("it should adjust the product price using the setQuantity method")
	void product_setQuantity() {
		String productName = getSaltString();
		int productId = randIntBetween(100, 10_000);
		int quantity = randIntBetween(100, 10_000);
		int adjustedQuantity = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		
		Product product = new Product(productName, productId, price, quantity);
		product.setQuantity(adjustedQuantity);
		
		assertEquals(adjustedQuantity, product.getQuantity());
	}
	
	@Test
	@DisplayName("it should instantiate Product without value for quantity")
	void product_constructor_without_quantity() {
		String productName = getSaltString();
		int productId = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		
		Product product = new Product(productName, productId, price);
		
		assertEquals(1, product.getQuantity());
		assertEquals(productName, product.getProductName());
		assertEquals(productId, product.getProductId());
		assertEquals(price, product.getPrice());
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
