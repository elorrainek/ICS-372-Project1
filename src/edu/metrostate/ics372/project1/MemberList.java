package edu.metrostate.ics372.project1;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MemberList implements Serializable{
	private static final long serialVersionUID = 1L;
	private Map<String, Member> listOfMembers;
	private static MemberList memberList;

	private MemberList() {
		listOfMembers = new HashMap<>();
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
		
		if (listOfMembers.containsKey(searchedMemberId)) {
			searchResult = listOfMembers.get(searchedMemberId);
		}
		
		return searchResult;
	}
	
	public boolean addNewMember(Member member) {
		boolean wasSuccessful = false;
		String memberId = member.getMemberId();
		
		if (!listOfMembers.containsKey(memberId)) {
			listOfMembers.put(memberId, member);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
	
	public boolean removeMember(Member member) {
		boolean wasSuccessful = false;
		String memberId = member.getMemberId();
		
		if (listOfMembers.containsKey(memberId)) {
			listOfMembers.remove(memberId);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
	
	public String print() {
		//TODO: print all members in list
		return null;
	}
}
