package com.jhu.oose16.zombieattack.model.effect;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.entity.zombie.Zombie;

public class IceBoulderToZombieEffect extends Effect {

	private final static int APPEARANCE_COUNT = 5;
	private final static int NORMAL_COUNT = 50;
	private final static int DISAPPEARANCE_COUNT = 5;
	
	public IceBoulderToZombieEffect(Zombie zombie) {
		super(zombie, APPEARANCE_COUNT, NORMAL_COUNT, DISAPPEARANCE_COUNT);
		zombie.stopRunning();
	}
	
	@Override
	public void startDisappear() {
		((Zombie) getEntity()).startRunning();
		super.startDisappear();
	}
	
	@Override
	protected void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.iceBoulderToZombieEffect);
	}
	
}
