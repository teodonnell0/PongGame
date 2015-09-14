package com.teodonnell0.pong.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import com.teodonnell0.pong.GamePanel;

public class Ball extends MovingAbstractEntity {

	public final static Float DEFAULT_RADIUS = 5f;
	private final Color DEFAULT_COLOR = new Color(255, 255, 255, 150);
	
	public Ball() {
		super(GamePanel.PANEL_WIDTH/2f, GamePanel.PANEL_HEIGHT/2f, DEFAULT_RADIUS, DEFAULT_RADIUS);
		setColor(DEFAULT_COLOR);
		reset();
	}
	
	public void reset() {
		x = (GamePanel.PANEL_WIDTH)/2f;
		y = (GamePanel.PANEL_HEIGHT)/2f;
		Random random = new Random();
		int rand = random.nextInt(4);
		
		xVelocity = 0f;
		yVelocity = 0f;
		
		switch(rand) {
		case 0:
			xVelocity = 0.01f;
			break;
		case 1:
			yVelocity = 0.01f;
			break;
		case 2:
			xVelocity = 0.01f;
			yVelocity = 0.01f;
			break;
		case 3:
		}
		
		xVelocity += random.nextFloat()*0.02f;
		yVelocity += random.nextFloat()*0.02f;
		
		if(random.nextBoolean()) {
			xVelocity *= -1;
		}
		if(random.nextBoolean()) {
			yVelocity *= -1;
		}	
		lastUpdatedTime = System.nanoTime();
	}

	@Override
	public void drawEntity(Graphics2D graphics2D) {
		graphics2D.setColor(color);
		Ellipse2D ellipse2D = getEllipse2D();
		graphics2D.fill(ellipse2D);
	}
	
	public Ellipse2D getEllipse2D() {
		return new Ellipse2D.Float(x, y, width, height);
	}

	@Override
	public void update() {
		long endTime = System.nanoTime();
		long duration = (endTime - lastUpdatedTime) / 100000;
		x += xVelocity * duration *0.5f;
		y += yVelocity * duration *0.5f;
		lastUpdatedTime = endTime;
	}
	



}
