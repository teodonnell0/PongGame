package com.teodonnell0.pong.entity;

import com.teodonnell0.pong.GamePanel;
import com.teodonnell0.pong.enums.PlayerId;
import com.teodonnell0.pong.game.Pong;
import com.teodonnell0.pong.util.KeyUtility;
import com.teodonnell0.pong.util.KeyUtility.Key;

public class PlayerPaddle extends Paddle {

	public PlayerPaddle(PlayerId playerId) {
		super(playerId);
	}
	
	@Override
	public void update() {
		long endTime = System.nanoTime();
	/*	float duration = (endTime -lastUpdatedTime)/1_000_000f/10f; // still working on this

		float tempX = x;
		float tempY = y;
		
		if(KeyUtility.isDown(Key.LEFT_ARROW)) {
			switch(playerId) {
			case ONE:
			case THREE:
				tempX += (-1*xVelocity) * duration;
				break;
			case TWO:
			case FOUR:
				tempY += (-1*yVelocity) * duration;
				break;
			}
		} else if(KeyUtility.isDown(Key.RIGHT_ARROW)) {
			switch(playerId) {
			case ONE:
			case THREE:
				tempX += (xVelocity) * duration;
				break;
			case TWO:
			case FOUR:
				tempY += yVelocity * duration;
				break;
			}
		} else {
			tempX = x;
			tempY = y;
		}
		
		// Test if paddle goes outside the wall boundaries
		if(tempX + getWidth() > GamePanel.PANEL_WIDTH - Pong.BORDER_SPACING) {
			tempX = GamePanel.PANEL_WIDTH - Pong.BORDER_SPACING - getWidth() - 1;
		}
		
		if(tempX < Pong.BORDER_SPACING) {
			tempX = Pong.BORDER_SPACING + 1;
		}
		
		if(tempY + getHeight() > GamePanel.PANEL_HEIGHT - Pong.BORDER_SPACING) {
			tempY = GamePanel.PANEL_HEIGHT - Pong.BORDER_SPACING - getHeight() - 1;
		}
		
		if(tempY < Pong.BORDER_SPACING) {
			tempY = Pong.BORDER_SPACING + 1;
		}
		
		x = tempX;
		y = tempY;
		
		lastUpdatedTime = endTime;*/
	}


}
