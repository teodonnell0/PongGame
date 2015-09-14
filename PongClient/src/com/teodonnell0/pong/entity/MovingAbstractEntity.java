package com.teodonnell0.pong.entity;

import java.awt.geom.Rectangle2D;

public abstract class MovingAbstractEntity extends AbstractEntity implements MovingEntity {

	protected Float xVelocity, yVelocity;
	
	protected Float previousX;
	protected Float previousY;
	
	protected Float minimumVelocity;
	protected Float maximumVelocity;
	
	protected Long lastUpdatedTime;
	
	public MovingAbstractEntity(Float x, Float y, Float width, Float height) {
		super(x, y, width, height);
		this.previousX = 0f;
		this.previousY = 0f;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		this.minimumVelocity = 0f;
		this.maximumVelocity = 1f;
		this.lastUpdatedTime = System.nanoTime();
	}
	
	public void setInitialVelocity(Float xVelocity, Float yVelocity) {
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}
	
	public void reverseYVelocity() {
		yVelocity *= -1;
	}
	
	public void reverseXVelocity() {
		xVelocity *= -1;
	}
	
	@Override
	public void setX(Float x) {
		previousX = this.x;
		this.x = x;
	}
	
	@Override
	public void setY(Float y) {
		previousY = this.y;
		this.y = y;
	}
	
	public Float getMinimumVelocity() {
		return minimumVelocity;
	}

	public void setMinimumVelocity(Float minimumVelocity) {
		this.minimumVelocity = minimumVelocity;
	}

	public Float getMaximumVelocity() {
		return maximumVelocity;
	}

	public void setMaximumVelocity(Float maximumVelocity) {
		this.maximumVelocity = maximumVelocity;
	}
	
	public boolean isIntersectedWith(Rectangle2D rectangle2D) {
		return !this.getRectangle2D().createIntersection(rectangle2D).isEmpty();
	}

	public Float getxVelocity() {
		return xVelocity;
	}

	public Float getyVelocity() {
		return yVelocity;
	}

	public void setxVelocity(Float xVelocity) {
		this.xVelocity = xVelocity;
	}

	public void setyVelocity(Float yVelocity) {
		this.yVelocity = yVelocity;
	}

	public Float getPreviousX() {
		return previousX;
	}

	public Float getPreviousY() {
		return previousY;
	}

	public Long getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Long lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
	
	
}
