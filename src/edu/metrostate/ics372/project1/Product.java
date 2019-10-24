package edu.metrostate.ics372.project1;

public class Product implements ProductInterface {
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
	
	@Override
	public String getProductName() {
		return productName;
	}

	@Override
	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public Integer getProductId() {
		return productId;
	}

	@Override
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Override
	public Double getPrice() {
		return price;
	}

	@Override
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
