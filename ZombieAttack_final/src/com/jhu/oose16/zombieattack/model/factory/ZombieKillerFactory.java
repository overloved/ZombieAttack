package com.jhu.oose16.zombieattack.model.factory;

import com.jhu.oose16.zombieattack.model.entity.zombie.KillerZombie;

public class ZombieKillerFactory implements EntityFactory<KillerZombie>{
	@Override
	public KillerZombie newEntity() {
		return new KillerZombie();
	}
}