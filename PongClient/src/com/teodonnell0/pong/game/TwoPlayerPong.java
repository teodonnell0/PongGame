package com.teodonnell0.pong.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import com.teodonnell0.pong.GamePanel;
import com.teodonnell0.pong.entity.Ball;
import com.teodonnell0.pong.entity.Paddle;
import com.teodonnell0.pong.enums.PlayerId;

public abstract class TwoPlayerPong extends SinglePlayerPong {

	protected Paddle playerOne;
	protected Paddle playerTwo;

	private Integer playerOneScore;
	private Integer playerTwoScore;


	public TwoPlayerPong() {
		initializeScores();
	}

	protected void initializeScores() {
		playerOneScore = new Integer(0);
		playerTwoScore = new Integer(0);
	}

	public void checkBallCollisionOnBottomPaddle(Ball ball) {
		if(playerOne.isIntersectedWith(ball.getRectangle2D())) {
			ball.reverseYVelocity();
			ball.setY(ball.getY()-ball.getHeight());
		}
	}

	public void checkBallCollisionOnTopPaddle(Ball ball) {
		if(playerTwo.isIntersectedWith(ball.getRectangle2D())) {
			ball.reverseYVelocity();
			ball.setY(ball.getY()+ball.getHeight());
		}
	}

	protected void drawScores(Graphics2D graphics2D) {
		Font font = graphics2D.getFont();
		font = font.deriveFont(24f);
		graphics2D.setFont(font);
		graphics2D.setColor(new Color(255, 255, 255, 30));

		FontMetrics fontMetrics = graphics2D.getFontMetrics();

		String score;
		int x;
		int y;

		score = playerOneScore.toString();
		x = (GamePanel.PANEL_WIDTH - fontMetrics.stringWidth(score))/2;
		y = (GamePanel.PANEL_HEIGHT/2 + 100);
		graphics2D.drawString(score, x, y);

		score = playerTwoScore.toString();
		x = (GamePanel.PANEL_WIDTH - fontMetrics.stringWidth(score))/2;
		y = (GamePanel.PANEL_HEIGHT/2 - 100);
		graphics2D.drawString(score, x, y);

		if(scored != null) {
			graphics2D.setColor(new Color(255,255,255));
			font = font.deriveFont(64f);
			graphics2D.setFont(font);
			fontMetrics = graphics2D.getFontMetrics();
			switch(scored) {
			case ONE:
				if(playerOneScore == 7) {
					score = "PLAYER WINS";
					gameOver = true;
				}
				else
					score = "PLAYER SCORED";
				break;
			case TWO:
				if(playerTwoScore == 7) {
					score = "CPU WINS";
					gameOver = true;
				}
				else
					score = "CPU SCORED";
				break;
			}
			x = (GamePanel.PANEL_WIDTH - fontMetrics.stringWidth(score))/2;
			y = (GamePanel.PANEL_HEIGHT)/2;
			graphics2D.drawString(score, x, y);
		}
	}


	@Override
	public void update() {
		if(scored != null) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			ball.reset();
			scored = null;
		}
		ball.update();
		float x = ball.getX();
		float y = ball.getY();
		if(x > GamePanel.PANEL_WIDTH - Pong.BORDER_SPACING) {
			ball.setX(x - ball.getWidth());
			ball.reverseXVelocity();	
		}

		if(y > GamePanel.PANEL_HEIGHT - Pong.BORDER_SPACING) {
			ball.setY(y - ball.getHeight());
			ball.reverseYVelocity();
			playerTwoScore++;
			scored = PlayerId.TWO;
		}

		if(x < Pong.BORDER_SPACING) {
			ball.setX(x + ball.getWidth());
			ball.reverseXVelocity();
		}

		if(y < Pong.BORDER_SPACING) {
			ball.setY(y + ball.getWidth());
			ball.reverseYVelocity();
			playerOneScore++;
			scored = PlayerId.ONE;
		}
	}

	private PlayerId scored = null;
}
