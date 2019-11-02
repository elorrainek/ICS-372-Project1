package edu.metrostate.ics372.project1;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Cart {
	private String memberId;
	private GregorianCalendar date;
	private List<Product> cart;
	
	public Cart(String memberId, GregorianCalendar date) {
		this.memberId = memberId;
		this.date = date;
		cart = new ArrayList<>();
	}
	
	public Cart(String memberId, GregorianCalendar date, Product product) {
		this(memberId, date);
		cart.add(product);
	}
	
	public String getMemberId() {
		return this.memberId;
	}
	
	public List<Product> getItemsInCart() {
		return this.cart;
	}
	
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
	
	public double getTotal() {
		double total = 0.0;
		
		for(Product product: cart) {
			total += product.getPrice() * product.getQuantity();
		}
		
		return total;
	}
	
	public GregorianCalendar getProcessDate() {
		return this.date;
	}
	
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
