package edu.metrostate.ics372.project1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
class MemberListTest {
	private final static long SEED = 20191015001L;
	private final static Random RAND = new Random(SEED);

	@Test
	@DisplayName("it should return an instance of the MemberList object")
	void memberList_instance() {
		assertTrue(MemberList.instance() instanceof MemberList);
	}
	
	@Test
	@DisplayName("it should add a new member to the list and return true")
	void memberList_addNewMember() {
		String name = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		Member member = new Member(name, address, date, feePaid);
		
		MemberList members = MemberList.instance();
		assertTrue(members.addNewMember(member));
	}
	
	@Test
	@DisplayName("it should not add a member if they are already in the list and return false")
	void memberList_addNewMember_exists() {
		String name = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		Member member = new Member(name, address, date, feePaid);
		
		MemberList members = MemberList.instance();
		members.addNewMember(member);
		
		assertFalse(members.addNewMember(member));
	}
	
	@Test
	@DisplayName("it should return the searched member")
	void memberList_search() {
		String name = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		Member member = new Member(name, address, date, feePaid);
		
		MemberList members = MemberList.instance();
		members.addNewMember(member);
		
		assertTrue(member.equals(members.search(member.getMemberId())));
	}
	
	@Test
	@DisplayName("it should return null if the searched member does not exist")
	void memberList_search_null() {
		String name = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		Member member = new Member(name, address, date, feePaid);
		
		MemberList members = MemberList.instance();
		members.addNewMember(member);
		
		Member searchedMember = new Member(getSaltString(), getSaltString(), 
				new GregorianCalendar(), RAND.nextBoolean());
		
		assertFalse(member.equals(members.search(searchedMember.getMemberId())));
	}
	
	@Test
	@DisplayName("it should remove a member from the list and return true")
	void memberList_removeMember() {
		String name = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		Member member = new Member(name, address, date, feePaid);
		
		MemberList members = MemberList.instance();
		members.addNewMember(member);
		assertTrue(members.removeMember(member.getMemberId()));
		assertFalse(member.equals(members.search(member.getMemberId())));
	}
	
	@Test
	@DisplayName("it should return false if the member to be removed does not exist")
	void memberList_removeMember_dne() {
		String name = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		Member member = new Member(name, address, date, feePaid);
		
		MemberList members = MemberList.instance();
		members.addNewMember(member);
		
		Member removeMember = new Member(getSaltString(), getSaltString(), 
				new GregorianCalendar(), RAND.nextBoolean());
		
		assertFalse(members.removeMember(removeMember.getMemberId()));
	}
	
	@Test
	@DisplayName("it should return formatted string of all members in list")
	void memberList_getAllMembers() {
		String name1 = getSaltString();
		String address1 = getSaltString();
		GregorianCalendar date1 = new GregorianCalendar();
		boolean feePaid1 = RAND.nextBoolean();
		Member member1 = new Member(name1, address1, date1, feePaid1);
		
		String name2 = getSaltString();
		String address2 = getSaltString();
		GregorianCalendar date2 = new GregorianCalendar();
		boolean feePaid2 = RAND.nextBoolean();
		Member member2 = new Member(name2, address2, date2, feePaid2);
		
		String name3 = getSaltString();
		String address3 = getSaltString();
		GregorianCalendar date3 = new GregorianCalendar();
		boolean feePaid3 = RAND.nextBoolean();
		Member member3 = new Member(name3, address3, date3, feePaid3);
		
		MemberList.clearInstance();
		MemberList members = MemberList.instance();
		members.addNewMember(member1);
		members.addNewMember(member2);
		members.addNewMember(member3);
		
		String listOfMembers = members.getAllMembers();
		
		String expected = String.format("%s\n%s\n%s", member1.getMemberName(), 
				member2.getMemberName(), member3.getMemberName());
		
		assertEquals(expected, listOfMembers);
		
	}
	
	private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}

}
