package edu.metrostate.ics372.project1;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * @author Group: Don Nguyen, Sadeq Husein, Elise Kurtz, Mohamed Abullahi, Ibsa Tilahun
 *         
 *		CartList collection class
 */
public class CartList {
	private List<Cart> listOfCarts;
	private static CartList cartList;
	
	/**
	 * CartList constructor
	 */
	private CartList() {
		this.listOfCarts = new ArrayList<>();
	}
	
	/**
	 * If cartList is equal to null 
	 * We created new CartList
	 * else we return cartList
	 * @return CartList
	 */
	public static CartList instance() {
		if (cartList == null) {
			cartList = new CartList();
		}
		
		return cartList;
	}
	
	/**
	 * 
	 * @return List of Cart objects
	 */
	public List<Cart> getListOfCarts() {
		return this.listOfCarts;
	}
	
	/**
	 * Search through listOfCarts to find the member's cart
	 * @param memberId
	 * @param date
	 * @return
	 */
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
	
	/**
	 * AddNewCart return Void
	 * Parameters memberid and date
	 * @param memberId
	 * @param date
	 */
	public void addNewCart(String memberId, GregorianCalendar date) {
		listOfCarts.add(new Cart(memberId, date));
	}
	
	/**
	 * AddNewCart return Void
	 * @param memberId
	 * @param date
	 * @param product
	 */
	public void addNewCart(String memberId, GregorianCalendar date, Product product) {
		listOfCarts.add(new Cart(memberId, date, product));
	}
	
	/**
	 * This Method addtCart returns Boolean 
	 * Arguments MemberId, date, product
	 * If searchCart is equl to null
	 * we add new a cart we get memberid and date
	 * @param memberId 
	 * @param date
	 * @param product
	 * @return true
	 */
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
