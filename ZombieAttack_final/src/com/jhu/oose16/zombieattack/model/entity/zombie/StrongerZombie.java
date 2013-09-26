package com.jhu.oose16.zombieattack.model.entity.zombie;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;

/**
 * Zombie can moving randomly and when it hits the barriers, it can choose
 * another randomly moving path.
 */
public class StrongerZombie extends Zombie {

	private static final int WIDTH_UP_DOWN = 45;
	private static final int HEIGHT_UP_DOWN = 60;
	private static final int WIDTH_LEFT_RIGHT = 31;
	private static final int HEIGHT_LEFT_RIGHT = 58;
	private static final int DESTRUCTION_PEROID = 11;
	private static final int RUNNING_SPEED = 3;
	private static final int HEALTH_POINT = 3;
	private static final int ZOMBIE_DAMAGE = 1;
	private static final int ZOMBIE_SCORE = 4;

	public StrongerZombie() {
		super(WIDTH_UP_DOWN, HEIGHT_UP_DOWN, WIDTH_LEFT_RIGHT,
				HEIGHT_LEFT_RIGHT, RUNNING_SPEED, HEALTH_POINT, ZOMBIE_DAMAGE,
				-1, -1, DESTRUCTION_PEROID);
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.StrongerZombie);
	}

	@Override
	public int score() {
		return ZOMBIE_SCORE;
	}
}