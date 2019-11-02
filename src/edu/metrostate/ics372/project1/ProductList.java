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
	
	public Product search(int productId) {
		Product searchResult = null;
		for(Product item: listOfProducts) {
			if (productId == item.getProductId()) {
				searchResult = item;
				break;
			}
		}
			
		return searchResult;
	}
	
	public Product search(String productName) {
		Product searchResult = null;
		for(Product item: listOfProducts) {
			if (productName.equals(item.getProductName())) {
				searchResult = item;
			}
		}
			
		return searchResult;
	}
	
	public boolean addNewProduct(String productName, Integer productId, Double price) {
		Product existingProduct = null;
		Product searchedProduct = search(productId);
		
		if (searchedProduct == null) {
			listOfProducts.add(new Product(productName, productId, price));
		} else {
			existingProduct = new Product(searchedProduct.getProductName(), 
					searchedProduct.getProductId(), searchedProduct.getPrice());
			listOfProducts.remove(searchedProduct);
			listOfProducts.add(existingProduct);
		}
		
		return true;
	}
	
	public boolean addNewProduct(String productName, Integer productId, Double price, 
			Integer quantity) {
		Product searchedProduct = search(productId);
		
		if (searchedProduct == null) {
			listOfProducts.add(new Product(productName, productId, price));
		} else {
			listOfProducts.set(listOfProducts.indexOf(searchedProduct),
					new Product(searchedProduct.getProductName(), 
							searchedProduct.getProductId(), searchedProduct.getPrice(), 
							searchedProduct.getQuantity() + quantity));
		}
		
		return true;
	}
	
	public List<Product> getAllProducts() {
		return this.listOfProducts;
	}
	
	public boolean adjustProductPrice(Integer productId, double price) {
		boolean wasSuccessful = false;
		Product searchedProduct = search(productId);
		
		if (searchedProduct != null) {
			searchedProduct.setPrice(price);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
	
	public void adjustQuantity(Product product, Integer itemsRemoved) {
		listOfProducts.set(listOfProducts.indexOf(product),
				new Product(product.getProductName(), product.getProductId(),
						product.getPrice(), product.getQuantity() - itemsRemoved));
	}
	
	public static void clearInstance() {
		productList = null;
	}
	
	public boolean removeProduct(Product product) {
		boolean wasSuccessful = false;
		
		if (search(product.getProductId()) != null) {
			listOfProducts.remove(product);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
}
