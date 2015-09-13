package com.teodonnell0.pong.entity;

import com.teodonnell0.pong.GamePanel;
import com.teodonnell0.pong.enums.PlayerId;
import com.teodonnell0.pong.game.Pong;
import com.teodonnell0.pong.states.Difficulty;

public class ComputerPaddle extends Paddle {

	private Ball ball;
	private Difficulty difficulty;


	public ComputerPaddle(PlayerId playerId, Ball ball, Difficulty difficulty) {
		super(playerId);
		this.ball = ball;
		this.difficulty = difficulty;

		switch(difficulty) {
		case EASY:
			this.width /= 5f;
			this.xVelocity /= 5f;
			break;
		case MEDIUM:
			this.width /= 3f;
			this.xVelocity /= 3f;
			break;
		case HARD:
			this.width /= 2f;
			this.xVelocity /= 2f;
			break;
		case IMPOSSIBLE:
			break;
		}
		xVelocity /= 2;
		yVelocity /= 2;
	}

	@Override
	public void update() {
		long endTime = System.nanoTime();
		float duration = (endTime - lastUpdatedTime)/1_000_000/10f;
		
		Float tempX = x;
		if(ball.getX() > (x + x + width)/2) {
			tempX += xVelocity * duration;
		}
		if(ball.getX() < (x + x + width)/2) {
			tempX += (-1 * xVelocity) * duration;
		}
		
		if(tempX + getWidth() > GamePanel.PANEL_WIDTH - Pong.BORDER_SPACING) {
			tempX = GamePanel.PANEL_WIDTH - Pong.BORDER_SPACING - getWidth() - 1;
		}
		
		if(tempX < Pong.BORDER_SPACING) {
			tempX = Pong.BORDER_SPACING + 1;
		}
		
		x = tempX;
		
		lastUpdatedTime = endTime;

	}

}
