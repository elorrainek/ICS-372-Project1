package edu.metrostate.ics372.project1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class MemberTest {
	private final static long SEED = 20191015001L;
	private final static Random RAND = new Random(SEED);

	@Test
	@DisplayName("it should return the member name")
	void member_getMemberName() {
		String memberName = getSaltString();
		String address = getSaltString();
		Date date = new Date();
		float fee = RAND.nextFloat();
		
		Member member = new Member(memberName, address, date, fee);
		
		assertEquals(memberName, member.getMemberName());
	}
	
	@Test
	@DisplayName("it should alter the member name")
	void member_setMemberName() {
		String memberName = getSaltString();
		String address = getSaltString();
		Date date = new Date();
		float fee = RAND.nextFloat();
		
		String alteredName = getSaltString();
		
		Member member = new Member(memberName, address, date, fee);
		member.setMemberName(alteredName);
		
		assertEquals(alteredName, member.getMemberName());
	}
	
	@Test
	@DisplayName("it should return the member address")
	void member_getMemberAddress() {
		String memberName = getSaltString();
		String address = getSaltString();
		Date date = new Date();
		float fee = RAND.nextFloat();
		
		Member member = new Member(memberName, address, date, fee);
		
		assertEquals(address, member.getMemberAddress());
	}
	
	@Test
	@DisplayName("it should alter the member address")
	void member_setMemberAddress() {
		String memberName = getSaltString();
		String address = getSaltString();
		Date date = new Date();
		float fee = RAND.nextFloat();
		
		String alteredAddress = getSaltString();
		
		Member member = new Member(memberName, address, date, fee);
		member.setMemberAddress(alteredAddress);
		
		assertEquals(alteredAddress, member.getMemberAddress());
	}
	
	@Test
	@DisplayName("it should return the member join date")
	void member_getMemberJoinDate() {
		String memberName = getSaltString();
		String address = getSaltString();
		Date date = new Date();
		float fee = RAND.nextFloat();
		
		Member member = new Member(memberName, address, date, fee);
		
		assertEquals(date, member.getMemberJoinDate());
	}
	
	@Test
	@DisplayName("it should alter the member join date")
	void member_setMemberJoinDate() {
		String memberName = getSaltString();
		String address = getSaltString();
		Date date = new Date();
		float fee = RAND.nextFloat();
		
		Date alteredDate = new Date(RAND.nextLong());
		
		Member member = new Member(memberName, address, date, fee);
		member.setMemberJoinDate(alteredDate);
		
		assertEquals(alteredDate, member.getMemberJoinDate());
	}
	
	@Test
	@DisplayName("it should return the member id")
	void member_getId() {
		String memberName = getSaltString();
		String address = getSaltString();
		Date date = new Date();
		float fee = RAND.nextFloat();
		String id = "M13";
		
		Member member = new Member(memberName, address, date, fee);
		
		assertEquals(id, member.getMemberId());
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
