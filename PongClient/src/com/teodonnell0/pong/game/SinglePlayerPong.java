package com.teodonnell0.pong.game;

import com.teodonnell0.pong.entity.Ball;

public abstract class SinglePlayerPong extends Pong {

	public SinglePlayerPong() {
	}
	
	protected void initializeBall() {
		ball = new Ball();
	}

}
