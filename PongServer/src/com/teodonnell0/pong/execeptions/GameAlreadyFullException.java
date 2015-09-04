package com.teodonnell0.pong.execeptions;

import com.teodonnell0.pong.ConnectedClient;

public class GameAlreadyFullException extends Exception {

	private static final long serialVersionUID = -766612538033088553L;
	private final ConnectedClient client;
	private final Integer gameId;
	
	public GameAlreadyFullException(Integer gameId, ConnectedClient client) {
		super("Attempted to add client-" + client.getClientId() + " to match-" + gameId +", but game is already full");
		this.client = client;
		this.gameId = gameId;
	}

	public ConnectedClient getClient() {
		return client;
	}

	public Integer getGameId() {
		return gameId;
	}
}
