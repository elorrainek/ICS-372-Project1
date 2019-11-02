package edu.metrostate.ics372.project1;

import java.util.List;
import java.util.ArrayList;

public class ProductList {
	private List<Product> listOfProducts;
	private final int lowInventory = 5;
	private List<Product> lowInventoryList;
	private static ProductList productList;
	
	private ProductList() {
		listOfProducts = new ArrayList<>();
		lowInventoryList = new ArrayList<>();
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
		Product searchedProduct = search(productId);
		
		if (searchedProduct == null) {
			listOfProducts.add(new Product(productName, productId, price));
		} else {
			searchedProduct.adjustQuantity(searchedProduct.getQuantity() + 1);
			listOfProducts.set(listOfProducts.indexOf(searchedProduct), searchedProduct);
		}
		
		return true;
	}
	
	public boolean addNewProduct(String productName, Integer productId, Double price, 
			Integer quantity) {
		Product searchedProduct = search(productId);
		if (searchedProduct == null) {
			listOfProducts.add(new Product(productName, productId, price, quantity));
		} else {
			searchedProduct.adjustQuantity(searchedProduct.getQuantity() + quantity);
			listOfProducts.set(listOfProducts.indexOf(searchedProduct), searchedProduct);
		}
		
		return true;
	}
	
	public List<Product> getAllProducts() {
		return this.listOfProducts;
	}
	
	public List<Product> getLowInventoryItems() {
		return this.lowInventoryList;
	}
	
	public void addToLowInventoryItemsList(Product product) {
		if (lowInventoryList.isEmpty() || 
				lowInventoryList.get(lowInventoryList.indexOf(product)) == null) {
			lowInventoryList.add(product);
		}
	}
	
	public void removeFromLowInventoryList(Product product) {
		if (!lowInventoryList.isEmpty() && 
				lowInventoryList.get(lowInventoryList.indexOf(product)) != null) {
			lowInventoryList.remove(product);
		}
	}
	
	public boolean checkLowInventory(Product product) {
		return product.getQuantity() <= lowInventory;
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
