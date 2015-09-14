package com.teodonnell0.pong.entity;

import java.awt.geom.Rectangle2D;

public interface MovingEntity {

	void setX(Float x);
	void setY(Float y);
	
	Float getX();
	Float getY();
	Float getxVelocity();
	Float getyVelocity();

	void setInitialVelocity(Float xVelocity, Float yVelocity);
	void setMinimumVelocity(Float minimumVelocity);
	void setMaximumVelocity(Float maximumVelocity);

	void reverseXVelocity();
	void reverseYVelocity();
	
	void update();
	
	boolean isIntersectedWith(Rectangle2D rectangle2D);
	
}
