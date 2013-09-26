package com.jhu.oose16.zombieattack.model.entity.boulder;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;

public class NormalBoulder extends Boulder {

	private static final int RADIUS = 28;
	private static final int APPEARANCE_COUNT = 8;
	private static final int DISAPPEARANCE_COUNT = 10;

	public NormalBoulder() {
		super(RADIUS, APPEARANCE_COUNT, DISAPPEARANCE_COUNT);
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.NormalBoulder);
	}
}
