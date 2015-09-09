package com.teodonnell0.pong;

import javax.swing.JFrame;

public class Game {

	public static void main(String...strings) {
		JFrame window = new JFrame("Pong");
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
