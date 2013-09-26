package com.jhu.oose16.zombieattack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import com.jhu.oose16.zombieattack.listener.ExpirableObjectManagerListener;
import com.jhu.oose16.zombieattack.listener.ModelListener;
import com.jhu.oose16.zombieattack.listener.ZombieListener;
import com.jhu.oose16.zombieattack.model.attached.Accelerate;
import com.jhu.oose16.zombieattack.model.attached.PauseTask;
import com.jhu.oose16.zombieattack.model.attached.Position;
import com.jhu.oose16.zombieattack.model.entity.boulder.Boulder;
import com.jhu.oose16.zombieattack.model.entity.zombie.Zombie;
import com.jhu.oose16.zombieattack.model.generator.LevelGenerator;
import com.jhu.oose16.zombieattack.model.generator.ZombieGenerator;

/**
 * This class implements the logic behind the game.<br />
 * First it will initialize the position and size of the Barriers, Goos and
 * scientist.<br/>
 * During the first several update loops, the boulder cannot be placed on the
 * screen and during this time the zombies will came into the screen in a fast
 * frequency.<br/>
 * After at, the player can place the boulder on the screen by touching the
 * screen and roll the boulder to kill zombies. And now the zombies will came
 * into the screen in a slow frequency.<br/>
 * After all the boulders are used up, the game ends. <br />
 * 
 * The height for the environment is 900 and the width is 1600
 */
public class GameModel {

	/** the height of the environment */
	private static final int GAME_FRAME_HEIGHT = 480;
	/** the width of the environment */
	private static final int GAME_fRAME_WIDTH = 760;
	/** The entities and effects update frequency */
	private static final int UPDATE_FREQUENCY = 50;

	/** The different state of the game */
	private GameModelState gameModelState;

	/** Generate the level */
	private LevelGenerator levelGenerator;
	/** Level */
	private Level level;

	/** Timer. */
	private Timer timer;
	/** List of all pause tasks */
	private List<PauseTask> pauseTasks;
	private int updateCount;

	/**
	 * Game model listener, for now it is used to update the view when the game
	 * model update
	 */
	private List<ModelListener> gameModelListeners;

	private int currentScore;

	public GameModel() {
		levelGenerator = new LevelGenerator();
		currentScore = 0;
	}

	/** start a new level */
	public void starNextLevel() {
		updateCount = 0;

		level = levelGenerator.getNextLevel();

		level.getExpirableObjectManager().addExpirableObjectManagerListeners(
				new ExpirableObjectManagerListener() {

					@Override
					public void cleanDeadViewObject() {
					}

					@Override
					public void addViewExpirableObject(ExpirableObject expirableObject) {
					}
				});

		gameModelState = GameModelState.PLAY_GAME;

		gameModelListeners = new ArrayList<ModelListener>();

		if (timer != null) {
			timer.cancel();
		}
		timer = new Timer();

		pauseTasks = new ArrayList<PauseTask>();
		PauseTask updateEffectAndEntityTask = new PauseTask() {
			@Override
			public void runIfNotPaused() {

				// long t1 = new Date().getTime();
				// Log.d("Start update entity and effct", "...");
				updateCount++;
				getExpirableObjectManager().updateEntityAndEffect();

				// long t2 = new Date().getTime();
				// Log.d("End update entity and effct", (t2 - t1) + " ms");

				if (updateCount
						% (getZombieGenerator().getAddNewZombieFre() / UPDATE_FREQUENCY) == 1) {

					Zombie newZombie = getZombieGenerator().newEntity();
					newZombie.addZombieListener(new ZombieListener() {
						@Override
						public void changeScore(int change) {
							GameModel.this.addCurrentScore(change);
						}

						@Override
						public void changeBackAddNewZombieFre() {
						}

						@Override
						public void changeAddNewZombieFre(int newFrenewFrequency) {
						}
					});
					getExpirableObjectManager().addEntity(newZombie);
					getExpirableObjectManager().increaseNumLeftZombie();
				}

				isGameEnd();

				// long t3 = new Date().getTime();
				// Log.d("End add new zombie", (t3 - t2) + " ms");

				updateGame();

				// long t4 = new Date().getTime();
				// Log.d("End update view", (t4 - t3) + " ms");
			}
		};
		timer.schedule(updateEffectAndEntityTask, 0, UPDATE_FREQUENCY);
		pauseTasks.add(updateEffectAndEntityTask);
	}

	/** pause game */
	public void pauseGame() {
		if (gameModelState == GameModelState.PLAY_GAME) {
			for (PauseTask p : pauseTasks) {
				p.pause();
			}
			gameModelState = GameModelState.PAUSE_GAME;
		}
	}

	/** resume game */
	public void resumeGame() {
		if (gameModelState == GameModelState.PAUSE_GAME) {
			for (PauseTask p : pauseTasks) {
				p.resume();
			}
			gameModelState = GameModelState.PLAY_GAME;
		}
	}

	/** end game */
	public void endGame() {
		timer.cancel();
	}

	/**
	 * Change the accelerate of the boulders
	 * 
	 * @param accelerate
	 *            Input accelerate
	 */
	public void changeAccelerate(Accelerate accelerate) {
		Boulder usingBoulder = getExpirableObjectManager().getUsingBoulder();
		if (usingBoulder != null) {
			usingBoulder.setAccelerate(accelerate);
		}
	}

	public List<ModelListener> getGameModelListeners() {
		return gameModelListeners;
	}

	public void updateGame() {
		for (ModelListener l : gameModelListeners) {
			l.update(gameModelState);
		}
	}

	/**
	 * Add new boulder into the screen.
	 * 
	 * @param newBoulderPosition
	 */
	public void addNewBoulder(Position newBoulderPosition) {
		getExpirableObjectManager().useTheNextBoulder(newBoulderPosition);
	}

	/**
	 * Whether the game is over
	 * 
	 * @return true when the game over.
	 */
	public boolean isGameEnd() {
		if (gameModelState == GameModelState.PLAY_GAME) {
			if (getExpirableObjectManager().getNumDeadZombie() >= level
					.getZombieNeedToKill()) {
				gameModelState = GameModelState.WIN_ONE_LEVEL;
				return true;
			} else if (getExpirableObjectManager().getNumLeftScientist() <= 0
					|| getExpirableObjectManager().getNumBoulderLeft() == 0
					|| getExpirableObjectManager().getNumLeftZombie() > level
							.getZombieNumberUpperBound()) {
				gameModelState = GameModelState.LOSE;
				return true;
			}
		}
		return false;
	}

	public static int getGameFrameHeight() {
		return GAME_FRAME_HEIGHT;
	}

	public static int getGameFrameWidth() {
		return GAME_fRAME_WIDTH;
	}

	public int getScore() {
		return currentScore;
	}

	public void addCurrentScore(int score) {
		currentScore += score;
	}

	public Level getLevel() {
		return level;
	}

	private ZombieGenerator getZombieGenerator() {
		return level.getZombieGenerator();
	}

	public ExpirableObjectManager getExpirableObjectManager() {
		return level.getExpirableObjectManager();
	}

	public void addModelListener(ModelListener gameModelListener) {
		gameModelListeners.add(gameModelListener);
	}

	public static int getUpdateFrequency() {
		return UPDATE_FREQUENCY;
	}

}