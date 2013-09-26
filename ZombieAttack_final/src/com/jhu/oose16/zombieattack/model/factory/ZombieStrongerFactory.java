package com.jhu.oose16.zombieattack.model.factory;

import com.jhu.oose16.zombieattack.model.entity.zombie.StrongerZombie;

public class ZombieStrongerFactory implements EntityFactory<StrongerZombie>{
	@Override
	public StrongerZombie newEntity() {
		return new StrongerZombie();
	}
}