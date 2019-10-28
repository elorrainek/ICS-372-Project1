package edu.metrostate.ics372.project1;

public class Product {
	String productName;
	Integer productId;
	Double price;
	Integer quantity;
	
	public Product(String productName, Integer productId, Double price, Integer quantity) {
		this.productName = productName;
		this.productId = productId;
		this.quantity = quantity;
		this.price = (double) (Math.round(price * 100) / 100);
	}
	
	public Product(String productName, Integer productId, Double price) {
		this(productName, productId, price, 1);
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void adjustQuantity(Integer quantity) {
		this.quantity += quantity;
	}
}
