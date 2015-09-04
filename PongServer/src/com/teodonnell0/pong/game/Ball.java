package com.teodonnell0.pong.game;

import java.awt.geom.Ellipse2D;
import java.util.List;

import com.teodonnell0.pong.Paddle;

public class Ball {

	public final static Float BALL_RADIUS = 5F;
	
	private Float x;
	private Float y;
	
	private Float velocityX;
	private Float velocityY;
	
	private Float accelerationX;
	private Float accelerationY;
	
	private Long timer;
	public Ball() {
		this.x = 0f;
		this.y = 0f;
		
		this.velocityX = 1f;
		this.velocityY = 1f;
		
		this.accelerationX = 1f;
		this.accelerationY = 1f;
	}

	public void updateBallPosition(List<Paddle> paddles) {
		Long duration = System.nanoTime()/1_000_000_000;
		timer = System.nanoTime();
		
		velocityX += accelerationX*duration;
		velocityY += accelerationY*duration;
	}

	public Ellipse2D getEllipse2D(){
		return new Ellipse2D.Float(x, y, BALL_RADIUS, BALL_RADIUS);
	}
}
