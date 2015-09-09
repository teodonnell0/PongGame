package com.teodonnell0.pong.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public interface Entity {

	Float getX();
	Float getY();
	
	Float getWidth();
	Float getHeight();
	
	Object getShape();
	
	Color getColor();
	
	void setColor(Color color);
	
	void drawEntity(Graphics2D graphics2D);
}
