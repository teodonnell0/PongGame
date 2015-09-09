package com.teodonnell0.pong.entity;

public interface MovingEntity {

	boolean isStopped();
	
	Float getXVelocity();
	Float getYVelocity();
	
	Float getXAcceleration();
	Float getYAcceleration();
	
	Float getMinimumVelocity();
	Float getMaximumVelocity();
	
	Float getMinimumAcceleration();
	Float getMaximumAcceleration();
	
	void setMinimumAcceleration(Float minimumAcceleration);
	void setMaximumAcceleration(Float maximumAcceleration);
}
