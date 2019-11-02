package edu.metrostate.ics372.project1;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * 
 * @author Group: Don Nguyen, Sadeq Husein, Elise Kurtz, Mohamed Abullahi, Ibsa Tilahun
 * 
 *     MEMBER CLASS
 *
 */
public class Member implements Serializable {
	/**
	 * Fields
	 * */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String address;
	private GregorianCalendar date;
	private static final String MEMBER_STRING = "M";
	private boolean feePaid;

	/**
	 * Member Constructor.
	 * 
	 * @param name
	 * @param phoneNumber
	 * @param address
	 * @param date
	 * @param feePaid
	 */
	public Member(String name, String address, GregorianCalendar GregorianCalendar, 
			boolean feePaid) {
		this.name = name;
		this.address = address;
		this.date = GregorianCalendar;
		id = MEMBER_STRING + (MemberIdServer.instance()).getId();
		this.feePaid = feePaid;
	}

	/**
	 * Getter for the Member name.
	 * 
	 * @return the member name
	 */
	public String getMemberName() {
		return this.name;
	}

	/**
	 * Setter for the Member name.
	 * 
	 * @param name
	 */
	public void setMemberName(String name) {
		this.name = name;		
	}

	/**
	 * Getter for the member address.
	 * 
	 * @return the member address
	 */
	public String getMemberAddress() {
		return this.address;
	}

	/**
	 * Setter for the member address.
	 * 
	 * @param address
	 */
	public void setMemberAddress(String address) {
		this.address = address;
	}

	/**
	 * Getter for the member phone number.
	 * 
	 * @return the member phone number
	 */
	public String getMemberPhoneNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Setter for the member phone number.
	 * 
	 * @param phoneNumber
	 */
	public void setMemberPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Getter for the member join date.
	 * 
	 * @return the join date
	 */
	public GregorianCalendar getMemberJoinDate() {
		return this.date;
	}

	/**
	 * Setter for the member join date.
	 * 
	 * @param date
	 */
	public void setMemberJoinDate(GregorianCalendar date) {
		this.date = date;
	}

	/**
	 * Getter for the Member Id.
	 * 
	 * @return member id
	 */
	public String getMemberId() {
		return id;
	}

	/**
	 * Getter for the fee paid.
	 * 
	 * @return the fee paid
	 */
	public boolean getFeePaid() {
		return feePaid;
	}

	/**
	 * Setter for the fee paid.
	 * 
	 * @param feePaid
	 */
	public void setFeePaid(boolean feePaid) {
		this.feePaid = feePaid;
		
	}
	
	/**
	 * Equals method.
	 * 
	 * @param id
	 * @return equals method of if the id in the field equals the id in the
	 *         parameter.
	 */
	public boolean equals(String id) {
		return this.id.equals(id);
	}
	
	/**
	 * ToString
	 */
	@Override
	public String toString() {
		return String.format("%s\t%s\t%s\t%s", id, name, address, date);
	}

}
