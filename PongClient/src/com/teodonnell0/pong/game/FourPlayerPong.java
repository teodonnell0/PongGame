package com.teodonnell0.pong.game;

import com.teodonnell0.pong.entity.Ball;
import com.teodonnell0.pong.entity.Paddle;

public abstract class FourPlayerPong {

	protected Paddle playerOne;
	protected Paddle playerTwo;
	protected Paddle playerThree;
	protected Paddle playerFour;
	
	public void checkBallCollisionOnBottomPaddle(Ball ball) {
		if(playerOne.isIntersectedWith(ball.getRectangle2D())) {
			ball.reverseYVelocity();
			ball.setY(ball.getY()-ball.getHeight());
		}
	}

	public void checkBallCollisionOnTopPaddle(Ball ball) {
		if(playerThree.isIntersectedWith(ball.getRectangle2D())) {
			ball.reverseYVelocity();
			ball.setY(ball.getY()+ball.getHeight());
		}
	}
	
	public void checkBallCollisionOnRightPaddle(Ball ball) {
		if(playerTwo.isIntersectedWith(ball.getRectangle2D())) {
			ball.reverseXVelocity();
			ball.setY(ball.getX()-ball.getHeight());
		}
	}
	
	public void checkBallCollisionOnLeftPaddle(Ball ball) {
		if(playerFour.isIntersectedWith(ball.getRectangle2D())) {
			ball.reverseYVelocity();
			ball.setY(ball.getX()+ball.getHeight());
		}
	}
}
