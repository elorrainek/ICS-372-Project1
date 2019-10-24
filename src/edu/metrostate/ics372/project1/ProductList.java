package edu.metrostate.ics372.project1;

import java.util.HashMap;
import java.util.Map;

public class ProductList {
	private Map<Integer, Product> listOfProducts;
	private static ProductList productList;
	
	private ProductList() {
		listOfProducts = new HashMap<>();
	}
	
	public static ProductList instance() {
		if (productList == null) {
			return (productList = new ProductList());
		} else {
			return productList;
		}
	}
	
	public Product search(Product product) {
		Product searchResult = null;
		int productId = product.getProductId();
		
		if (listOfProducts.containsKey(productId)) {
			searchResult = listOfProducts.get(productId);
		}
			
		return searchResult;
	}
	
	public void addNewProduct(Product product) {
		int productId = product.getProductId();
		
		if (!listOfProducts.containsKey(productId)) {
			listOfProducts.put(productId, product);
		} else {
			listOfProducts.get(productId).setQuantity(product.getQuantity());
		}
	}
	
	public boolean removeProduct(Product product) {
		boolean wasSuccessful = false;
		int productId = product.getProductId();
		
		if (listOfProducts.containsKey(productId)) {
			listOfProducts.remove(productId);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
}
