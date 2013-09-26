package com.jhu.oose16.zombieattack.model.entity.materials;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.attached.Position;

public class BarrierSmallHorizontal extends Barrier {

	private final static int width = 100;

	private final static int height = 30;

	public BarrierSmallHorizontal(Position topLeft) {
		super(topLeft, new Position(topLeft.getX() + width, topLeft.getY()
				+ height));
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.BarrierSmallHorizontal);
	}

}