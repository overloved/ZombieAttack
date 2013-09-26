package com.jhu.oose16.zombieattack.model.factory;

import com.jhu.oose16.zombieattack.model.entity.scientist.Tesla;

public class ScientistTeslaFactory implements EntityFactory<Tesla>{
	@Override
	public Tesla newEntity() {
		return new Tesla();
	}
}