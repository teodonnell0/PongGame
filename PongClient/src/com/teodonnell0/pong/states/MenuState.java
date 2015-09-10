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

	enum MenuStateOptions { SINGLEPLAYER, MULTIPLAYER, OPTIONS, EXIT }
	
	private MenuStateOptions selectedOption;
	
	private Map<Entities, Entity> entities;
	
	public MenuState(GameStateManager gameStateManager) {
		super(gameStateManager);
	}

	@Override
	public void init() {
		selectedOption = MenuStateOptions.MULTIPLAYER;
		entities = new HashMap<>();
		
		Wall border = new Wall(GamePanel.BORDER_SPACING, 
							  GamePanel.BORDER_SPACING, 
							  GamePanel.PANEL_WIDTH-GamePanel.BORDER_SPACING*2, 
							  GamePanel.PANEL_HEIGHT-GamePanel.BORDER_SPACING*2);
		Random random = new Random();
		Ball ball = new Ball(random.nextFloat()*(GamePanel.PANEL_WIDTH-GamePanel.BORDER_SPACING)+10,random.nextFloat()*(GamePanel.PANEL_HEIGHT-GamePanel.BORDER_SPACING)+10,20f,20f,random.nextFloat(), random.nextFloat());
		
		
		entities.put(Entities.BALL, ball);
		entities.put(Entities.WALL, border);
	}

	@Override
	public void update() {
		super.update();
		Ball ball = (Ball) entities.get(Entities.BALL);
		ball.tick();
	}

	@Override
	public void draw(Graphics2D graphics2D) {

		drawBoard(graphics2D);

		Iterator<Entity> iterator = entities.values().iterator();
		while(iterator.hasNext()) {
			Entity e = iterator.next();
			e.drawEntity(graphics2D);
		}
		
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
	
	private void drawLogo(Graphics2D graphics2D) {
		Font font = graphics2D.getFont();
		font = font.deriveFont(120f);
		graphics2D.setFont(font);
		
		String logo = "PONG";
		FontMetrics fontMetrics = graphics2D.getFontMetrics();
		graphics2D.setColor(new Color(255, 255, 255, 80));
		graphics2D.drawString("PONG", (GamePanel.PANEL_WIDTH-fontMetrics.stringWidth(logo))/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/4);
	}
	
	private void drawMenu(Graphics2D graphics2D) {
		Font font = graphics2D.getFont();
		font = font.deriveFont(24f);
		graphics2D.setFont(font);
		graphics2D.setColor(new Color(255, 255, 255, 80));
		
		FontMetrics fontMetrics = graphics2D.getFontMetrics();
		
		int i = 0;
		
		for(MenuStateOptions option : MenuStateOptions.values()) {
			graphics2D.drawString(option.toString(), (GamePanel.PANEL_WIDTH-fontMetrics.stringWidth(option.name()))/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/2+(fontMetrics.getHeight()*i));
			
			if(option == selectedOption) {
				graphics2D.drawString(option.toString(), (GamePanel.PANEL_WIDTH-fontMetrics.stringWidth(option.name()))/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/2+(fontMetrics.getHeight()*i));
			}
			i += 3;
		}
		
	}

	@Override
	public void handleKeyInput() {
		if(KeyUtility.isPressed(KeyUtility.Key.DOWN_ARROW)) {
			int nextOrdinal = (selectedOption.ordinal()+1) % MenuStateOptions.values().length;
			selectedOption = MenuStateOptions.values()[nextOrdinal];
		}
		
		if(KeyUtility.isPressed(KeyUtility.Key.UP_ARROW)) {
			int previousOrdinal = (selectedOption.ordinal()-1) >= 0 ? selectedOption.ordinal()-1 : MenuStateOptions.values().length-1;
			selectedOption = MenuStateOptions.values()[previousOrdinal];
		}
		
		if(KeyUtility.isPressed(KeyUtility.Key.SPACE)) {
			State state = null;
			switch(selectedOption) {
			case SINGLEPLAYER:
				state = State.MENU_SINGLEPLAYER;
				break;
			case MULTIPLAYER:
				state = State.MENU_MULTIPLAYER;
				break;
			case OPTIONS:
				state = State.MENU_OPTIONS;
				break;
			case EXIT:
				logger.info("Exitting from MenuState");
				System.exit(-1);
				break;
			default:
				logger.fatal("Illegal MenuState" + selectedOption);
			}
			if(state != null) {
				gameStateManager.setState(state);
			}
		}
	}

}
