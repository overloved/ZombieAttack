package com.jhu.oose16.zombieattack.model.generator;

import java.util.ArrayList;
import java.util.List;

import com.jhu.oose16.zombieattack.model.entity.scientist.Scientist;
import com.jhu.oose16.zombieattack.model.factory.EntityFactory;
import com.jhu.oose16.zombieattack.model.factory.ScientistNormalFactory;
import com.jhu.oose16.zombieattack.model.factory.ScientistTeslaFactory;
import com.jhu.oose16.zombieattack.model.factory.WeightedEntityFactory;

/**
 * Generate new scientist by the given probability.
 */
public class ScientistGenerator implements EntityFactory<Scientist> {
	/** The appearance probability of Tesla */
	private static final int TESLA_APPEAR_WEIGHT = 1;
	/** The appearance probability of normal scientist */
	private static final int NORMAL_APPEAR_WEIGHT = 1;

	/** The number of scientist in one level */
	private static final int SCIENTIST_NUMBER = 3;

	private EntityGenerator<Scientist> entityGenerator;

	public ScientistGenerator() {
		List<Integer> scientistAppearInterval = new ArrayList<Integer>();
		scientistAppearInterval.add(TESLA_APPEAR_WEIGHT);
		scientistAppearInterval.add(NORMAL_APPEAR_WEIGHT);

		/** The factories for all kinds of scientist */
		ArrayList<EntityFactory<? extends Scientist>> scientistFactories = new ArrayList<EntityFactory<? extends Scientist>>();
		scientistFactories.add(new ScientistTeslaFactory());
		scientistFactories.add(new ScientistNormalFactory());

		List<WeightedEntityFactory<? extends Scientist>> weightedScientistFactories = new ArrayList<WeightedEntityFactory<? extends Scientist>>();
		for (int i = 0; i < scientistFactories.size(); i++) {
			weightedScientistFactories
					.add(new WeightedEntityFactory<Scientist>(
							scientistAppearInterval.get(i), scientistFactories
									.get(i)));
		}

		entityGenerator = new EntityGenerator<Scientist>(
				weightedScientistFactories);
	}

	@Override
	public Scientist newEntity() {
		return entityGenerator.newEntity();
	}

	public List<Scientist> getNewScientists() {
		List<Scientist> scientists = new ArrayList<Scientist>();
		for (int i = 0; i < SCIENTIST_NUMBER; i++) {
			scientists.add(newEntity());
		}
		return scientists;
	}

}
