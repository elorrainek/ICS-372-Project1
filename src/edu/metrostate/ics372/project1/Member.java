package edu.metrostate.ics372.project1;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String address;
	private Date date;
	private static final String MEMBER_STRING = "M";

	public Member(String name, String address, Date date, float feePaid) {
		this.name = name;
		this.address = address;
		this.date = date;
		id = MEMBER_STRING + (MemberIdServer.instance()).getId();
	}

	public String getMemberName() {
		return this.name;
	}

	public void setMemberName(String name) {
		this.name = name;		
	}

	public String getMemberAddress() {
		return this.address;
	}

	public void setMemberAddress(String address) {
		this.address = address;
	}

	public String getMemberPhoneNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMemberPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		
	}

	public Date getMemberJoinDate() {
		return this.date;
	}

	public void setMemberJoinDate(Date date) {
		this.date = date;
	}

	public String getMemberId() {
		return id;
	}

	public boolean getFeePaid() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setFeePaid(float amount) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean equals(String id) {
		return this.id.equals(id);
	}
	
	@Override
	public String toString() {
		return String.format("%s\t%s\t%s\t%s", id, name, address, date);
	}

}
