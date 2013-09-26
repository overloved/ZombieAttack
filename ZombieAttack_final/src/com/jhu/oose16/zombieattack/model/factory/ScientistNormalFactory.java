package com.jhu.oose16.zombieattack.model.factory;

import com.jhu.oose16.zombieattack.model.entity.scientist.NormalScientist;

public class ScientistNormalFactory implements EntityFactory<NormalScientist> {
	@Override
	public NormalScientist newEntity() {
		return new NormalScientist();
	}
}