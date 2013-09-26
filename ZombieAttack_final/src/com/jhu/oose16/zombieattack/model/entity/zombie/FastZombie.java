package com.jhu.oose16.zombieattack.model.entity.zombie;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;

/**
 * Zombie can moving randomly and when it hits the barriers, it can choose
 * another randomly moving path.
 */
public class FastZombie extends Zombie {

	private static final int WIDTH_UP_DOWN = 35;
	private static final int HEIGHT_UP_DOWN = 60;
	private static final int WIDTH_LEFT_RIGHT = 26;
	private static final int HEIGHT_LEFT_RIGHT = 60;
	private static final int DESTRUCTION_PEROID = 10;
	private static final int RUNNING_SPEED = 6;
	private static final int HEALTH_POINT = 2;
	private static final int ZOMBIE_DAMAGE = 1;
	private static final int ZOMBIE_SCORE = 2;

	public FastZombie() {
		super(WIDTH_UP_DOWN, HEIGHT_UP_DOWN, WIDTH_LEFT_RIGHT,
				HEIGHT_LEFT_RIGHT, RUNNING_SPEED, HEALTH_POINT, ZOMBIE_DAMAGE,
				-1, -1, DESTRUCTION_PEROID);
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.FastZombie);

	}

	@Override
	public int score() {
		return ZOMBIE_SCORE;
	}
}