package com.teodonnell0.pong;

import java.io.Serializable;

public class PlayerInfo implements Serializable, Cloneable {
	private static final long serialVersionUID = 8848020944004633312L;
	private final Integer id;
	private final String name;
	private final Paddle paddle;
	
	public PlayerInfo(Integer id, String name, Paddle paddle) {
		this.id = id;
		this.name = name;
		this.paddle = paddle;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Paddle getPaddle() {
		return paddle;
	}
	
	public PlayerInfo clone() {
		Integer id = this.id;
		String name = this.name;
		Paddle paddle = this.paddle.clone();
		return new PlayerInfo(id, name, paddle);
	}
}