package com.teodonnell0.pong.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teodonnell0.pong.GamePanel;
import com.teodonnell0.pong.entity.Ball;
import com.teodonnell0.pong.entity.Entity;
import com.teodonnell0.pong.entity.Wall;
import com.teodonnell0.pong.enums.Entities;

public abstract class Pong {

	public static final float BORDER_SPACING = 10f;
	
	protected final Map<Entities, List<Entity>> entities = new HashMap<>();
	
	protected Ball ball;
	
	protected Wall wall;
	
	protected boolean gameOver;
	
	public Pong() {
		initializeWall();
		initializeBall();
		initializeScores();
	}
	
	private void initializeWall() {
		wall = new Wall(Pong.BORDER_SPACING, Pong.BORDER_SPACING, (float)GamePanel.PANEL_WIDTH-Pong.BORDER_SPACING*2, (float)GamePanel.PANEL_HEIGHT-Pong.BORDER_SPACING*2);
	}
	
	protected abstract void initializeScores();
	protected abstract void initializeBall();
	protected abstract void initializePaddles();
	
	public void draw(Graphics2D graphics2D) {
		drawBoard(graphics2D);
		drawScores(graphics2D);
		drawEntities(graphics2D);
	}
	
	public abstract void update();
	
	private void drawBoard(Graphics2D graphics2D) {
		graphics2D.setColor(new Color(67, 89, 94));
		graphics2D.fillRect(0, 0, GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT);		
		graphics2D.setColor(new Color(255, 246, 194, 10));
		graphics2D.drawLine(10, 10, 790, 790);
		graphics2D.drawLine(790, 10, 10, 790);
	}
	
	protected abstract void drawScores(Graphics2D graphics2D);
	
	protected void drawEntities(Graphics2D graphics2D) {
		ball.drawEntity(graphics2D);
		wall.drawEntity(graphics2D);
	}

	public boolean isGameOver() {
		return gameOver;
	}
}
