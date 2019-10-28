package edu.metrostate.ics372.project1;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


public class MemberList implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Member> listOfMembers;
	private static MemberList memberList;

	private MemberList() {
		listOfMembers = new ArrayList<>();
	}
	
	public static MemberList instance() {
		if (memberList == null) {
			return (memberList = new MemberList());
		} else {
			return memberList;
		}
	}
	
	public Member search(Member member) {
		Member searchResult = null;
		String searchedMemberId = member.getMemberId();
		
		for(Member individual: listOfMembers) {
			if (searchedMemberId.equals(individual.getMemberId())) {
				searchResult = individual;
				break;
			}
		}
		
		return searchResult;
	}
	
	public boolean addNewMember(Member member) {
		boolean wasSuccessful = false;
		
		if (search(member) == null) {
			listOfMembers.add(member);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
	
	public boolean removeMember(Member member) {
		boolean wasSuccessful = false;
		
		if (search(member) != null) {
			listOfMembers.remove(member);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
	
	public String print() {
		//TODO: print all members in list
		return null;
	}
}
