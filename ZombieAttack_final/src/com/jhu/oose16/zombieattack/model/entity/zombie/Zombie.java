package com.jhu.oose16.zombieattack.model.entity.zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jhu.oose16.zombieattack.listener.ZombieListener;
import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.GameModel;
import com.jhu.oose16.zombieattack.model.attached.CleanupContext;
import com.jhu.oose16.zombieattack.model.attached.CollisionContext;
import com.jhu.oose16.zombieattack.model.attached.Position;
import com.jhu.oose16.zombieattack.model.entity.RandomMovingRectangle;
import com.jhu.oose16.zombieattack.model.entity.boulder.Boulder;
import com.jhu.oose16.zombieattack.model.entity.materials.Barrier;
import com.jhu.oose16.zombieattack.model.entity.materials.Goo;
import com.jhu.oose16.zombieattack.model.entity.scientist.Scientist;
import com.jhu.oose16.zombieattack.model.objectstate.LifeState;

/**
 * Zombie can moving randomly and when it hits the barriers, it can choose
 * another randomly moving path.
 */
public abstract class Zombie extends RandomMovingRectangle {

	/** The damage to the scientist */
	private int zombieDamage;

	/** The zombie listeners */
	protected List<ZombieListener> zombieListeners;

	/**
	 * Find the starting position
	 * 
	 * @param widthUpDown
	 *            The width when moving up or down
	 * @param heightUpDown
	 *            The height when moving up or down
	 * @param widthRightLeft
	 *            The width when moving right or left
	 * @param heightRightLeft
	 *            The height when moving right or left
	 * @param runningSpeed
	 *            The running speed
	 * @param healthPoint
	 *            The health point
	 * @param appearanceCount
	 *            The appearance state count
	 * @param normalCount
	 *            The normal state count
	 * @param disappearanceCount
	 *            The disappearance state count
	 */
	public Zombie(int widthUpDown, int heightUpDown, int widthRightLeft,
			int heightRightLeft, int runningSpeed, int healthPoint,
			int zombieDamage, int appearanceCount, int normalCount,
			int disappearanceCount) {
		super(widthUpDown, heightUpDown, widthRightLeft, heightRightLeft,
				runningSpeed, healthPoint, appearanceCount, normalCount,
				disappearanceCount);

		this.zombieDamage = zombieDamage;

		zombieListeners = new ArrayList<ZombieListener>();

		// find the starting position
		ArrayList<Position> tempArrayList = new ArrayList<Position>();
		tempArrayList.add(new Position(getWidth(), getHeight()));
		tempArrayList.add(new Position(GameModel.getGameFrameWidth()
				- getWidth(), getHeight()));
		tempArrayList.add(new Position(GameModel.getGameFrameWidth()
				- getWidth(), GameModel.getGameFrameHeight() - getHeight()));
		tempArrayList.add(new Position(getWidth(), GameModel
				.getGameFrameHeight() - getHeight()));
		Random random = new Random();
		int enterPosition = random.nextInt(tempArrayList.size());

		setCenter(tempArrayList.get(enterPosition));
		findRunningPath(enterPosition + 10);

		startRunning();
	}
	
	public abstract int score();

	@Override
	protected List<Position> getLineToTargetPosition(Position position) {
		return getCenter().linePosition(position);
	}

	public int getDamage() {
		return zombieDamage;
	}

	@Override
	public void hitBarrier(Barrier barrier, CollisionContext collisionContext) {
		stayCloseTo(barrier);
		changeRunningPathToOpposite(barrier);
	}

	@Override
	public void hitZombie(Zombie zombie, CollisionContext collisionContext) {
		changeRunningPathToOpposite(zombie);
	}

	@Override
	public void hitBoulder(Boulder boulder, CollisionContext collisionContext) {
		changeRunningPathToOpposite(boulder);
		if (!isInvincible()
				&& getObjectState().getLifeState() == LifeState.NORMAL) {
			turnToInvincible();
			loseHealthPoint(boulder.getDamage());
		}
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.Zombie);
	}

	@Override
	public void cleanup(CleanupContext cleanupContext) {
		cleanupContext.trackDeadAndLeftZombieCount();
		for (int i = 0; i < zombieListeners.size(); i++) {
			zombieListeners.get(i).changeScore(score());
		}
	}

	@Override
	public void hitScientist(Scientist scientist,
			CollisionContext collisionContext) {
		if (!scientist.isInvincible()) {
			changeRunningPathToOpposite(scientist);
		}
	}

	@Override
	public void hitGoo(Goo goo, CollisionContext collisionContext) {
		this.deceleration = Goo.DECELERATION;
	}

	public void addZombieListener(ZombieListener zombieListener) {
		zombieListeners.add(zombieListener);
	}

	public void responseToCreation() {
	}
}