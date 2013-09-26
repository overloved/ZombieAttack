package com.jhu.oose16.zombieattack.model.factory;

import com.jhu.oose16.zombieattack.model.entity.zombie.LeaderZombie;

public class ZombieLeaderFactory implements EntityFactory<LeaderZombie>{
	@Override
	public LeaderZombie newEntity() {
		return new LeaderZombie();
	}
}
