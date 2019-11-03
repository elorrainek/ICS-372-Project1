package edu.metrostate.ics372.project1;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class ProductList implements Serializable {
	/**
	 * Fields
	 */
	private static final long serialVersionUID = 1L;
	private List<Product> listOfProducts;
	private final int lowInventory = 5;
	private List<Product> lowInventoryList;
	private static ProductList productList;
	
	/**
	 *We create listofproducts and then
	 *initialize to new ArrayList
	 */
	private ProductList() {
		listOfProducts = new ArrayList<>();
		lowInventoryList = new ArrayList<>();
	}
	
	/**
	 * ProductList instance
	 * If ProductList is equal null we should return productListis equal to new Productlist
	 * else we return the productList
	 * @return ProductList
	 */
	public static ProductList instance() {
		if (productList == null) {
			return (productList = new ProductList());
		} else {
			return productList;
		}
	}
	
	/**
	 * 
	 * @param productId the product id we need to search
	 * @return searchResult 
	 */
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
	
	/**
	 * 
	 * @param productName Product Name Searched
	 * @return search Result
	 */
	public Product search(String productName) {
		Product searchResult = null;
		for(Product item: listOfProducts) {
			if (productName.equals(item.getProductName())) {
				searchResult = item;
			}
		}
			
		return searchResult;
	}
	
	/**
	 * 
	 * @param productName Product name added on the Cart
	 * @param productId Product Id added on the Cart
	 * @param price Price of the Product added
	 * @return true
	 */
	public boolean addNewProduct(Integer productId, Integer quantity) {
		boolean wasSuccessful = false;
		Product searchedProduct = search(productId);
		
		if (searchedProduct != null) {
			searchedProduct.adjustQuantity(searchedProduct.getQuantity() + quantity);
			listOfProducts.set(listOfProducts.indexOf(searchedProduct), searchedProduct);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
	
	/**
	 * 
	 * @param productName Product name added on the Cart
	 * @param productId Product Id added on the Cart
	 * @param price Price of the Product added
	 * @return true
	 */
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
	
	/**
	 * 
	 * @param productName Product name added on the Cart
	 * @param productId Product Id added on the Cart
	 * @param price Price of the Product added
	 * @param quantity Quantity of the product added
	 * @return true
	 */
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
	
	/**
	 * In this Method we get all the Products in the product List
	 * If searchedproduct is not equal to null searched product is set to a price
	 * if it was successufully returns true
	 * @return true.
	 */
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
	
	/**
	 * 
	 * @param product Product that its quantity is adjusted
	 * @param itemsRemoved 
	 */
	public void adjustQuantity(Product product, Integer itemsRemoved) {
		listOfProducts.set(listOfProducts.indexOf(product),
				new Product(product.getProductName(), product.getProductId(),
						product.getPrice(), product.getQuantity() - itemsRemoved));
	}
	
	protected static void clearInstance() {
		productList = null;
	}
	
	/**
	 * 
	 * @param Product Removed Product
	 * @return was Successful
	 */ 
	public boolean removeProduct(Product product) {
		boolean wasSuccessful = false;
		
		if (search(product.getProductId()) != null) {
			listOfProducts.remove(product);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
}
