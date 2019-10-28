package edu.metrostate.ics372.project1;

import java.util.List;
import java.util.ArrayList;

public class ProductList {
	private List<Product> listOfProducts;
	private static ProductList productList;
	
	private ProductList() {
		listOfProducts = new ArrayList<>();
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
		
		for(Product item: listOfProducts) {
			if (productId == item.getProductId()) {
				searchResult = item;
			}
		}
			
		return searchResult;
	}
	
	public void addNewProduct(Product product) {
		Product existingProduct = null;
		Product searchedProduct = search(product);
		
		if (search(product) == null) {
			listOfProducts.add(product);
		} else {
			existingProduct = new Product(searchedProduct.getProductName(), 
					searchedProduct.getProductId(), searchedProduct.getPrice(), 
					searchedProduct.getQuantity() + product.getQuantity());
			listOfProducts.remove(searchedProduct);
			listOfProducts.add(existingProduct);
		}
	}
	
	public boolean removeProduct(Product product) {
		boolean wasSuccessful = false;
		
		if (search(product) != null) {
			listOfProducts.remove(product);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
}
