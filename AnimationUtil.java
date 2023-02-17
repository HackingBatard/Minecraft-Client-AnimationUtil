package com.riceclient.clients.cringe.utils.impl.render;

import com.riceclient.clients.cringe.utils.Utility;
import com.riceclient.clients.cringe.utils.impl.TimerUtil;

public class AnimationUtil implements Utility {
	
	private int progress;
	private AnimationType type;
	private long startTime;
	private int duration;
	private Direction direction;
	
	public AnimationUtil(AnimationType type){
		this.type = type;
		stop();
	}
	
	public void stop() {
		direction = Direction.IDLE;
	}
	
	public void start(int duration) {
		direction = Direction.INC;
		this.progress = 0;
		this.duration = duration;
		startTime = System.currentTimeMillis();
	}
	
	public void end(int duration) {
		direction = Direction.OUG;
		this.progress = 100;
		this.duration = duration;
		startTime = System.currentTimeMillis();
	}
	
	public void update() {
		if(direction == Direction.IDLE)
			return;
		
		int delay = (1000 * duration) / 100;
		
		if(direction == Direction.INC) {
			if(isMaxed()) {
				if(progress != 100) progress = 100;
				stop();
			}else {
				if(startTime + delay <= System.currentTimeMillis()) {
					progress++;
					startTime = System.currentTimeMillis();
				}
			}
		}else
		if(direction == Direction.OUG) {
			if(isDead()) {
				if(progress != 0) progress = 0;
				stop();
			}else {
				if(startTime + delay <= System.currentTimeMillis()) {
					progress--;
					startTime = System.currentTimeMillis();
				}
			}
		}
	}
	
	public boolean isMaxed() {
		return progress >= 100;
	}
	
	public boolean isDead() {
		return progress <= 0;
	}
	
	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	public enum AnimationType{
		FADE
	}
	
	public enum Direction{
		INC,
		OUG,
		IDLE
	}
	
}
