package com.jhu.oose16.zombieattack.model.factory;

import com.jhu.oose16.zombieattack.model.entity.zombie.NormalZombie;

public class ZombieNormalFactory implements EntityFactory<NormalZombie>{
	@Override
	public NormalZombie newEntity() {
		return new NormalZombie();
	}
}