package com.teodonnell0.pong.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.RectangularShape;

import com.teodonnell0.pong.GamePanel;

public abstract class AbstractEntity implements Entity {
	
	protected Float x;
	protected Float y;
	
	protected Float width;
	protected Float height;
	
	protected Color color;

	public AbstractEntity(Float x, Float y) {
		this.x = x;
		this.y = y;
		this.width = 1f;
		this.height = 1f;
	}
	
	public AbstractEntity(Float x, Float y, Float width, Float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Float getX() {
		return x;
	}
	
	public Float getY() {
		return y;
	}
	
	public Float getWidth() {
		return width;
	}
	
	public Float getHeight() {
		return height;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void drawEntity(Graphics2D graphics2D) {
		graphics2D.setColor(color);
		Line2D line2D = new Line2D.Float(x, y, width, height);
		graphics2D.draw(line2D);
	}
	
}
