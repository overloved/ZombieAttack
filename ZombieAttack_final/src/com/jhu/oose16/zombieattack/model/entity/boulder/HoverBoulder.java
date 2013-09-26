package com.jhu.oose16.zombieattack.model.entity.boulder;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.attached.CollisionContext;
import com.jhu.oose16.zombieattack.model.entity.materials.Goo;

public class HoverBoulder extends Boulder {

	private static final int RADIUS = 27;
	private static final int APPEARANCE_COUNT = 12;
	private static final int DISAPPEARANCE_COUNT = 12;

	public HoverBoulder() {
		super(RADIUS, APPEARANCE_COUNT, DISAPPEARANCE_COUNT);
	}

	@Override
	public void hitGoo(Goo goo, CollisionContext collisionContext) {
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.HoverBoulder);
	}
}
