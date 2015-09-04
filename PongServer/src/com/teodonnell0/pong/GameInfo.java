package com.teodonnell0.pong;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.teodonnell0.pong.game.Ball;

public class GameInfo implements Serializable, Cloneable {

	private static final long serialVersionUID = 4549967686023722105L;

	private final Integer gameId;
	
	private final List<PlayerInfo> infoList;
	
	private Ball ball;

	public GameInfo(Integer gameId) {
		this.gameId = gameId;
		this.infoList = new ArrayList<>();
	}
	
	public Integer getGameId() {
		return gameId;
	}

	public List<PlayerInfo> getInfoList() {
		return infoList;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public void addPlayer(Integer playerId, String name, Paddle paddle) {
		infoList.add(new PlayerInfo(playerId, name, paddle));
	}
	
	public PlayerInfo getPlayerInfo(Integer index) {
		if(index > 0 && index < infoList.size()) {
			return infoList.get(index);
		}
		return null;
	}
	
	public GameInfo clone() {
		Integer gId = this.gameId;
		GameInfo newGameInfo = new GameInfo(gId);
		List<PlayerInfo> newInfoList = new ArrayList<>();
		for(PlayerInfo playerInfo : infoList) {
			newInfoList.add(playerInfo.clone());
		}
		//Ball newBall = ball.clone();
		
		return newGameInfo;
	}
	
}
