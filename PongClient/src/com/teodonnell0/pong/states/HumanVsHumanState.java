package com.teodonnell0.pong.states;

import java.awt.Graphics2D;

import com.teodonnell0.pong.enums.State;
import com.teodonnell0.pong.game.HumanVsHumanPong;

public class HumanVsHumanState extends GameState {

	public HumanVsHumanState(GameStateManager gameStateManager) {
		super(gameStateManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		pong = new HumanVsHumanPong();
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
		((HumanVsHumanPong)pong).update();
		
		if(((HumanVsHumanPong)pong).isGameOver()) {
			gameStateManager.setState(State.MENU);
		}
	}
}
