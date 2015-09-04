package com.teodonnell0.pong;

public class Player {

	private Integer matchId;
	
	private Integer playerId;

	private Paddle paddle;
	
	public Integer getMatchId() {
		return matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}
		
}
