package com.teodonnell0.pong.entity;

public abstract class MovingAbstractEntity extends AbstractEntity implements MovingEntity {

	protected Float xVelocity, yVelocity;
	
	protected Float xAcceleration, yAcceleration;
	
	protected Float minimumVelocity;
	protected Float maximumVelocity;
	
	protected Float minimumAcceleration;
	protected Float maximumAcceleration;
	
	protected Long lastUpdatedTime;
	
	protected Integer ticks = new Integer(0);
	
	public MovingAbstractEntity(Float x, Float y, Float width, Float height) {
		super(x, y, width, height);
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		
		this.xAcceleration = 0f;
		this.yAcceleration = 0f;
		
		this.lastUpdatedTime = System.nanoTime();
	}
	
	public MovingAbstractEntity(Float x, Float y, Float width, Float height, Float initialXVelocity, Float initialYVelocity) {
		super(x, y, width, height);
		this.xVelocity = initialXVelocity;
		this.yVelocity = initialYVelocity;
		
		this.xAcceleration = 0f;
		this.yAcceleration = 0f;
		
		this.lastUpdatedTime = System.nanoTime();
	}
	
	
	public MovingAbstractEntity(Float x, Float y, Float width, Float height, Float initialXVelocity, Float initialYVelocity, Float initialXAcceleration, Float initialYAcceleration) {
		super(x, y, width, height);
		this.xVelocity = initialXVelocity;
		this.yAcceleration = initialYVelocity;
		
		this.xAcceleration = initialXAcceleration;
		this.yAcceleration = initialYAcceleration;
		
		this.lastUpdatedTime = System.nanoTime();
	}
	
	public Float getXVelocity() {
		return xVelocity;
	}

	public Float getYVelocity() {
		return yVelocity;
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

	public Float getXAcceleration() {
		return xAcceleration;
	}

	public Float getYAcceleration() {
		return yAcceleration;
	}
	
	public Float getMinimumAcceleration() {
		return minimumAcceleration;
	}

	public void setMinimumAcceleration(Float minimumAcceleration) {
		this.minimumAcceleration = minimumAcceleration;
	}

	public Float getMaximumAcceleration() {
		return maximumAcceleration;
	}

	public void setMaximumAcceleration(Float maximumAcceleration) {
		this.maximumAcceleration = maximumAcceleration;
	}

	public boolean isStopped() {
		return ((xVelocity == 0f) && (yVelocity == 0f));
	}
	
	public abstract void tick();
	
}
