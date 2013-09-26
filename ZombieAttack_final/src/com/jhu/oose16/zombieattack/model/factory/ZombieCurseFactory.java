package com.jhu.oose16.zombieattack.model.factory;

import com.jhu.oose16.zombieattack.model.entity.zombie.CurseZombie;

public class ZombieCurseFactory implements EntityFactory<CurseZombie>{
	@Override
	public CurseZombie newEntity() {
		return new CurseZombie();
	}
}
