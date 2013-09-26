package com.jhu.oose16.zombieattack.model.factory;

import com.jhu.oose16.zombieattack.model.entity.boulder.Boulder;
import com.jhu.oose16.zombieattack.model.entity.boulder.FireBoulder;

public class BoulderFireFactory implements EntityFactory<Boulder>{

	@Override
	public Boulder newEntity() {
		return new FireBoulder();
	}

}
