package com.jhu.oose16.zombieattack.model.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jhu.oose16.zombieattack.listener.ZombieListener;
import com.jhu.oose16.zombieattack.model.entity.zombie.Zombie;
import com.jhu.oose16.zombieattack.model.factory.EntityFactory;
import com.jhu.oose16.zombieattack.model.factory.WeightedEntityFactory;
import com.jhu.oose16.zombieattack.model.factory.ZombieCurseFactory;
import com.jhu.oose16.zombieattack.model.factory.ZombieFastFactory;
import com.jhu.oose16.zombieattack.model.factory.ZombieGiantFactory;
import com.jhu.oose16.zombieattack.model.factory.ZombieKillerFactory;
import com.jhu.oose16.zombieattack.model.factory.ZombieLeaderFactory;
import com.jhu.oose16.zombieattack.model.factory.ZombieNormalFactory;
import com.jhu.oose16.zombieattack.model.factory.ZombieSmartFactory;
import com.jhu.oose16.zombieattack.model.factory.ZombieStrongerFactory;

/**
 * Generate new zombie by the given probability.
 */
public class ZombieGenerator implements EntityFactory<Zombie> {
	private static final int NUM_LEVEL_DIFFERENT = 5;

	private final static int ADD_NEW_ZOMBIE_FRE = 1000 * 3;

	private int currentAddNewZombieFre;

	private EntityGenerator<Zombie> entityGenerator;

	// normal, fast, smart, stronger, killer, curse, giant, leader
	@SuppressWarnings("unchecked")
	private final static List<List<Integer>> zombieAppearInterval = Arrays
			.asList(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0), // not using
					Arrays.asList(1, 0, 0, 0, 2, 0, 0, 0), // 1
					Arrays.asList(0, 1, 2, 0, 0, 0, 0, 0), // 2
					Arrays.asList(0, 0, 0, 0, 0, 2, 0, 0), // 3
					Arrays.asList(0, 0, 0, 1, 0, 0, 1, 1), // 4
					Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1));// 5

	public ZombieGenerator(int levelNum) {

		currentAddNewZombieFre = ADD_NEW_ZOMBIE_FRE;
		if (levelNum >= NUM_LEVEL_DIFFERENT) {
			levelNum = NUM_LEVEL_DIFFERENT;
		}
		/** The factories for all kinds of zombie */
		ArrayList<EntityFactory<? extends Zombie>> zombieFactories = new ArrayList<EntityFactory<? extends Zombie>>();
		zombieFactories.add(new ZombieNormalFactory());
		zombieFactories.add(new ZombieFastFactory());
		zombieFactories.add(new ZombieSmartFactory());
		zombieFactories.add(new ZombieStrongerFactory());
		zombieFactories.add(new ZombieKillerFactory());
		zombieFactories.add(new ZombieCurseFactory());
		zombieFactories.add(new ZombieGiantFactory());
		zombieFactories.add(new ZombieLeaderFactory());

		List<WeightedEntityFactory<? extends Zombie>> weightedZombieFactories = new ArrayList<WeightedEntityFactory<? extends Zombie>>();
		for (int i = 0; i < zombieFactories.size(); i++) {
			weightedZombieFactories.add(new WeightedEntityFactory<Zombie>(
					zombieAppearInterval.get(levelNum).get(i), zombieFactories
							.get(i)));
		}

		entityGenerator = new EntityGenerator<Zombie>(weightedZombieFactories);
	}

	@Override
	public Zombie newEntity() {
		Zombie newZombie = entityGenerator.newEntity();
		newZombie.addZombieListener(new ZombieListener() {

			@Override
			public void changeBackAddNewZombieFre() {
				ZombieGenerator.this.currentAddNewZombieFre = ADD_NEW_ZOMBIE_FRE;

			}

			@Override
			public void changeAddNewZombieFre(int newFrequency) {
				ZombieGenerator.this.currentAddNewZombieFre = newFrequency;

			}

			@Override
			public void changeScore(int change) {				
			}
		});
		newZombie.responseToCreation();
		return newZombie;
	}

	public int getAddNewZombieFre() {
		return currentAddNewZombieFre;
	}

	public List<Double> getWeight() {
		return entityGenerator.getWeight();
	}

}