package edu.metrostate.ics372.project1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cart {
	private String memberId;
	private Date date;
	private List<Product> cart;
	
	public Cart(String memberId, Date date) {
		this.memberId = memberId;
		this.date = date;
		cart = new ArrayList<>();
	}
	
	public String getMemberId() {
		return this.memberId;
	}
	
	public List<Product> getItemsInCart() {
		return this.cart;
	}
	
	public void addToCart(Product product) {
		cart.add(product);
	}
	
	public double getTotal() {
		double total = 0.0;
		
		for(Product product: cart) {
			total += product.getPrice() * product.getQuantity();
		}
		
		return total;
	}
	
	public Date getProcessDate() {
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
}
