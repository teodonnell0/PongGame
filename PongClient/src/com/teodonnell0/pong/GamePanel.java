package com.teodonnell0.pong;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JPanel;

import com.teodonnell0.pong.states.GameStateManager;

public class GamePanel extends JPanel implements Runnable {
	public static final int PANEL_WIDTH = 800;
	public static final int PANEL_HEIGHT = 800;

	public static final int BORDER_SPACING = 10;
	private Thread thread;
	private boolean running;
	private final int FPS = 60;
	private final int TARGET_TIME = 1000 / FPS;

	private BufferedImage bufferedImage;
	private Graphics2D graphics2D;

	private GameStateManager gameStateManager;

	public GamePanel() {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

		setFocusable(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	private void init() {
		running = true;
		bufferedImage = new BufferedImage(PANEL_WIDTH, PANEL_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font font = null;

		try {
			font = Font.createFont(Font.TRUETYPE_FONT, ClassLoader.getSystemResourceAsStream("font.ttf")).deriveFont(64f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		graphics2D = (Graphics2D) bufferedImage.getGraphics();
		graphics2D.setFont(font);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gameStateManager = new GameStateManager(this);
	}

	public synchronized void stop() throws InterruptedException {
		if(running) {
			running = false;
			thread.join();
			System.exit(0);
		}
	}

	public void run() {
		init();

		long start;
		long elapsed;
		long wait;

		while(running) {
			start = System.nanoTime();

			update();
			draw();
			drawToScreen();

			elapsed = System.nanoTime() - start;

			wait = TARGET_TIME - elapsed / 1_000_000;

			if(wait < 0) {
				wait = TARGET_TIME;
			}

			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void update() {
		gameStateManager.update();
	}

	private void draw() {
		gameStateManager.draw(graphics2D);
	}

	private void drawToScreen() {
		Graphics graphics = getGraphics();
		graphics.drawImage(bufferedImage, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);
		graphics.dispose();
	}


}
