package com.teodonnell0.pong.game;

import java.util.LinkedList;
import java.util.List;

import com.teodonnell0.pong.ConnectedClient;
import com.teodonnell0.pong.GameProtocol;
import com.teodonnell0.pong.enums.Protocol;
import com.teodonnell0.pong.execeptions.GameAlreadyFullException;
import com.teodonnell0.pong.execeptions.GameAlreadyStartedException;

public abstract class Game {

	public enum GameStatus {
		WAITING_FOR_PLAYERS,
		RUNNING,
		ENDED;
	}
	
	private static int games = 0;
	
	protected final Integer gameId;
	
	protected final List<ConnectedClient> clientsInGame;
	
	protected GameStatus gameStatus;
	

	public Game() {
		this.gameId = ++games;
		clientsInGame = new LinkedList<>();
		gameStatus = GameStatus.WAITING_FOR_PLAYERS;
	}
	
	public int getNumberOfClientsInGame() {
		return clientsInGame.size();
	}
	
	public Integer getGameId() {
		return gameId;
	}


	public void initializeGame() {
		gameStatus = GameStatus.RUNNING;
	}

	//Returns the total number of games that have been started
	protected static int getGameCount() {
		return games;
	}
	
	public boolean addPlayerToGame(ConnectedClient client) throws GameAlreadyStartedException, GameAlreadyFullException {
		if(gameStatus != GameStatus.WAITING_FOR_PLAYERS) {
			throw new GameAlreadyStartedException(gameId, client);
		}
		
		if(clientsInGame.size() > 4) {
			throw new GameAlreadyFullException(gameId, client);
		}
		
		//assign paddle
		
		client.getPlayer().setPlayerId(clientsInGame.size());
		clientsInGame.add(client);
		
		client.sendProtocol(new GameProtocol(Protocol.GAME_ID, gameId));
		client.sendProtocol(new GameProtocol(Protocol.PLAYER_ID, client.getPlayer().getPlayerId()));
		
		return true;
	}
	
	protected void sendProtocolToAll(GameProtocol gameProtocol) {
		for(ConnectedClient client : clientsInGame) {
			client.sendProtocol(gameProtocol);
		}
	}
}
