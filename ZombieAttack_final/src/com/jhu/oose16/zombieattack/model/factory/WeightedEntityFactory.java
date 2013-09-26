package com.jhu.oose16.zombieattack.model.factory;

import com.jhu.oose16.zombieattack.model.entity.Entity;

public class WeightedEntityFactory<E extends Entity> implements
		EntityFactory<E> {
	private int weight;
	private EntityFactory<? extends E> factory;

	public WeightedEntityFactory(int weight, EntityFactory<? extends E> factory) {
		this.weight = weight;
		this.factory = factory;
	}

	public int getWeight() {
		return weight;
	}

	public EntityFactory<? extends E> getFactory() {
		return factory;
	}

	@Override
	public E newEntity() {
		return this.factory.newEntity();
	}
}
