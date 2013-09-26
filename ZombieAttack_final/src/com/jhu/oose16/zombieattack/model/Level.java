package com.jhu.oose16.zombieattack.model;

import java.util.List;

import com.jhu.oose16.zombieattack.model.entity.Entity;
import com.jhu.oose16.zombieattack.model.generator.ZombieGenerator;

public class Level {

	/** the upper bound of the number of zombies left in the game */
	private static final int ZOMBIE_NUMBER_UPPER_BOUND = 15;
	/** the zombie need to kill */
	private static final int ZOMBIE_NEED_TO_KILL = 3;

	private ZombieGenerator zombieGenerator;

	private ExpirableObjectManager expirableObjectManager;

	public Level(ZombieGenerator zombieGenerator, List<Entity> barAndScientist,
			List<Entity> boulderleft) {
		this.zombieGenerator = zombieGenerator;

		expirableObjectManager = new ExpirableObjectManager(barAndScientist, boulderleft);
	}

	public ZombieGenerator getZombieGenerator() {
		return zombieGenerator;
	}

	public ExpirableObjectManager getExpirableObjectManager() {
		return expirableObjectManager;
	}

	public int getZombieNumberUpperBound() {
		return ZOMBIE_NUMBER_UPPER_BOUND;
	}

	public int getZombieNeedToKill() {
		return ZOMBIE_NEED_TO_KILL;
	}

}
