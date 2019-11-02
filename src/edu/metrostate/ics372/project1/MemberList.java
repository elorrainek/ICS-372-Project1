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
	
	public Member search(String memberId) {
		Member searchResult = null;
		
		for(Member individual: listOfMembers) {
			if (memberId.equals(individual.getMemberId())) {
				searchResult = individual;
				break;
			}
		}
		
		return searchResult;
	}
	
	public boolean addNewMember(Member member) {
		boolean wasSuccessful = false;
		
		if (search(member.getMemberId()) == null) {
			listOfMembers.add(member);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
	
	public boolean removeMember(String memberId) {
		boolean wasSuccessful = false;
		Member member = search(memberId);
		
		if (member != null) {
			listOfMembers.remove(member);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
	
	public String getAllMembers() {
		String members = "";
		
		for(Member member: listOfMembers) {
			if (listOfMembers.indexOf(member) == listOfMembers.size() - 1) {
				members += member.getMemberName();
			} else {
				members += member.getMemberName() + "\n";
			}
		}
		
		return members;
	}
	
	public static void clearInstance() {
		memberList = null;
	}
}
