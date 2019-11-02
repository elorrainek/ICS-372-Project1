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
	@DisplayName("it should return the searched product when searching for the product id")
	void productList_search_productId() {
		String productName = getSaltString();
		Integer productId = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity = randIntBetween(100, 10_000);
		Product product4 = new Product(productName, productId, price, quantity);
		
		ProductList.clearInstance();
		ProductList productList = ProductList.instance();
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(productName, productId, price, quantity);
		
		assertTrue(product4.equals(productList.search(productId)));
	}
	
	@Test
	@DisplayName("it should return the searched product when searching for the product name")
	void productList_search_productName() {
		String productName = getSaltString();
		Integer productId = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity = randIntBetween(100, 10_000);
		Product product5 = new Product(productName, productId, price, quantity);
		
		ProductList.clearInstance();
		ProductList productList = ProductList.instance();
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(productName, productId, price, quantity);
		
		assertTrue(product5.equals(productList.search(productName)));
	}
	
	@Test
	@DisplayName("it should return null searched product does not exists in the list")
	void productList_search_productId_null() {
		ProductList productList = ProductList.instance();
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		Product product5 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		Product searchedProduct = new Product(getSaltString(), 
				randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		assertFalse(product5.equals(productList.search(searchedProduct.getProductId())));
	}
	
	@Test
	@DisplayName("it should return null searched product does not exists in the list")
	void productList_search_productName_null() {
		Product product4 = new Product(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		ProductList productList = ProductList.instance();
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		Product searchedProduct = new Product(getSaltString(), 
				randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		assertFalse(product4.equals(productList.search(searchedProduct.getProductName())));
	}
	
	@Test
	@DisplayName("it should add a new product to the list and if it does not already exist")
	void productList_addProduct() {
		String productName = getSaltString();
		Integer productId = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity = randIntBetween(100, 10_000);
		Product product5 = new Product(productName, productId, price, quantity);
		
		ProductList.clearInstance();
		ProductList productList = ProductList.instance();
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		assertNull(productList.search(product5.getProductId()));
		productList.addNewProduct(productName, productId, price, quantity);
		assertTrue(product5.equals(productList.search(productId)));
	}
	
	@Test
	@DisplayName("it should increment product quantity if product already exists in list")
	void productList_addProduct_exists() {
		String productName = getSaltString();
		Integer productId = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		
		ProductList.clearInstance();
		ProductList productList = ProductList.instance();
		
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100));
		productList.addNewProduct(productName, productId, price, 1);
		productList.addNewProduct(productName, productId, price, 1);
		
		assertEquals(2, productList.search(productId).getQuantity());
		assertEquals(5, productList.getAllProducts().size());
	}
	
	@Test
	@DisplayName("it should remove a product from the list")
	void productList_removeProduct() {
		String productName = getSaltString();
		Integer productId = randIntBetween(100, 10_000);
		Double price = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity = randIntBetween(100, 10_000);
		Product product4 = new Product(productName, productId, price, quantity);
		
		ProductList.clearInstance();
		ProductList productList = ProductList.instance();
		
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(productName, productId, price, quantity);
		
		assertTrue(productList.removeProduct(product4));
		assertNull(productList.search(productId));
	}
	
	@Test
	@DisplayName("it should not remove a product if it is not in the list")
	void productList_removeProduct_dne() {
		ProductList productList = ProductList.instance();
		
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		productList.addNewProduct(getSaltString(), randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000));
		
		assertFalse(productList.removeProduct(new Product(getSaltString(), 
				randIntBetween(100, 10_000), 
				(double) (Math.round(RAND.nextDouble() * 100) / 100), 
				randIntBetween(100, 10_000))));
	}
	
	@Test
	@DisplayName("it should return all products")
	void productList_getAllProducts() {
		String productName1 = getSaltString();
		Integer productId1 = randIntBetween(100, 10_000);
		Double price1 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity1 = randIntBetween(100, 10_000);
		Product p1 = new Product(productName1, productId1, price1, quantity1);
		
		String productName2 = getSaltString();
		Integer productId2 = randIntBetween(100, 10_000);
		Double price2 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity2 = randIntBetween(100, 10_000);
		Product p2 = new Product(productName2, productId2, price2, quantity2);
		
		String productName3 = getSaltString();
		Integer productId3 = randIntBetween(100, 10_000);
		Double price3 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity3 = randIntBetween(100, 10_000);
		Product p3 = new Product(productName3, productId3, price3, quantity3);
		
		String productName4 = getSaltString();
		Integer productId4 = randIntBetween(100, 10_000);
		Double price4 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity4 = randIntBetween(100, 10_000);
		Product p4 = new Product(productName4, productId4, price4, quantity4);
		
		ProductList.clearInstance();
		ProductList productList = ProductList.instance();
		productList.addNewProduct(productName1, productId1, price1, quantity1);
		productList.addNewProduct(productName2, productId2, price2, quantity2);
		productList.addNewProduct(productName3, productId3, price3, quantity3);
		productList.addNewProduct(productName4, productId4, price4, quantity4);
		
		assertEquals(4, productList.getAllProducts().size());
		assertTrue(productList.getAllProducts().contains(p1));
		assertTrue(productList.getAllProducts().contains(p2));
		assertTrue(productList.getAllProducts().contains(p3));
		assertTrue(productList.getAllProducts().contains(p4));
	}
	
	@Test
	@DisplayName("it should adjust the product quantity")
	void productList_adjustQuantity() {
		String productName1 = getSaltString();
		Integer productId1 = randIntBetween(100, 10_000);
		Double price1 = (double) (Math.round(RAND.nextDouble() * 100) / 100);
		Integer quantity1 = randIntBetween(100, 10_000);
		Product p1 = new Product(productName1, productId1, price1, quantity1);
		
		ProductList.clearInstance();
		ProductList productList = ProductList.instance();
		productList.addNewProduct(productName1, productId1, price1, quantity1);
		productList.adjustQuantity(p1, 2);
		
		assertEquals(quantity1 - 2, productList.search(productId1).getQuantity());
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
