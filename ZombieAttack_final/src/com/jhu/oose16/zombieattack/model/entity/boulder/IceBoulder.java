package com.jhu.oose16.zombieattack.model.entity.boulder;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.attached.CollisionContext;
import com.jhu.oose16.zombieattack.model.effect.IceBoulderToZombieEffect;
import com.jhu.oose16.zombieattack.model.entity.zombie.Zombie;

public class IceBoulder extends Boulder {

	private static final int RADIUS = 28;
	private static final int APPEARANCE_COUNT = 10;
	private static final int DISAPPEARANCE_COUNT = 10;

	public IceBoulder() {
		super(RADIUS, APPEARANCE_COUNT, DISAPPEARANCE_COUNT);
	}

	@Override
	public void hitZombie(Zombie zombie, CollisionContext collisionContext) {
		if (!zombie.isUnderEffect()) {
			collisionContext.addEffect(new IceBoulderToZombieEffect(zombie));
		}
		super.hitZombie(zombie, collisionContext);
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.IceBoulder);
	}
}
