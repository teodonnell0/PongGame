package com.teodonnell0.pong.states;

import java.awt.Color;
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

public class MenuState extends GameState {

	private Map<Entities, Entity> entities;
	
	public MenuState(GameStateManager gameStateManager) {
		super(gameStateManager);
	}

	@Override
	public void init() {
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
		Ball ball = (Ball) entities.get(Entities.BALL);
		Wall wall = (Wall) entities.get(Entities.WALL);
		ball.tick();
	}

	@Override
	public void draw(Graphics2D graphics2D) {
		graphics2D.setColor(new Color(67, 89, 94));
		graphics2D.fillRect(0, 0, GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT);
		
		
		graphics2D.setColor(new Color(255, 246, 194, 10));
		graphics2D.drawLine(10, 10, 790, 790);
		graphics2D.drawLine(790, 10, 10, 790);

		Iterator<Entity> iterator = entities.values().iterator();
		while(iterator.hasNext()) {
			Entity e = iterator.next();
			e.drawEntity(graphics2D);
		}
		
		String logo = "PONG";
		FontMetrics fontMetrics = graphics2D.getFontMetrics();
		graphics2D.setColor(new Color(255, 255, 255, 80));
		graphics2D.drawString("PONG", (GamePanel.PANEL_WIDTH-fontMetrics.stringWidth(logo))/2, (GamePanel.PANEL_HEIGHT-fontMetrics.getHeight())/4);

	}

}
