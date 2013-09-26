package com.jhu.oose16.zombieattack.model.factory;

import com.jhu.oose16.zombieattack.model.entity.zombie.GiantZombie;

public class ZombieGiantFactory implements EntityFactory<GiantZombie> {
	@Override
	public GiantZombie newEntity() {
		return new GiantZombie();
	}
}
