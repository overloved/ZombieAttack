package com.jhu.oose16.zombieattack.model.entity.zombie;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;

public class GiantZombie extends Zombie {

	private static final int WIDTH_UP_DOWN = 90;
	private static final int HEIGHT_UP_DOWN = 90;
	private static final int WIDTH_LEFT_RIGHT = 67;
	private static final int HEIGHT_LEFT_RIGHT = 90;
	private static final int DESTRUCTION_PEROID = 10;
	private static final int RUNNING_SPEED = 2;
	private static final int HEALTH_POINT = 5;
	private static final int ZOMBIE_DAMAGE = 2;
	private static final int ZOMBIE_SCORE = 7;

	public GiantZombie() {
		super(WIDTH_UP_DOWN, HEIGHT_UP_DOWN, WIDTH_LEFT_RIGHT,
				HEIGHT_LEFT_RIGHT, RUNNING_SPEED, HEALTH_POINT, ZOMBIE_DAMAGE,
				-1, -1, DESTRUCTION_PEROID);
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.GiantZombie);
	}
	
	@Override
	public int score() {
		return ZOMBIE_SCORE;
	}
}
