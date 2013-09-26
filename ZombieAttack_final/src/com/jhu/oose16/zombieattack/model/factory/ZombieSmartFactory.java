package com.jhu.oose16.zombieattack.model.factory;

import com.jhu.oose16.zombieattack.model.entity.zombie.SmartZombie;

public class ZombieSmartFactory implements EntityFactory<SmartZombie>{

	@Override
	public SmartZombie newEntity() {
		return new SmartZombie();
	}

}
