package edu.metrostate.ics372.project1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class ProductListTest {
	private final static long SEED = 20191015001L;
	private final static Random RAND = new Random(SEED);

	@Test
	@DisplayName("it should return an instance of the ProductList object")
	void productList_instance() {
		assertTrue(ProductList.instance() instanceof ProductList);
	}
	
	@Test
	@DisplayName("it should return the searched product if it exists in the list")
	void productList_search() {
		Product product1 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product2 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product3 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product4 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		ProductList productList = ProductList.instance();
		productList.addNewProduct(product1);
		productList.addNewProduct(product2);
		productList.addNewProduct(product3);
		productList.addNewProduct(product4);
		
		assertTrue(product4.equals(productList.search(product4)));
	}
	
	@Test
	@DisplayName("it should return null searched product does not exists in the list")
	void productList_search_null() {
		Product product1 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product2 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product3 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product4 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		ProductList productList = ProductList.instance();
		productList.addNewProduct(product1);
		productList.addNewProduct(product2);
		productList.addNewProduct(product3);
		productList.addNewProduct(product4);
		
		assertFalse(product4.equals(productList.search(new Product(getSaltString(), 
				randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000)))));
	}
	
	@Test
	@DisplayName("it should add a new product to the list and if it does not already exist")
	void productList_addProduct() {
		Product product1 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product2 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product3 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product4 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product5 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		ProductList productList = ProductList.instance();
		productList.addNewProduct(product1);
		productList.addNewProduct(product2);
		productList.addNewProduct(product3);
		productList.addNewProduct(product4);
		
		assertNull(productList.search(product5));
		productList.addNewProduct(product5);
		assertTrue(product5.equals(productList.search(product5)));
	}
	
	@Test
	@DisplayName("it should increment product quantity if product already exists in list")
	void productList_addProduct_exists() {
		Product product1 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product2 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product3 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product4 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				1);
		
		ProductList productList = ProductList.instance();
		
		productList.addNewProduct(product1);
		productList.addNewProduct(product2);
		productList.addNewProduct(product3);
		productList.addNewProduct(product4);
		productList.addNewProduct(product4);
		
		assertEquals(2, productList.search(product4).getQuantity());
	}
	
	@Test
	@DisplayName("it should remove a product from the list and return true")
	void productList_removeProduct() {
		Product product1 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product2 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product3 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product4 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		ProductList productList = ProductList.instance();
		
		productList.addNewProduct(product1);
		productList.addNewProduct(product2);
		productList.addNewProduct(product3);
		productList.addNewProduct(product4);
		
		assertTrue(productList.removeProduct(product1));
		assertNull(productList.search(product1));
	}
	
	@Test
	@DisplayName("it should not remove a product if it is not in the list and return false")
	void productList_removeProduct_dne() {
		Product product1 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product2 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product3 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		Product product4 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		ProductList productList = ProductList.instance();
		
		productList.addNewProduct(product1);
		productList.addNewProduct(product2);
		productList.addNewProduct(product3);
		productList.addNewProduct(product4);
		
		assertFalse(productList.removeProduct(new Product(getSaltString(), 
				randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000))));
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
