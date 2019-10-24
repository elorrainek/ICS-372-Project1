package edu.metrostate.ics372.project1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
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
		Date date = new Date();
		float feePaid = RAND.nextFloat();
		Member member = new Member(name, address, date, feePaid);
		
		MemberList members = MemberList.instance();
		assertTrue(members.addNewMember(member));
	}
	
	@Test
	@DisplayName("it should not add a member if they are already in the list and return false")
	void memberList_addNewMember_exists() {
		String name = getSaltString();
		String address = getSaltString();
		Date date = new Date();
		float feePaid = RAND.nextFloat();
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
		Date date = new Date();
		float feePaid = RAND.nextFloat();
		Member member = new Member(name, address, date, feePaid);
		
		MemberList members = MemberList.instance();
		members.addNewMember(member);
		
		assertTrue(member.equals(members.search(member)));
	}
	
	@Test
	@DisplayName("it should return null if the searched member does not exist")
	void memberList_search_null() {
		String name = getSaltString();
		String address = getSaltString();
		Date date = new Date();
		float feePaid = RAND.nextFloat();
		Member member = new Member(name, address, date, feePaid);
		
		MemberList members = MemberList.instance();
		members.addNewMember(member);
		
		assertFalse(member.equals(members.search(
				new Member(getSaltString(), getSaltString(), new Date(), RAND.nextFloat()))));
	}
	
	@Test
	@DisplayName("it should remove a member from the list and return true")
	void memberList_removeMember() {
		String name = getSaltString();
		String address = getSaltString();
		Date date = new Date();
		float feePaid = RAND.nextFloat();
		Member member = new Member(name, address, date, feePaid);
		
		MemberList members = MemberList.instance();
		members.addNewMember(member);
		assertTrue(members.removeMember(member));
		assertFalse(member.equals(members.search(member)));
	}
	
	@Test
	@DisplayName("it should return false if the member to be removed does not exist")
	void memberList_removeMember_dne() {
		String name = getSaltString();
		String address = getSaltString();
		Date date = new Date();
		float feePaid = RAND.nextFloat();
		Member member = new Member(name, address, date, feePaid);
		
		MemberList members = MemberList.instance();
		members.addNewMember(member);
		
		assertFalse(members.removeMember(
				new Member(getSaltString(), getSaltString(), new Date(), RAND.nextFloat())));
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
