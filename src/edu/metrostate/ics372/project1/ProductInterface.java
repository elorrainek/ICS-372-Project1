package edu.metrostate.ics372.project1;

/**
 * @author Don Nguyen
 * 
 * The Product interface to be used for grocery store collection class. It contains all the methods
 * to be implemented by a product.
 *
 */
public interface ProductInterface {
	
	/**
	 * Return the product name
	 * @return String value of the product name
	 */
	public String getProductName();
	
	/**
	 * Set the product name
	 * @param String value of the new product name
	 */
	public void setProductName(String productName);
	
	/**
	 * Return the product id
	 * @return Integer value of the product id
	 */
	public Integer getProductId();
	
	/**
	 * Set the product ID
	 * @param Integer value of the new product id
	 */
	public void setProductId(Integer productId);
	
	/**
	 * Return the price of the product
	 * @return Double value of the product price
	 */
	public Double getPrice();
	
	/**
	 * Set the new product price
	 * @param Double value of the new product price
	 */
	public void setPrice(Double price);
	
	/**
	 * Return the product quantity
	 * @return Integer value of the product quantity
	 */
	public Integer getQuantity();
	
	/**
	 * Set the new product quantity
	 * @param Integer value of the new product quantity
	 */
	public void setQuantity(Integer quantity);
}
