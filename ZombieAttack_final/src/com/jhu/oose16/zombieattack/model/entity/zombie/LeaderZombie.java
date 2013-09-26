package com.jhu.oose16.zombieattack.model.entity.zombie;

import com.jhu.oose16.zombieattack.listener.ZombieListener;
import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.GameModel;
import com.jhu.oose16.zombieattack.model.attached.CleanupContext;

/**
 * When it die, it return into a goo.
 */
public class LeaderZombie extends Zombie {

	private static final int WIDTH_UP_DOWN = 79;
	private static final int HEIGHT_UP_DOWN = 90;
	private static final int WIDTH_LEFT_RIGHT = 42;
	private static final int HEIGHT_LEFT_RIGHT = 89;
	private static final int DESTRUCTION_PEROID = 15;
	private static final int RUNNING_SPEED = 3;
	private static final int HEALTH_POINT = Integer.MAX_VALUE;
	private static final int ZOMBIE_DAMAGE = 1;

	private static final int LIFE_TIME = 5000 / GameModel.getUpdateFrequency();

	private static final int NEW_ADD_ZOMBIE_FRE = 800;
	private static final int ZOMBIE_SCORE = 7;

	public LeaderZombie() {
		super(WIDTH_UP_DOWN, HEIGHT_UP_DOWN, WIDTH_LEFT_RIGHT,
				HEIGHT_LEFT_RIGHT, RUNNING_SPEED, HEALTH_POINT, ZOMBIE_DAMAGE,
				-1, LIFE_TIME, DESTRUCTION_PEROID);
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.LeaderZombie);

	}

	@Override
	public void cleanup(CleanupContext cleanupContext) {
		for (int i = 0; i < zombieListeners.size(); i++) {
			zombieListeners.get(i).changeBackAddNewZombieFre();
		}
		cleanupContext.trackDeadZombieCount();
	}
	
	@Override
	public void addZombieListener(ZombieListener zombieListener) {
		super.addZombieListener(zombieListener);
	}
	
	@Override
	public void responseToCreation() {
		for (int i = 0; i < zombieListeners.size(); i++) {
			zombieListeners.get(i).changeAddNewZombieFre(NEW_ADD_ZOMBIE_FRE);
		}
	}
	
	@Override
	public int score() {
		return ZOMBIE_SCORE;
	}
}