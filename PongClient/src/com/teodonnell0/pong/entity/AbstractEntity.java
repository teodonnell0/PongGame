package com.teodonnell0.pong.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class AbstractEntity implements Entity {
	
	protected Float x;
	protected Float y;
	
	protected Float width;
	protected Float height;
	
	protected Color color;
	
	public AbstractEntity(Float x, Float y, Float width, Float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = new Color(0, 0, 0);
	}
	
	
	public void setX(Float x) {
		this.x = x;
	}


	public void setY(Float y) {
		this.y = y;
	}


	public void setWidth(Float width) {
		this.width = width;
	}


	public void setHeight(Float height) {
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
	
	public Rectangle2D getRectangle2D() {
		return new Rectangle2D.Float(x, y, width, height);
	}

	public void drawEntity(Graphics2D graphics2D) {
		graphics2D.setColor(color);
		graphics2D.draw(getRectangle2D());
	}
	
}
