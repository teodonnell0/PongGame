package com.teodonnell0.pong.game;

import java.awt.Graphics2D;

import com.teodonnell0.pong.GamePanel;
import com.teodonnell0.pong.entity.ComputerPaddle;
import com.teodonnell0.pong.entity.PlayerPaddle;
import com.teodonnell0.pong.enums.Difficulty;
import com.teodonnell0.pong.enums.PlayerId;
import com.teodonnell0.pong.util.KeyUtility;
import com.teodonnell0.pong.util.KeyUtility.Key;

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
		updateHumanPlayerOne();

		playerTwo.update();

		checkBallCollisionOnTopPaddle(ball);
		checkBallCollisionOnBottomPaddle(ball);
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

	private void updateHumanPlayerOne() {
		long endTime = System.nanoTime();
		float duration = (endTime - playerOne.getLastUpdatedTime())/1_000_000f/10f;

		float tempX = playerOne.getX();

		if(KeyUtility.isDown(Key.LEFT_ARROW)) {
			tempX += -1 * playerOne.getxVelocity() * duration;
		} else if (KeyUtility.isDown(Key.RIGHT_ARROW)) {
			tempX += playerOne.getxVelocity() * duration;
		}

		if(tempX + playerOne.getWidth() > GamePanel.PANEL_WIDTH - Pong.BORDER_SPACING) {
			tempX = GamePanel.PANEL_WIDTH - Pong.BORDER_SPACING - playerOne.getWidth() - 1;
		}

		if(tempX < Pong.BORDER_SPACING) {
			tempX = Pong.BORDER_SPACING + 1;
		}

		playerOne.setX(tempX);
		playerOne.setLastUpdatedTime(endTime);
	}
}
