package com.jhu.oose16.zombieattack.model.effect;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.GameModel;
import com.jhu.oose16.zombieattack.model.entity.zombie.Zombie;

/** The fire effect on the zombie */
public class FireBoulderToZombieEffect extends Effect {

	/** The damage during each update */
	private float damagePerUpdate;

	private final static int APPEARANCE_COUNT = 6;
	private final static int NORMAL_COUNT = 1000 / GameModel
			.getUpdateFrequency();
	private final static int DISAPPEARANCE_COUNT = 4;

	public FireBoulderToZombieEffect(Zombie zombie, int damageOverTime) {
		super(zombie, APPEARANCE_COUNT, NORMAL_COUNT, DISAPPEARANCE_COUNT);
		damagePerUpdate = damageOverTime / (float) NORMAL_COUNT;
	}

	@Override
	public void update() {
		((Zombie) getEntity()).loseHealthPoint(damagePerUpdate);
		super.update();
	}

	@Override
	protected void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.fireBoulderToZombieEffect);
	}

}
