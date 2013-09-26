package com.jhu.oose16.zombieattack.model.entity.scientist;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;

/** normal scientist */
public class NormalScientist extends Scientist {

	private static final int WIDTH_LEFT_RIGHT = 21;
	private static final int HEIGHT_LEFT_RIGHT = 54;
	private static final int WIDTH_UP_DOWN = 32;
	private static final int HEIGHT_UP_DOWN = 56;
	private static final int DESTRUCTION_PEROID = 12;

	public NormalScientist() {
		super(WIDTH_UP_DOWN, HEIGHT_UP_DOWN, WIDTH_LEFT_RIGHT,
				HEIGHT_LEFT_RIGHT, -1, -1, DESTRUCTION_PEROID);
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.NormalScientist);
	}
}