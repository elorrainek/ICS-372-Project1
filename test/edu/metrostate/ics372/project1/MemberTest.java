package edu.metrostate.ics372.project1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;
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
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		
		Member member = new Member(memberName, address, date, feePaid);
		
		assertEquals(memberName, member.getMemberName());
	}
	
	@Test
	@DisplayName("it should alter the member name")
	void member_setMemberName() {
		String memberName = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		
		String alteredName = getSaltString();
		
		Member member = new Member(memberName, address, date, feePaid);
		member.setMemberName(alteredName);
		
		assertEquals(alteredName, member.getMemberName());
	}
	
	@Test
	@DisplayName("it should return the member address")
	void member_getMemberAddress() {
		String memberName = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		
		Member member = new Member(memberName, address, date, feePaid);
		
		assertEquals(address, member.getMemberAddress());
	}
	
	@Test
	@DisplayName("it should alter the member address")
	void member_setMemberAddress() {
		String memberName = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		
		String alteredAddress = getSaltString();
		
		Member member = new Member(memberName, address, date, feePaid);
		member.setMemberAddress(alteredAddress);
		
		assertEquals(alteredAddress, member.getMemberAddress());
	}
	
	@Test
	@DisplayName("it should return the member join date")
	void member_getMemberJoinDate() {
		String memberName = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		
		Member member = new Member(memberName, address, date, feePaid);
		
		assertEquals(date, member.getMemberJoinDate());
	}
	
	@Test
	@DisplayName("it should alter the member join date")
	void member_setMemberJoinDate() {
		String memberName = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		
		GregorianCalendar alteredDate = 
				new GregorianCalendar(randIntBetween(1900, 2019)
						, randIntBetween(1, 12)
						, randIntBetween(1, 28));
		
		Member member = new Member(memberName, address, date, feePaid);
		member.setMemberJoinDate(alteredDate);
		
		assertEquals(alteredDate, member.getMemberJoinDate());
	}
	
	@Test
	@DisplayName("it should return the member id")
	void member_getId() {
		String memberName = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		int current = getCurrentCount() + 1;
		String id = "M" + current;
		
		Member member = new Member(memberName, address, date, feePaid);
		
		assertEquals(id, member.getMemberId());
	}
	
	@Test
	@DisplayName("it should return the boolean if the guest has paid the fee")
	void member_getFeePaid() {
		String memberName = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = RAND.nextBoolean();
		
		Member member = new Member(memberName, address, date, feePaid);
		
		assertEquals(feePaid, member.getFeePaid());
	}
	
	@Test
	@DisplayName("it should set the boolean for the fee paid")
	void member_setFeePaid() {
		String memberName = getSaltString();
		String address = getSaltString();
		GregorianCalendar date = new GregorianCalendar();
		boolean feePaid = false;
		
		Member member = new Member(memberName, address, date, feePaid);
		member.setFeePaid(true);
		
		assertEquals(true, member.getFeePaid());
	}
	
	private int randIntBetween(int min, int max) {
		return min + RAND.nextInt(max - min);
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
	
	private int getCurrentCount() {
		return MemberIdServer.instance().getId();
	}

}
