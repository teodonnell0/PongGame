package com.teodonnell0.pong;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import com.teodonnell0.pong.game.PongGame;

public class Paddle implements Serializable, Cloneable {
	private static final long serialVersionUID = -8363963421252341101L;

	private final Integer PADDLE_WIDTH = 25;
	private final Integer PADDLE_HEIGHT = 10;
	
	private final Integer playerId;
	
	private Float delta;
	
	private Float acceleration;
	
	public Paddle(Integer playerId) {
		this.playerId = playerId;
		this.acceleration = 1f;
		this.delta = 0f;
	}

	public Paddle clone() {
		return null;
	}
	
	
	public Float getDelta() {
		return delta;
	}

	public void setAcceleration(Float acceleration) {
		this.acceleration = acceleration;
	}

	public Float getAcceleration() {
		return acceleration;
	}
	
	public void increaseDelta() {
		if(delta + acceleration < PongGame.WALL_BOUNDS / 2)
			delta += acceleration;
		else
			delta = new Float(PongGame.WALL_BOUNDS/2);
	}
	
	public void decreaseDelta() {
		if(delta - acceleration > -PongGame.WALL_BOUNDS / 2)
			delta -= acceleration;
		else
			delta = new Float(-PongGame.WALL_BOUNDS / 2);
	}

	public Integer getPlayerId() {
		return playerId;
	}
	
	public Rectangle2D getRectangle() {
		return new Rectangle2D.Float(400+Math.abs(delta),100f,PADDLE_WIDTH,PADDLE_HEIGHT);
	}
	

}
