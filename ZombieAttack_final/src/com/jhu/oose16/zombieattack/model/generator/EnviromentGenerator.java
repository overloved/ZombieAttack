package com.jhu.oose16.zombieattack.model.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.jhu.oose16.zombieattack.model.GameModel;
import com.jhu.oose16.zombieattack.model.attached.Position;
import com.jhu.oose16.zombieattack.model.entity.Entity;
import com.jhu.oose16.zombieattack.model.entity.materials.Barrier;
import com.jhu.oose16.zombieattack.model.entity.materials.BarrierLargeHorizontal;
import com.jhu.oose16.zombieattack.model.entity.materials.BarrierLargeVertical;
import com.jhu.oose16.zombieattack.model.entity.materials.BarrierSmallHorizontal;
import com.jhu.oose16.zombieattack.model.entity.materials.BarrierSmallVertical;
import com.jhu.oose16.zombieattack.model.entity.materials.ScreenBound;
import com.jhu.oose16.zombieattack.model.entity.scientist.Scientist;

public class EnviromentGenerator {

	private ScientistGenerator scientistGenerator;

	private List<List<Barrier>> preDesignedBarriers = new ArrayList<List<Barrier>>();
	private List<List<Position>> preDesignedStartPositions = new ArrayList<List<Position>>();

	public EnviromentGenerator() {

		// Map 1
		preDesignedBarriers.add(Arrays.<Barrier> asList(
				new BarrierLargeHorizontal(new Position(200, 300)),
				new BarrierSmallHorizontal(new Position(350, 300)),
				new BarrierLargeVertical(new Position(200, 100)),
				new BarrierSmallVertical(new Position(350, 100))));

		preDesignedStartPositions.add(Arrays.<Position> asList(new Position(
				300, 150), new Position(400, 150), new Position(400, 200),
				new Position(500, 400)));

		// Map 2
		preDesignedBarriers.add(Arrays.<Barrier> asList(
				new BarrierLargeHorizontal(new Position(200, 100)),
				new BarrierSmallHorizontal(new Position(350, 100)),
				new BarrierLargeVertical(new Position(200, 300)),
				new BarrierSmallVertical(new Position(350, 300))));

		preDesignedStartPositions.add(Arrays.<Position> asList(new Position(
				300, 400), new Position(300, 300), new Position(450, 300),
				new Position(450, 400)));

		scientistGenerator = new ScientistGenerator();
	}

	public List<Entity> getBarAndScientist() {
		int mapIndex = new Random().nextInt(preDesignedBarriers.size());
		List<Entity> entities = new ArrayList<Entity>();
		entities.addAll(preDesignedBarriers.get(mapIndex));

		// choose several different random numbers for the start position of
		// scientist
		ArrayList<Integer> randomNumbers = new ArrayList<Integer>();
		for (int i = 0; i < preDesignedStartPositions.get(mapIndex).size(); i++) {
			randomNumbers.add(Integer.valueOf(i));
		}
		Random random = new Random();
		for (int i = 0; i < 2 * randomNumbers.size(); i++) {
			int index = random.nextInt(preDesignedStartPositions.get(mapIndex)
					.size());
			int temp;
			temp = randomNumbers.get(0);
			randomNumbers.set(0, randomNumbers.get(index));
			randomNumbers.set(index, temp);
		}

		List<Scientist> scientists = scientistGenerator.getNewScientists();
		for (int i = 0; i < scientists.size(); i++) {
			scientists.get(i).findFirstRunningPath(
					preDesignedStartPositions.get(mapIndex).get(
							randomNumbers.get(i)));
		}
		entities.addAll(scientists);

		// add four screen bounds
		entities.add(new ScreenBound(new Position(0, -1000), new Position(
				GameModel.getGameFrameWidth(), 2)));
		entities.add(new ScreenBound(new Position(0, GameModel
				.getGameFrameHeight() - 2),
				new Position(GameModel.getGameFrameWidth(), GameModel
						.getGameFrameHeight() - 2 + 1000)));
		entities.add(new ScreenBound(new Position(-1000, 0), new Position(2,
				GameModel.getGameFrameHeight())));
		entities.add(new ScreenBound(new Position(
				GameModel.getGameFrameWidth() - 2, 0), new Position(GameModel
				.getGameFrameWidth() + 1000, GameModel.getGameFrameHeight())));

		return entities;
	}
}
