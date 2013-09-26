package com.jhu.oose16.zombieattack.model.entity.boulder;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.attached.CollisionContext;
import com.jhu.oose16.zombieattack.model.entity.materials.Barrier;
import com.jhu.oose16.zombieattack.model.entity.materials.Goo;
import com.jhu.oose16.zombieattack.model.entity.scientist.Scientist;
import com.jhu.oose16.zombieattack.model.entity.zombie.Zombie;

public class EtherealBoulder extends Boulder {

	private static final int RADIUS = 28;
	private static final int APPEARANCE_COUNT = 10;
	private static final int DISAPPEARANCE_COUNT = 10;

	public EtherealBoulder() {
		super(RADIUS, APPEARANCE_COUNT, DISAPPEARANCE_COUNT);
	}

	@Override
	public void hitBarrier(Barrier barrier, CollisionContext collisionContext) {
		if (barrier.isExpirableObjectType(ExpirableObjectType.ScreenBound)) {
			super.hitBarrier(barrier, collisionContext);
		}
	}

	@Override
	public void hitGoo(Goo goo, CollisionContext collisionContext) {
	}

	@Override
	public void hitScientist(Scientist scientist,
			CollisionContext collisionContext) {
	}

	@Override
	public void hitZombie(Zombie zombie, CollisionContext collisionContext) {
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.EtherealBoulder);
	}

}
