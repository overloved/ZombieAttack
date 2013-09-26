package com.jhu.oose16.zombieattack.model.entity.boulder;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.attached.CollisionContext;
import com.jhu.oose16.zombieattack.model.effect.FireBoulderToZombieEffect;
import com.jhu.oose16.zombieattack.model.entity.zombie.Zombie;

public class FireBoulder extends Boulder {

	private static final int RADIUS = 24;
	private static final int APPEARANCE_COUNT = 9;
	private static final int DISAPPEARANCE_COUNT = 8;

	public FireBoulder() {
		super(RADIUS, APPEARANCE_COUNT, DISAPPEARANCE_COUNT);
	}

	/** The total continue damage after the hit */
	private static final int DAMAGE_OVER_TIME = 1;

	@Override
	public void hitZombie(Zombie zombie, CollisionContext collisionContext) {
		if (!zombie.isUnderEffect()) {
			collisionContext.addEffect(new FireBoulderToZombieEffect(zombie,
					DAMAGE_OVER_TIME));
		}
		super.hitZombie(zombie, collisionContext);
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.FireBoulder);
	}

}
