package com.teodonnell0.pong.execeptions;

import com.teodonnell0.pong.ConnectedClient;


public class GameAlreadyStartedException extends Exception {

	private static final long serialVersionUID = 9002217548561413965L;
	private ConnectedClient client;
	private Integer matchId;
	
	public GameAlreadyStartedException(Integer matchId, ConnectedClient client) {
		super("Client-" + client.getClientId() + " attempted joining matchId:" + matchId +", but game has already started");
		this.matchId = matchId;
		this.client = client;
	}

	public final ConnectedClient getClient() {
		return client;
	}

	public final Integer getMatchId() {
		return matchId;
	}
	
}
