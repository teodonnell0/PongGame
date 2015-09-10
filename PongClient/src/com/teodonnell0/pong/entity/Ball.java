package com.teodonnell0.pong.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import com.teodonnell0.pong.GamePanel;

public class Ball extends MovingAbstractEntity {

	private Float initialVelocity = 0.1f;
	private final static Float defaultSize = 20f;

	
	public Ball() {
		super(new Float(GamePanel.PANEL_WIDTH/2)-defaultSize/2, new Float(GamePanel.PANEL_HEIGHT/2)-defaultSize/2, defaultSize, defaultSize);
		color = new Color(255, 246, 194);
	}
	
	public Ball(Float x, Float y) {
		super(x, y, 3f, 3f);
		color = new Color(255, 246, 194);
	}
	
	public Ball(Float x, Float y, Float width, Float height) {
		super(x, y, width, height);
		color = new Color(255, 246, 194);
	}
	
	public Ball(Float x, Float y, Float width, Float height, Float initialXVelocity, Float initialYVelocity) {
		super(x, y, width, height, initialXVelocity, initialYVelocity);
		color = new Color(255, 246, 194);
	}

	public Ball(Float x, Float y, Float width, Float height, Float initialXVelocity, Float initialYVelocity, Float initialXAcceleration, Float initialYAcceleration) {
		super(x, y, width, height, initialXVelocity, initialYVelocity, initialXAcceleration, initialYAcceleration);
		color = new Color(255, 246, 194);
	}
	
	public void start() {
		xVelocity = initialVelocity;
		yVelocity = initialVelocity;
	}
	
	public void stop() {
		xVelocity = 0f;
		yVelocity = 0f;
	}
	
	public void reset() {
		stop();
		x = new Float(GamePanel.PANEL_WIDTH / 2);
		y = new Float(GamePanel.PANEL_HEIGHT / 2);
	}

	@Override
	public void drawEntity(Graphics2D graphics2D) {
		graphics2D.setColor(color);
		graphics2D.fill((Ellipse2D)getShape());
	}
	
	@Override
	public Object getShape() {
		return new Ellipse2D.Float(x, y, width, height);
	}

	@Override
	public void tick() {
		long now = System.nanoTime();
		long duration = (now -lastUpdatedTime)/1_000_000;
		
		float tempX = xVelocity * (duration) + .5f * (xAcceleration) * (duration) * (duration);
		float tempY = yVelocity * (duration) + .5f * (yAcceleration) * (duration) * (duration);
		
		if(x + tempX <= GamePanel.BORDER_SPACING || (x + tempX) + defaultSize/2 > GamePanel.PANEL_WIDTH-GamePanel.BORDER_SPACING*2) {
			xVelocity = -xVelocity;
		}
		
		if(y + tempY < GamePanel.BORDER_SPACING || (y + tempY) + defaultSize/2 > GamePanel.PANEL_HEIGHT-GamePanel.BORDER_SPACING*2) {
			yVelocity = -yVelocity;
		}
		
		x += xVelocity * (duration) + .5f * (xAcceleration) * (duration) * (duration);
		y += yVelocity * (duration) + .5f * (yAcceleration) * (duration) * (duration);
		
		ticks = ++ticks % 10000;
		
		if(ticks == 0) {
			xAcceleration += 0.01f;
			yAcceleration += 0.01f;
		}
		lastUpdatedTime = System.nanoTime();
	}

}
