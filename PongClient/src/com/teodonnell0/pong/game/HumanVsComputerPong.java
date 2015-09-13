package com.teodonnell0.pong.game;

import java.awt.Graphics2D;

import com.teodonnell0.pong.entity.ComputerPaddle;
import com.teodonnell0.pong.entity.PlayerPaddle;
import com.teodonnell0.pong.enums.PlayerId;
import com.teodonnell0.pong.states.Difficulty;

public class HumanVsComputerPong extends TwoPlayerPong {

	private Difficulty difficulty;
	
	public HumanVsComputerPong(Difficulty difficulty) {
		super();
		this.difficulty = difficulty;
		initializePaddles();
	}
	
	@Override
	public void update() {
		super.update();
		playerOne.update();
		playerTwo.update();
		
		checkBallCollisionOnBottomPaddle(ball);
		checkBallCollisionOnTopPaddle(ball);
	}

	@Override
	protected void initializePaddles() {
		playerOne = new PlayerPaddle(PlayerId.ONE);
		playerTwo = new ComputerPaddle(PlayerId.THREE, ball, difficulty);
	}

	@Override
	protected void drawEntities(Graphics2D graphics2D) {
		super.drawEntities(graphics2D);
		playerOne.drawEntity(graphics2D);
		playerTwo.drawEntity(graphics2D);
	}

}
