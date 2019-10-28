package edu.metrostate.ics372.project1;

import java.util.List;
import java.util.ArrayList;

public class ProductList {
	private List<Product> listOfProducts;
//	private Map<Integer, Product> listOfProducts;
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
		int productId = product.getProductId();
		Product existingProduct = null;
		int index = -1;
		boolean productExists = false;
		
		for(Product item: listOfProducts) {
			if (productId == item.getProductId()) {
				productExists = true;
				index = listOfProducts.indexOf(item);
				existingProduct = new Product(item.getProductName(), item.getProductId(), 
						item.getPrice(), item.getQuantity() + product.getQuantity());
				break;
			}
		}
		
		if (!productExists) {
			listOfProducts.add(product);
		} else {
			listOfProducts.remove(index);
			listOfProducts.add(index, existingProduct);
		}
	}
	
	public boolean removeProduct(Product product) {
		boolean wasSuccessful = false;
		int productId = product.getProductId();
		int index = -1;
		
		for (Product item: listOfProducts) {
			if (productId == item.getProductId()) {
				index = listOfProducts.indexOf(item);
				listOfProducts.remove(index);
				wasSuccessful = true;
				break;
			}
		}
		
		return wasSuccessful;
	}
}
