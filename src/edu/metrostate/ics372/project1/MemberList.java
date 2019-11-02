package edu.metrostate.ics372.project1;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * @author Group: Don Nguyen, Sadeq Husein, Elise Kurtz, Mohamed Abullahi, Ibsa Tilahun
 *
 *	MEMBERLIST CLASS
 */
public class MemberList implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Member> listOfMembers;
	private static MemberList memberList;

	private MemberList() {
		listOfMembers = new ArrayList<>();
	}
	
	/**
	 * MemberList instance
	 * If MemberList is equal null we should return MemberList initialized to new MemberList
	 * else we return the productList
	 * @return ProductList
	 */
	public static MemberList instance() {
		if (memberList == null) {
			return (memberList = new MemberList());
		} else {
			return memberList;
		}
	}
	
	/**
	 * 
	 * @param memberId the member id that is searched
	 * @return We return SearchResult
	 */
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
	
	/**
	 * @param member the New member Added
	 * @return We return WasSuccessful
	 * This Method addNewMember return boolean and takes and argument of Member 
	 * We assign wasSuccessfu to false and 
	 * we search for Memberid  if it is not availbale 
	 * we add member on the list of members
	 * if it is added it returns wasSuccessful
	 */
	public boolean addNewMember(Member member) {
		boolean wasSuccessful = false;
		
		if (search(member.getMemberId()) == null) {
			listOfMembers.add(member);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
	
	/**
	 * 
	 * @param memberId
	 * @return WasSuccessful
	 * This Method RemoveMember return boolean and takes and argument of String memberid
	 * then we set wasSuccesful to false
	 * We search for the member ID
	 * if the member is not equal to null
	 * then the member is removed from the list of Members
	 * then We return Was Succesful if removed
	 */
	public boolean removeMember(String memberId) {
		boolean wasSuccessful = false;
		Member member = search(memberId);
		
		if (member != null) {
			listOfMembers.remove(member);
			wasSuccessful = true;
		}
		
		return wasSuccessful;
	}
	
	/**
	 * 
	 * @return
	 * This Method  getAllmember returns String
	 * The loop goes throug all the member in the Listofmembers
	 * if the List of Members of the positon of the first occurencey is equal to list of member size minus 1
	 * Return members
	 */
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
