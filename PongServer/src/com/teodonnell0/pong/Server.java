package com.teodonnell0.pong;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.teodonnell0.pong.execeptions.GameAlreadyFullException;
import com.teodonnell0.pong.execeptions.GameAlreadyStartedException;
import com.teodonnell0.pong.game.Game;
import com.teodonnell0.pong.game.PongGame;

public class Server implements Runnable{
	
	
	private final static Queue<ConnectedClient> lobby = new LinkedList<>();
	private final static List<ConnectedClient> clients = new ArrayList<>(); // clients that are currently connected to the server
	private final static Map<Integer, Game> games = new HashMap<>();
	
	private final static Logger logger = Logger.getLogger(Server.class);
	
	private static boolean acceptingConnections;
	
	private static final int DEFAULT_PORT = 49818;
	
	private static ExecutorService executorService;
	
	private final ExecutorService gameService = Executors.newCachedThreadPool();
	
	public static void main(String...strings) {
		Server server = new Server();
		
		executorService = Executors.newSingleThreadExecutor();
		executorService.execute(server);
		
		Logger logger = Logger.getLogger("Main");
		acceptingConnections = true;
		
		ServerSocket serverSocket = null;
		logger.info("Attempting to open server socket on port " + DEFAULT_PORT);
		try {
			serverSocket = new ServerSocket(DEFAULT_PORT);
		} catch (IOException e) {
			acceptingConnections = false;
			logger.fatal("Port " + DEFAULT_PORT + " is already in use. Exiting.");
			System.exit(-1);
		}
		
		logger.info("Server socket opened successfully");
		
		logger.info("Listening for connections");
		while(acceptingConnections) {
			try {
				Socket connection = serverSocket.accept();
				logger.info("Obtained a connection from " + connection.getInetAddress().toString() + ":" + connection.getPort());
				ConnectedClient client = new ConnectedClient(server, connection);
				addToLobby(client);
				addToServer(client);
			} catch (IOException e) {
				e.printStackTrace();
				logger.fatal("Something went wrong while setting up new ConnectClient");
			}
		}
	}
	
	public static PongGame getPongGame(ConnectedClient client) {
		Integer matchId = client.getPlayer().getMatchId();
		
		if(games.containsKey(matchId)) {
			return (PongGame) games.get(matchId);
		}
		
		return null;
	}
	
	public static boolean addToLobby(ConnectedClient client) {
		return lobby.add(client);
	}
	
	public static boolean removeFromLobby(ConnectedClient client) {
		if(lobby.contains(client))
			return lobby.remove(client);
		return false;
	}
	
	public static boolean addToServer(ConnectedClient client) {
		return clients.add(client);
	}
	public static boolean removeFromServer(ConnectedClient client) {
		if(clients.contains(client)) 
			return clients.remove(client);
		return false;
	}
	
	public static Game getGame(ConnectedClient client) {
		Integer matchId = client.getPlayer().getMatchId();
		
		if(games.containsKey(matchId)) {
			return games.get(matchId);
		}
		
		return null; 
	}
	
	/*
	 * Checks lobby to see if there are enough players to start a new game
	 */
	@Override
	public void run() {
		while(acceptingConnections) {
			checkLobby();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checkLobby() {
		if(lobby.size() >= 4) {
			logger.info("Server has at least 4 players waiting in the queue. Setting up a new game");
			PongGame pong = new PongGame();
			Integer gameId = pong.getGameId();
			while(pong.getNumberOfClientsInGame() != 4) {
				ConnectedClient client = lobby.poll();
				
				try {
					pong.addPlayerToGame(client);
					logger.info(gameId + ": added client#"+client.getClientId()+" ("+client.getClientId()+")");
				} catch (GameAlreadyFullException | GameAlreadyStartedException e) {
					logger.warn("Match:"+ gameId + " was already full when server tried to add a client:" + client.getClientId());
					lobby.add(client);
					logger.warn("Client" + client.getClientId() + " has been added back into the lobby's queue");
				}
				client.getPlayer().setMatchId(gameId);
				
				if(!games.containsKey(gameId)) {
					games.put(gameId, pong);
				}
			}
			pong.initializeGame();
			gameService.execute(pong);
		}
	}
}
