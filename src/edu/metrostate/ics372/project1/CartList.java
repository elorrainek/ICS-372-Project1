package edu.metrostate.ics372.project1;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CartList {
	private List<Cart> listOfCarts;
	private static CartList cartList;
	
	private CartList() {
		this.listOfCarts = new ArrayList<>();
	}
	
	public static CartList instance() {
		if (cartList == null) {
			cartList = new CartList();
		}
		
		return cartList;
	}
	
	public List<Cart> getListOfCarts() {
		return this.listOfCarts;
	}
	
	public Cart search(String memberId, GregorianCalendar date) {
		Cart searchResult = null;
		
		for (Cart cart: listOfCarts) {
			if (cart.getMemberId().equals(memberId) && cart.getProcessDate().equals(date)) {
				searchResult = cart;
				break;
			}
		}
		
		return searchResult;
	}
	
	public void addNewCart(String memberId, GregorianCalendar date) {
		listOfCarts.add(new Cart(memberId, date));
	}
	
	public void addNewCart(String memberId, GregorianCalendar date, Product product) {
		listOfCarts.add(new Cart(memberId, date, product));
	}
	
	public boolean addToCart(String memberId, GregorianCalendar date, Product product) {
		Cart searchedCart = search(memberId, date);

		if (searchedCart == null) {
			addNewCart(memberId, date, product);
		} else {
			searchedCart.addToCart(product);
			listOfCarts.set(listOfCarts.indexOf(searchedCart), searchedCart);
		}
		return true;
	}
	
	public static void clearInstance() {
		cartList = null;
	}
}
