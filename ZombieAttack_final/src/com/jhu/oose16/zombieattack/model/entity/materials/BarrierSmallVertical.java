package com.jhu.oose16.zombieattack.model.entity.materials;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.attached.Position;

public class BarrierSmallVertical extends Barrier {
	private final static int width = 30;

	private final static int height = 100;

	public BarrierSmallVertical(Position topLeft) {
		super(topLeft, new Position(topLeft.getX() + width, topLeft.getY()
				+ height));
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.BarrierSmallVertical);
	}

}
