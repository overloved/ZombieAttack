package com.jhu.oose16.zombieattack.model.generator;

import java.util.ArrayList;
import java.util.List;

import com.jhu.oose16.zombieattack.model.entity.Entity;
import com.jhu.oose16.zombieattack.model.entity.boulder.Boulder;
import com.jhu.oose16.zombieattack.model.factory.BoulderEtherealFactory;
import com.jhu.oose16.zombieattack.model.factory.BoulderFireFactory;
import com.jhu.oose16.zombieattack.model.factory.BoulderHoverFactory;
import com.jhu.oose16.zombieattack.model.factory.BoulderIceFactory;
import com.jhu.oose16.zombieattack.model.factory.BoulderNormalFactory;
import com.jhu.oose16.zombieattack.model.factory.EntityFactory;
import com.jhu.oose16.zombieattack.model.factory.WeightedEntityFactory;

public class BoulderGenerator implements EntityFactory<Boulder> {

	private static final int NORMAL_BOULDER_WEIGHT = 2;
	private static final int ICE_BOULDER_WEIGHT = 2;
	private static final int FIRE_BOULDER_WEIGHT = 2;
	private static final int HOVER_BOULDER_WEIGHT = 1;
	private static final int ETHEREAL_BOULDER_WEIGHT = 1;
	
	/** The number of boulder in one level */
	private static final int BOULDER_NUMBER = 3;

	private EntityGenerator<Boulder> entityGenerator;

	public BoulderGenerator() {
		List<Integer> boulderWeight = new ArrayList<Integer>();
		boulderWeight.add(NORMAL_BOULDER_WEIGHT);
		boulderWeight.add(ICE_BOULDER_WEIGHT);
		boulderWeight.add(FIRE_BOULDER_WEIGHT);
		boulderWeight.add(HOVER_BOULDER_WEIGHT);
		boulderWeight.add(ETHEREAL_BOULDER_WEIGHT);

		/** The factories for all kinds of boulder */
		List<EntityFactory<? extends Boulder>> boulderFactories = new ArrayList<EntityFactory<? extends Boulder>>();
		boulderFactories.add(new BoulderNormalFactory());
		boulderFactories.add(new BoulderIceFactory());
		boulderFactories.add(new BoulderFireFactory());
		boulderFactories.add(new BoulderHoverFactory());
		boulderFactories.add(new BoulderEtherealFactory());

		List<WeightedEntityFactory<? extends Boulder>> weightedboulderFactories = new ArrayList<WeightedEntityFactory<? extends Boulder>>();
		for (int i = 0; i < boulderFactories.size(); i++) {
			weightedboulderFactories.add(new WeightedEntityFactory<Boulder>(
					boulderWeight.get(i), boulderFactories.get(i)));
		}

		entityGenerator = new EntityGenerator<Boulder>(weightedboulderFactories);
	}

	@Override
	public Boulder newEntity() {
		return entityGenerator.newEntity();
	}

	public List<Entity> getNewBoulders() {
		List<Entity> boulders = new ArrayList<Entity>();
		for (int i = 0; i < BOULDER_NUMBER; i++) {
			boulders.add(newEntity());
		}
		return boulders;
	}
}
