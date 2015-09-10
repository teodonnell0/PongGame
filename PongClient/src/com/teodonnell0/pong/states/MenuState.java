package com.teodonnell0.pong.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import com.teodonnell0.pong.GamePanel;
import com.teodonnell0.pong.entity.Ball;
import com.teodonnell0.pong.entity.Entities;
import com.teodonnell0.pong.entity.Entity;
import com.teodonnell0.pong.entity.Wall;
import com.teodonnell0.pong.util.KeyUtility;

public class MenuState extends GameState {

	private enum MenuOptions {MAIN, SINGLEPLAYER, MULTIPLAYER, OPTIONS }
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

	
	private Map<Entities, Entity> menuBackgroundEntities;
	
	public MenuState(GameStateManager gameStateManager) {
		super(gameStateManager);
	}

	@Override
	public void init() {
		selectedMenuOption = MenuOptions.values()[0];
		selectedMenuStateOption = MenuStateOptions.values()[0];
		selectedSingleplayerOption = MenuSingleplayerOptions.values()[0];
		selectedDifficultyOption = DifficultyOptions.values()[0];
		
		if(menuBackgroundEntities == null) {
			menuBackgroundEntities = new HashMap<>();
			Wall border = new Wall(GamePanel.BORDER_SPACING, 
					GamePanel.BORDER_SPACING, 
					GamePanel.PANEL_WIDTH-GamePanel.BORDER_SPACING*2, 
					GamePanel.PANEL_HEIGHT-GamePanel.BORDER_SPACING*2);
			Random random = new Random();
			Ball ball = new Ball(random.nextFloat()*(GamePanel.PANEL_WIDTH-GamePanel.BORDER_SPACING)+10,random.nextFloat()*(GamePanel.PANEL_HEIGHT-GamePanel.BORDER_SPACING)+10,20f,20f,random.nextFloat(), random.nextFloat());


			menuBackgroundEntities.put(Entities.BALL, ball);
			menuBackgroundEntities.put(Entities.WALL, border);
		}
	}

	@Override
	public void update() {
		super.update();
		Ball ball = (Ball) menuBackgroundEntities.get(Entities.BALL);

		ball.tick();
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
		Iterator<Entity> iterator = menuBackgroundEntities.values().iterator();
		while(iterator.hasNext()) {
			Entity e = iterator.next();
			e.drawEntity(graphics2D);
		}
	}

	private void drawLogo(Graphics2D graphics2D) {
		Font font = graphics2D.getFont();
		font = font.deriveFont(120f);
		graphics2D.setFont(font);

		String logo = "PONG";
		FontMetrics fontMetrics = graphics2D.getFontMetrics();
		graphics2D.setColor(new Color(255, 255, 255, 80));
		graphics2D.drawString("PONG", (GamePanel.PANEL_WIDTH-fontMetrics.stringWidth(logo))/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/4);
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
			}
		}
	}
}
