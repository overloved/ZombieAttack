package com.jhu.oose16.zombieattack.model.generator;

import com.jhu.oose16.zombieattack.model.Level;

/**
 * Generate the level, there are infinity levels in the game, the first five
 * levels have different difficulties, the level after 5 have the same
 * difficulty. <br/>
 * Given a level number it will return a object of level.
 */
public class LevelGenerator {

	private EnviromentGenerator enviromentGenerator = new EnviromentGenerator();
	private BoulderGenerator boulderGenerator = new BoulderGenerator();

	private int currentLevelNum = 0;

	private Level getLevel(int levelNum) {
		return new Level(new ZombieGenerator(levelNum),
				enviromentGenerator.getBarAndScientist(), boulderGenerator.getNewBoulders());
	}

	public Level getNextLevel() {
		currentLevelNum++;
		return getLevel(currentLevelNum);
	}
}
