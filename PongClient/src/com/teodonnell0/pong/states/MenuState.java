package com.teodonnell0.pong.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.teodonnell0.pong.GamePanel;
import com.teodonnell0.pong.entity.Ball;
import com.teodonnell0.pong.entity.Entity;
import com.teodonnell0.pong.entity.MovingEntity;
import com.teodonnell0.pong.entity.Wall;
import com.teodonnell0.pong.enums.Difficulty;
import com.teodonnell0.pong.enums.Entities;
import com.teodonnell0.pong.enums.State;
import com.teodonnell0.pong.game.Pong;
import com.teodonnell0.pong.util.KeyUtility;

public class MenuState extends GameState {

	private enum MenuOptions {MAIN, SINGLEPLAYER, HUMAN_VS_COMPUTER, COMPUTER_VS_COMPUTER, MULTIPLAYER, OPTIONS }
	private enum MenuStateOptions { SINGLEPLAYER, MULTIPLAYER, OPTIONS, EXIT }

	private enum MenuSingleplayerOptions {
		HUMAN_VS_HUMAN,
		HUMAN_VS_COMPUTER,
		COMPUTER_VS_COMPUTER,
		BACK;
	}



	private enum HumanVsComputerOptions {
		DIFFICULTY
	}

	private enum DifficultyOptions {
		EASY, MEDIUM, HARD, IMPOSSIBLE
	}

	private MenuOptions selectedMenuOption;
	private MenuStateOptions selectedMenuStateOption;
	private MenuSingleplayerOptions selectedSingleplayerOption;
	private DifficultyOptions selectedDifficultyOption;


	private Map<Entities, List<Entity>> mapEntities;

	public MenuState(GameStateManager gameStateManager) {
		super(gameStateManager);
	}

	@Override
	public void init() {
		selectedMenuOption = MenuOptions.values()[0];
		selectedMenuStateOption = MenuStateOptions.values()[0];
		selectedSingleplayerOption = MenuSingleplayerOptions.values()[0];
		selectedDifficultyOption = DifficultyOptions.values()[0];

		if(mapEntities == null) {
			mapEntities = new HashMap<>();

			List<Entity> balls = new LinkedList<Entity>();

			for(int i = 0; i < 100; i++) {
				Ball ball = new Ball();
				//ball.setLastUpdatedTime(System.nanoTime() - 2555555555L);
				balls.add(ball);
			}
			
			mapEntities.put(Entities.BALL, balls);
			Wall border = new Wall(Pong.BORDER_SPACING, Pong.BORDER_SPACING, (float)GamePanel.PANEL_WIDTH-Pong.BORDER_SPACING*2, (float)GamePanel.PANEL_HEIGHT-Pong.BORDER_SPACING*2);
			
			List<Entity> walls = new LinkedList<Entity>();
			walls.add(border);
			mapEntities.put(Entities.WALL, walls);
		}
	}

	@Override
	public void update() {
		super.update();

		Iterator<List<Entity>> listIterator = mapEntities.values().iterator();

		while(listIterator.hasNext()) {
			List<Entity> list = listIterator.next();
			Iterator<Entity> entityIterator = list.iterator();

			while(entityIterator.hasNext()) {
				Entity entity = entityIterator.next();

				if(!(entity instanceof MovingEntity)) {
					continue;
				} else {
					((MovingEntity) entity).update();

					if(entity instanceof Ball) {
						Ball ball = (Ball) entity;

						float x = ball.getX();
						float y = ball.getY();

						if(x > GamePanel.PANEL_WIDTH - Pong.BORDER_SPACING) {
							ball.setX(x - ball.getWidth());
							ball.reverseXVelocity();	
						}

						if(y > GamePanel.PANEL_HEIGHT - Pong.BORDER_SPACING) {
							ball.setY(y - ball.getHeight());
							ball.reverseYVelocity();
						}

						if(x < Pong.BORDER_SPACING) {
							ball.setX(x + ball.getWidth());
							ball.reverseXVelocity();
						}

						if(y < Pong.BORDER_SPACING) {
							ball.setY(y + ball.getWidth());
							ball.reverseYVelocity();
						}
					}
				}
			}

		}
	}


	@Override
	public void draw(Graphics2D graphics2D) {

		drawBoard(graphics2D);

		drawEntities(graphics2D);

		drawLogo(graphics2D);

		drawMenu(graphics2D);

	}

	private void drawBoard(Graphics2D graphics2D) {
		graphics2D.setColor(new Color(67, 89, 94));
		graphics2D.fillRect(0, 0, GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT);		
		graphics2D.setColor(new Color(255, 246, 194, 10));
		graphics2D.drawLine(10, 10, 790, 790);
		graphics2D.drawLine(790, 10, 10, 790);
	}

	private void drawEntities(Graphics2D graphics2D) {
		Iterator<List<Entity>> listIterator = mapEntities.values().iterator();

		while(listIterator.hasNext()) {
			List<Entity> list = listIterator.next();
			Iterator<Entity> entityIterator = list.iterator();

			while(entityIterator.hasNext()) {
				Entity entity = entityIterator.next();
				entity.drawEntity(graphics2D);
			}
		}
	}

	private void drawLogo(Graphics2D graphics2D) {
		Font font = graphics2D.getFont();
		font = font.deriveFont(72f);
		graphics2D.setFont(font);

		String logo = "4PONG";
		FontMetrics fontMetrics = graphics2D.getFontMetrics();
		graphics2D.setColor(new Color(255, 255, 255, 160));
		graphics2D.drawString("4PONG", (GamePanel.PANEL_WIDTH-fontMetrics.stringWidth(logo))/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/4);
	}

	protected void drawMenu(Graphics2D graphics2D) {
		Font font = graphics2D.getFont();
		font = font.deriveFont(24f);
		graphics2D.setFont(font);
		graphics2D.setColor(new Color(255, 255, 255, 80));

		FontMetrics fontMetrics = graphics2D.getFontMetrics();

		int i = 0;
		String optionString;
		int optionStringWidth;
		switch (selectedMenuOption) {
		case MAIN:
			for(MenuStateOptions option : MenuStateOptions.values()) {
				graphics2D.drawString(option.toString(), (GamePanel.PANEL_WIDTH-fontMetrics.stringWidth(option.name()))/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/2+(fontMetrics.getHeight()*i));

				if(option == selectedMenuStateOption) {
					graphics2D.drawString(option.toString(), (GamePanel.PANEL_WIDTH-fontMetrics.stringWidth(option.name()))/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/2+(fontMetrics.getHeight()*i));
				}
				i += 3;
			}
			break;

		case SINGLEPLAYER:
			for(MenuSingleplayerOptions option : MenuSingleplayerOptions.values()) {
				optionString = option.toString().replaceAll("_", " ");
				optionStringWidth = fontMetrics.stringWidth(optionString);

				graphics2D.drawString(optionString, (GamePanel.PANEL_WIDTH-optionStringWidth)/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/2+(fontMetrics.getHeight()*i));

				if(option == selectedSingleplayerOption) {
					graphics2D.drawString(optionString, (GamePanel.PANEL_WIDTH-optionStringWidth)/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/2+(fontMetrics.getHeight()*i));
				}
				i += 3;
			}
			break;

		case HUMAN_VS_COMPUTER:
			optionString = "SELECT DIFFICULTY";
			optionStringWidth = fontMetrics.stringWidth(optionString);

			graphics2D.setColor(new Color(255, 255, 255, 255));
			graphics2D.drawString(optionString, (GamePanel.PANEL_WIDTH-optionStringWidth)/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/2-(fontMetrics.getHeight()*3));
			graphics2D.setColor(new Color(255, 255, 255, 80));
			for(DifficultyOptions option : DifficultyOptions.values()) {
				optionString = option.name();
				optionStringWidth = fontMetrics.stringWidth(optionString);

				graphics2D.drawString(optionString, (GamePanel.PANEL_WIDTH-optionStringWidth)/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/2+(fontMetrics.getHeight()*i));

				if(option == selectedDifficultyOption) {
					graphics2D.drawString(optionString, (GamePanel.PANEL_WIDTH-optionStringWidth)/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/2+(fontMetrics.getHeight()*i));
				}
				i += 3;
			}
			break;
			
		case COMPUTER_VS_COMPUTER:
			optionString = "SELECT DIFFICULTY";
			optionStringWidth = fontMetrics.stringWidth(optionString);

			graphics2D.setColor(new Color(255, 255, 255, 255));
			graphics2D.drawString(optionString, (GamePanel.PANEL_WIDTH-optionStringWidth)/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/2-(fontMetrics.getHeight()*3));
			graphics2D.setColor(new Color(255, 255, 255, 80));
			for(DifficultyOptions option : DifficultyOptions.values()) {
				optionString = option.name();
				optionStringWidth = fontMetrics.stringWidth(optionString);

				graphics2D.drawString(optionString, (GamePanel.PANEL_WIDTH-optionStringWidth)/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/2+(fontMetrics.getHeight()*i));

				if(option == selectedDifficultyOption) {
					graphics2D.drawString(optionString, (GamePanel.PANEL_WIDTH-optionStringWidth)/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/2+(fontMetrics.getHeight()*i));
				}
				i += 3;
			}	
			break;
		}

		

	}

	@Override
	public void handleKeyInput() {
		if(KeyUtility.isPressed(KeyUtility.Key.DOWN_ARROW)) {
			int nextOrdinal;

			switch(selectedMenuOption) {
			case MAIN:
				nextOrdinal = (selectedMenuStateOption.ordinal()+1) % MenuStateOptions.values().length;
				selectedMenuStateOption = MenuStateOptions.values()[nextOrdinal];
				break;
			case SINGLEPLAYER:
				nextOrdinal = (selectedSingleplayerOption.ordinal()+1) % MenuSingleplayerOptions.values().length;
				selectedSingleplayerOption = MenuSingleplayerOptions.values()[nextOrdinal];
				break;
			case HUMAN_VS_COMPUTER:
				nextOrdinal = (selectedDifficultyOption.ordinal()+1) % DifficultyOptions.values().length;
				selectedDifficultyOption = DifficultyOptions.values()[nextOrdinal];
				break;
			case COMPUTER_VS_COMPUTER:
				nextOrdinal = (selectedDifficultyOption.ordinal()+1) % DifficultyOptions.values().length;
				selectedDifficultyOption = DifficultyOptions.values()[nextOrdinal];
				break;
			}

		}

		if(KeyUtility.isPressed(KeyUtility.Key.UP_ARROW)) {
			int previousOrdinal;


			switch(selectedMenuOption) {
			case MAIN:
				previousOrdinal = (selectedMenuStateOption.ordinal()-1) >= 0 ? selectedMenuStateOption.ordinal()-1 : MenuStateOptions.values().length-1;
				selectedMenuStateOption = MenuStateOptions.values()[previousOrdinal];
				break;
			case SINGLEPLAYER:
				previousOrdinal = (selectedSingleplayerOption.ordinal()-1) >= 0 ? selectedSingleplayerOption.ordinal()-1 : MenuSingleplayerOptions.values().length-1;
				selectedSingleplayerOption = MenuSingleplayerOptions.values()[previousOrdinal];
				break;
			case HUMAN_VS_COMPUTER:
				previousOrdinal = (selectedDifficultyOption.ordinal()-1) >= 0 ? selectedDifficultyOption.ordinal()-1 : DifficultyOptions.values().length-1;
				selectedDifficultyOption = DifficultyOptions.values()[previousOrdinal];
				break;
			case COMPUTER_VS_COMPUTER:
				previousOrdinal = (selectedDifficultyOption.ordinal()-1) >= 0 ? selectedDifficultyOption.ordinal()-1 : DifficultyOptions.values().length-1;
				selectedDifficultyOption = DifficultyOptions.values()[previousOrdinal];
				break;
			}
		}

		if(KeyUtility.isPressed(KeyUtility.Key.SPACE)) {
			switch(selectedMenuOption) {
			case MAIN:

				switch(selectedMenuStateOption) {
				case SINGLEPLAYER:
					selectedMenuOption = MenuOptions.SINGLEPLAYER;
					break;
				case MULTIPLAYER:
					selectedMenuOption = MenuOptions.MULTIPLAYER;
					break;
				case OPTIONS:
					selectedMenuOption = MenuOptions.OPTIONS;
					break;
				case EXIT:
					logger.info("Exitting");
					System.exit(-1);
					break;
				}
				break;

			case SINGLEPLAYER:
				switch(selectedSingleplayerOption) {
				case HUMAN_VS_HUMAN:
					gameStateManager.setState(State.HUMAN_VS_HUMAN);
					break;
				case COMPUTER_VS_COMPUTER:
					selectedMenuOption = MenuOptions.COMPUTER_VS_COMPUTER;
					break;
				case HUMAN_VS_COMPUTER:
					selectedMenuOption = MenuOptions.HUMAN_VS_COMPUTER;
					break;
				case BACK:
					selectedMenuOption = MenuOptions.MAIN;
					break;
				}
				break;

			case HUMAN_VS_COMPUTER:
				switch(selectedDifficultyOption) {
				case EASY:
					gameStateManager.setState(State.HUMAN_VS_COMPUTER, Difficulty.EASY);
					break;
				case MEDIUM:
					gameStateManager.setState(State.HUMAN_VS_COMPUTER, Difficulty.MEDIUM);
					break;
				case HARD:
					gameStateManager.setState(State.HUMAN_VS_COMPUTER, Difficulty.HARD);
					break;
				case IMPOSSIBLE:
					gameStateManager.setState(State.HUMAN_VS_COMPUTER, Difficulty.IMPOSSIBLE);
					break;
				}
				
			case COMPUTER_VS_COMPUTER:
				switch(selectedDifficultyOption) {
				case EASY:
					gameStateManager.setState(State.COMPUTER_VS_COMPUTER, Difficulty.EASY);
					break;
				case MEDIUM:
					gameStateManager.setState(State.COMPUTER_VS_COMPUTER, Difficulty.MEDIUM);
					break;
				case HARD:
					gameStateManager.setState(State.COMPUTER_VS_COMPUTER, Difficulty.HARD);
					break;
				case IMPOSSIBLE:
					gameStateManager.setState(State.COMPUTER_VS_COMPUTER, Difficulty.IMPOSSIBLE);
					break;
				}
			}
		}
	}
}
