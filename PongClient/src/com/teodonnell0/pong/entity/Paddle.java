package com.teodonnell0.pong.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import com.teodonnell0.pong.GamePanel;
import com.teodonnell0.pong.enums.PlayerId;
import com.teodonnell0.pong.game.Pong;

public abstract class Paddle extends MovingAbstractEntity {

	public static final Float DEFAULT_WIDTH = ((float)GamePanel.PANEL_WIDTH-Pong.BORDER_SPACING)/4;
	public static final Float DEFAULT_HEIGHT = 10f;
	public static final Float PADDING_FROM_WALL = 30f;
	
	private final Color DEFAULT_COLOR = new Color(255, 255, 255, 200);
	
	protected PlayerId playerId;
	
	public Paddle(PlayerId playerId) {
		super(null, null, null, null);
		setColor(DEFAULT_COLOR);
		setPlayerId(playerId);
		
		//Set width/height based on playerId
		switch(playerId) {
		case ONE:
		case THREE:
			setWidth(DEFAULT_WIDTH);
			setHeight(DEFAULT_HEIGHT);
			break;
		case TWO:
		case FOUR:
			setWidth(DEFAULT_HEIGHT);
			setHeight(DEFAULT_WIDTH);
			break;
		}
		
		//Set x/y based on playerId
		switch(playerId) {
		case ONE:
			setX((float)(GamePanel.PANEL_WIDTH-Pong.BORDER_SPACING-width)/2);
			setY((float)GamePanel.PANEL_HEIGHT-Pong.BORDER_SPACING-Paddle.PADDING_FROM_WALL);
			break;
		case TWO:
			setX((float)GamePanel.PANEL_WIDTH-Pong.BORDER_SPACING-Paddle.PADDING_FROM_WALL);
			setY((float)(GamePanel.PANEL_HEIGHT-Pong.BORDER_SPACING-height)/2);
			break;
		case THREE:
			setX((float)(GamePanel.PANEL_WIDTH-width)/2);
			setY(Paddle.PADDING_FROM_WALL);
			break;
			
		case FOUR:
			setX(Paddle.PADDING_FROM_WALL);
			setY((float)(GamePanel.PANEL_HEIGHT-Pong.BORDER_SPACING-height)/2);
			break;
		}
	
		xVelocity = 2.5f;
		yVelocity = 2.5f;
	}
	
	public void setPlayerId(PlayerId playerId) {
		this.playerId = playerId;
	}
	
	public PlayerId getPlayerId() {
		return playerId;
	}
	
	public void drawEntity(Graphics2D graphics2D) {
		super.drawEntity(graphics2D);
		Rectangle2D rectangle2D = getRectangle2D();
		graphics2D.fill(rectangle2D);
	}

}
