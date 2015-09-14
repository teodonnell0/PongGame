package com.teodonnell0.pong.game;

import java.awt.Graphics2D;

import com.teodonnell0.pong.entity.ComputerPaddle;
import com.teodonnell0.pong.enums.Difficulty;
import com.teodonnell0.pong.enums.PlayerId;

public class ComputerVsComputerPong extends TwoPlayerPong {
	private Difficulty difficulty;
	
	public ComputerVsComputerPong(Difficulty difficulty) {
		super();
		this.difficulty = difficulty;
		initializePaddles();
	}

	@Override
	protected void initializePaddles() {
		playerOne = new ComputerPaddle(PlayerId.ONE, ball, difficulty);
		playerTwo = new ComputerPaddle(PlayerId.THREE, ball, difficulty);
	}
	
	@Override
	protected void drawEntities(Graphics2D graphics2D) {
		super.drawEntities(graphics2D);
		playerOne.drawEntity(graphics2D);
		playerTwo.drawEntity(graphics2D);
	}
	
	@Override
	public void update() {
		super.update();
		
		playerOne.update();
		playerTwo.update();
		
		checkBallCollisionOnTopPaddle(ball);
		checkBallCollisionOnBottomPaddle(ball);
	}
	
	
}
