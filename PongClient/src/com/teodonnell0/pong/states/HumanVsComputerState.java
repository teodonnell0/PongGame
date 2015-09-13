package com.teodonnell0.pong.states;

import java.awt.Graphics2D;

import com.teodonnell0.pong.enums.State;
import com.teodonnell0.pong.game.HumanVsComputerPong;

public class HumanVsComputerState extends GameState {

	private Difficulty difficulty;
	
	public HumanVsComputerState(GameStateManager gameStateManager, Difficulty difficulty) {
		super(gameStateManager);
		this.difficulty = difficulty;
	}

	@Override
	public void init() {
		pong = new HumanVsComputerPong(difficulty);
	}

	@Override
	public void handleKeyInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D graphics2D) {
		pong.draw(graphics2D);
	}

	@Override
	public void update() {
		super.update();
		((HumanVsComputerPong)pong).update();
		
		if(((HumanVsComputerPong)pong).isGameOver()) {
			gameStateManager.setState(State.MENU);
		}
	}
}
