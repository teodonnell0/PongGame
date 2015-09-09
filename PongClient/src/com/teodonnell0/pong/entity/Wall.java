package com.teodonnell0.pong.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Wall extends AbstractEntity {

	public Wall(Integer x, Integer y, Integer width, Integer height) {
		super(new Float(x), new Float(y), new Float(width), new Float(height));
		color = new Color(255, 246, 194, 80);
	}

	
	public Wall(Float x, Float y, Float width, Float height) {
		super(x, y, width, height);
		color = new Color(255, 246, 194, 80);
	}

	@Override
	public void drawEntity(Graphics2D graphics2D) {
		graphics2D.setColor(color);
		graphics2D.draw((Rectangle2D)getShape());
	}
	
	@Override
	public Object getShape() {
		return new Rectangle2D.Float(x, y, width, height);
	}

}
