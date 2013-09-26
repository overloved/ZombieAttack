package com.jhu.oose16.zombieattack.model.entity.materials;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.attached.Position;

public class ScreenBound extends Barrier {
	public ScreenBound(Position topLeft, Position bottomRight) {
		super(topLeft, bottomRight);
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.ScreenBound);
	}
	
}
