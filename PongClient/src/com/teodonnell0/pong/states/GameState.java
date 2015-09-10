package com.teodonnell0.pong.states;

import java.awt.Graphics2D;

import org.apache.log4j.Logger;


public abstract class GameState {

	protected GameStateManager gameStateManager;
	protected final Logger logger = Logger.getLogger(getClass());
	
	public GameState(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
	}
	
	public abstract void init();
	public void update() {
		handleKeyInput();
	}
	public abstract void handleKeyInput();
	public abstract void draw(Graphics2D graphics2D);
}
