package com.teodonnell0.pong.states;

import java.awt.Graphics2D;

import com.teodonnell0.pong.GamePanel;
import com.teodonnell0.pong.enums.Difficulty;
import com.teodonnell0.pong.enums.State;

public class GameStateManager {

	private GameState[] gameStates;
	private State currentState;
	private State previousState;
	
	private GamePanel gamePanel;
	
	public GameStateManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		previousState = State.MENU;
		currentState = State.MENU;
		
		gameStates = new GameState[State.values().length];
		setState(State.MENU);
	}
	
	public void setState(State state) {
		setState(state, null);
	}
	
	public void setState(State state, Difficulty difficulty) {
		previousState = currentState;
		unloadState(previousState);
		currentState = state;

		switch(state) {
		case MENU:
			gameStates[State.MENU.ordinal()] = new MenuState(this);
			gameStates[State.MENU.ordinal()].init();
			break;
		case HUMAN_VS_COMPUTER:
			gameStates[State.HUMAN_VS_COMPUTER.ordinal()] = new HumanVsComputerState(this, difficulty);
			gameStates[State.HUMAN_VS_COMPUTER.ordinal()].init();
			break;
		case COMPUTER_VS_COMPUTER:
			gameStates[State.COMPUTER_VS_COMPUTER.ordinal()] = new ComputerVsComputerState(this, difficulty);
			gameStates[State.COMPUTER_VS_COMPUTER.ordinal()].init();
			break;
		case HUMAN_VS_HUMAN:
			gameStates[State.HUMAN_VS_HUMAN.ordinal()] = new HumanVsHumanState(this);
			gameStates[State.HUMAN_VS_HUMAN.ordinal()].init();
			break;
		}
	}
	public void unloadState(State state) {
		gameStates[state.ordinal()] = null;
	}
	
	public void update() {
		if(gameStates[currentState.ordinal()] != null) {
			gameStates[currentState.ordinal()].update();
		}
	}
	
	public void draw(Graphics2D graphics2D) {
		if(gameStates[currentState.ordinal()] != null) {
			gameStates[currentState.ordinal()].draw(graphics2D);
		}
	}
}
