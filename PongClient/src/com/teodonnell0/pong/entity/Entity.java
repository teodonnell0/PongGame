package com.teodonnell0.pong.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface Entity {

	Float getX();
	Float getY();
	
	Float getWidth();
	Float getHeight();
	
	Rectangle2D getRectangle2D();
	
	Color getColor();

	void setColor(Color color);
	
	void drawEntity(Graphics2D graphics2D);
}
