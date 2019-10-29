package edu.metrostate.ics372.project1;

import java.io.Serializable;

public class MemberIdServer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCounter;
	private static MemberIdServer server;
	
	private MemberIdServer() {
		idCounter = 1;
	}
	
	public static MemberIdServer instance() {
		if (server == null) {
			return (server = new MemberIdServer());
		} else {
			return server;
		}
	}
	
	public int getId() {
		int currentId = idCounter;
		idCounter++;
		
		return currentId;
	}
	
	@Override
	public String toString() {
		return "IdServer" + idCounter;
	}
	
	public static void clearInstance() {
		server = null;
	}

}
