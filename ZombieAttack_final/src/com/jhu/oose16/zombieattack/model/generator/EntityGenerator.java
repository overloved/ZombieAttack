package com.jhu.oose16.zombieattack.model.generator;

import java.util.ArrayList;
import java.util.List;

import com.jhu.oose16.zombieattack.model.entity.Entity;
import com.jhu.oose16.zombieattack.model.factory.WeightedEntityFactory;

public class EntityGenerator<E extends Entity> {
	private int sumWeight = 0;
	
	private List<WeightedEntityFactory<? extends E>> entityFactories = new ArrayList<WeightedEntityFactory<? extends E>>();

	public EntityGenerator(List<WeightedEntityFactory<? extends E>> entityFactories) {
		this.entityFactories = entityFactories;
		for (int i = 0; i < entityFactories.size(); i++) {
			sumWeight += entityFactories.get(i).getWeight();
		}
	}

	public E newEntity() {
		double temp = Math.random() * sumWeight;
		for (WeightedEntityFactory<? extends E> factory : this.entityFactories) {
			temp -= factory.getWeight();
			if (temp < 0) {
				return factory.newEntity();
			}
		}
		throw new IllegalStateException("Weights did not add up to one!");
	}
	
	public List<Double> getWeight() {
		List<Double> weight = new ArrayList<Double>();
		for (int i = 0; i < entityFactories.size(); i++) {
			weight.add((double) entityFactories.get(i).getWeight()/sumWeight);
		}
		return weight;
	}
}
