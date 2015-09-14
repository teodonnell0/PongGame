package com.teodonnell0.pong.entity;

import java.awt.Color;
import java.awt.geom.Line2D;

public class Wall extends AbstractEntity {

	private final Color DEFAULT_COLOR = new Color(255, 246, 194, 80);
	
	public Wall(Float x, Float y, Float width, Float height) {
		super(x, y, width, height);
		setColor(DEFAULT_COLOR);
	}
}
