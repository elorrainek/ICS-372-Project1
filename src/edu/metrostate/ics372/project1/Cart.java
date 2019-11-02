package edu.metrostate.ics372.project1;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * @author Group: Don Nguyen, Sadeq Husein, Elise Kurtz, Mohamed Abullahi, Ibsa Tilahun
 *
 *	CART CLASS 
 */
public class Cart {
	/**
	 * Fields
	 * */
	private String memberId;
	private GregorianCalendar date;
	private List<Product> cart;
	
	/**
	 * Cart constructor
	 * @param memberId
	 * @param date
	 */
	public Cart(String memberId, GregorianCalendar date) {
		this.memberId = memberId;
		this.date = date;
		cart = new ArrayList<>();
	}
	
	/**
	 * Cart constructor
	 * @param memberId
	 * @param date
	 * @param product
	 */
	public Cart(String memberId, GregorianCalendar date, Product product) {
		this(memberId, date);
		cart.add(product);
	}
	
	/**
	 * Getter for the member Id
	 * @return
	 */
	public String getMemberId() {
		return this.memberId;
	}
	
	/**
	 * Getter for the items in the Cart.
	 * @return cart
	 */
	public List<Product> getItemsInCart() {
		return this.cart;
	}
	
	/**
	 * Method to add the product to the cart.
	 * @param product
	 */
	public void addToCart(Product product) {
		if(cart.contains(product) ) {
			Product temp = cart.get(cart.indexOf(product));
			cart.set(cart.indexOf(product), new Product(product.getProductName(),
					product.getProductId(), product.getPrice(), product.getQuantity() + 
					temp.getQuantity()));
		} else {
			cart.add(product);
		}
	}
	
	/**
	 * Getter of cart total.
	 * @return total
	 */
	public double getTotal() {
		double total = 0.0;
		
		for(Product product: cart) {
			total += product.getPrice() * product.getQuantity();
		}
		
		return total;
	}
	
	/**
	 * Getter for the process date.
	 * @return the process date
	 */
	public GregorianCalendar getProcessDate() {
		return this.date;
	}
	
	/**
	 * Equals method.
	 * @param o
	 * @return boolean isTrue if the conditions are met. 
	 */
	@Override
	public boolean equals(Object o) {
		boolean isTrue = false;
		
		if (o instanceof Cart) {
			if (memberId.equals(((Cart)o).getMemberId()) && 
					date.equals(((Cart)o).getProcessDate())) {
				isTrue = true;
			}
		}
		
		return isTrue;
	}
	
	@Override
	public String toString() {
		return String.format("MemberID: %s\nCart: %s", memberId, cart);
	}
}
