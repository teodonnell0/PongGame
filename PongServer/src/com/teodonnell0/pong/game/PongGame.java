package com.teodonnell0.pong.game;

import java.util.ArrayList;
import java.util.List;

import com.teodonnell0.pong.ConnectedClient;
import com.teodonnell0.pong.GameInfo;
import com.teodonnell0.pong.GameProtocol;
import com.teodonnell0.pong.Paddle;
import com.teodonnell0.pong.enums.PaddleDelta;
import com.teodonnell0.pong.enums.Protocol;
import com.teodonnell0.pong.execeptions.GameAlreadyFullException;
import com.teodonnell0.pong.execeptions.GameAlreadyStartedException;


public class PongGame extends Game implements Runnable {

	public static final Integer WALL_BOUNDS = 800;

	private Ball ball;
	
	private final List<Paddle> paddles;
	
	private final GameInfo gameInfo;
	
	public PongGame() {
		super();
		paddles = new ArrayList<>();
		gameInfo = new GameInfo(gameId);
	}
	
	@Override
	public void initializeGame() {
		super.initializeGame();
		ball = new Ball();
		gameInfo.setBall(ball);
	}
	
	@Override
	public boolean addPlayerToGame(ConnectedClient client) throws GameAlreadyStartedException, GameAlreadyFullException {
		super.addPlayerToGame(client);
		gameInfo.addPlayer(client.getPlayer().getPlayerId(), "", client.getPlayer().getPaddle());
		return paddles.add(client.getPlayer().getPaddle());
	}
	
	public synchronized void attemptPaddleMove(ConnectedClient client, PaddleDelta delta) {
		if(gameStatus == GameStatus.RUNNING) {
			if(clientsInGame.contains(client)) {
				switch(delta) {
				case INCREASE:
					client.getPlayer().getPaddle().increaseDelta();
					break;
				case DECREASE:
					client.getPlayer().getPaddle().decreaseDelta();
				}
			} 
		}
	}

	@Override
	public void run() {
		while(gameStatus == GameStatus.RUNNING) {
			ball.updateBallPosition(paddles);
			sendProtocolToAll(new GameProtocol(Protocol.GAME_INFO, gameInfo));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
