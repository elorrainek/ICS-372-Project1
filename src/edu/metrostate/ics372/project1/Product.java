package edu.metrostate.ics372.project1;

import java.io.Serializable;

/**
 * 
 * @author Group: Don Nguyen, Sadeq Husein, Elise Kurtz, Mohamed Abullahi, Ibsa Tilahun
 *         
 *		PRODUCT CLASS
 */
public class Product implements Serializable{
	/**
	 * Fields
	 * */
	private static final long serialVersionUID = 1L;
	String productName;
	Integer productId;
	Double price;
	Integer quantity;
	
	/**
	 * Product Constructor.
	 * @param productName
	 * @param productId
	 * @param price
	 * @param quantity
	 */
	public Product(String productName, Integer productId, Double price, Integer quantity) {
		this.productName = productName;
		this.productId = productId;
		this.quantity = quantity;
		this.price = (double) (Math.round(price * 100) / 100);
	}
	
	/**
	 * Product 
	 * @param productName
	 * @param productId
	 * @param price
	 */
	public Product(String productName, Integer productId, Double price) {
		this(productName, productId, price, 1);
	}
	
	/**
	 * Getter for the Product Name
	 * @return
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Setter for Product Name
	 * @param productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Getter for product id.
	 * @return productId.
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * Setter for the product id. 
	 * @param productId
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * Getter for the price.
	 * @return price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Setter for the price
	 * @param price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Getter for the quantity. 
	 * @return quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Adjustment for the Quantity
	 * @param quantity
	 */
	public void adjustQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Equals method 
	 * @param o
	 * @return boolean isTrue if the conditions are met.
	 */
	@Override
	public boolean equals(Object o) {
		boolean isTrue = false;
		
		if (o instanceof Product) {
			if (productId == ((Product) o).getProductId()) {
				isTrue = true;
			}
		}
		
		return isTrue;
	}
	
	@Override
	public String toString() {
		return String.format("%s\t\t\t%s\t\t$%s", productName, quantity, price);
	}
}
