package edu.metrostate.ics372.project1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class MemberIdServerTest {
	@Test
	@DisplayName("it should return an instance of MemberIdServer")
	void MemberIdServer_instance() {
		assertTrue(MemberIdServer.instance() instanceof MemberIdServer);
	}

	@Test
	@DisplayName("it should return the current id counter")
	void MemberIdServer_toString() {
		int expected = getCurrentCount() + 1;
		assertEquals("IdServer" + expected, MemberIdServer.instance().toString());
	}
	
	@Test
	@DisplayName("it should return an incremented value of idCounter")
	void MemberIdServer_getId() {
		int expected = getCurrentCount() + 1;
		assertEquals(expected, MemberIdServer.instance().getId());
	}
	
	private int getCurrentCount() {
		return MemberIdServer.instance().getId();
	}
}
