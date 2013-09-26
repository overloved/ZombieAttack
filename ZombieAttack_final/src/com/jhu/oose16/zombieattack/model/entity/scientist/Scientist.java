package com.jhu.oose16.zombieattack.model.entity.scientist;

import java.util.List;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.attached.CleanupContext;
import com.jhu.oose16.zombieattack.model.attached.CollisionContext;
import com.jhu.oose16.zombieattack.model.attached.Position;
import com.jhu.oose16.zombieattack.model.entity.RandomMovingRectangle;
import com.jhu.oose16.zombieattack.model.entity.boulder.Boulder;
import com.jhu.oose16.zombieattack.model.entity.materials.Barrier;
import com.jhu.oose16.zombieattack.model.entity.materials.Goo;
import com.jhu.oose16.zombieattack.model.entity.zombie.Zombie;
import com.jhu.oose16.zombieattack.model.objectstate.LifeState;

/**
 * This class represents the scientists.<br/>
 * The position which is near a barrier or screen edge is a "safe position".<br/>
 * The scientist is represent by a square.<br/>
 * When the scientist is collision with a zombie, he will choose a random
 * position and run to it. When hit a barrier or the screen edge, he will stop.<br/>
 * He cannot be attacked for 1 second after being attacked.<br/>
 * The scientist will appear at a given position at the beginning of the game
 * and run to a random position until hit the edge or barrier.
 */
public abstract class Scientist extends RandomMovingRectangle {

	private static final int HEALTH_POINT = 3;
	private static final int RUNNING_SPEED = 3;

	/**
	 * Initialize the running speed and health point
	 * 
	 * @param widthUpDown
	 *            The width when moving up or down
	 * @param heightUpDown
	 *            The height when moving up or down
	 * @param widthRightLeft
	 *            The width when moving right or left
	 * @param heightRightLeft
	 *            The height when moving right or left
	 * @param appearanceCount
	 *            The appearance state count
	 * @param normalCount
	 *            The normal state count
	 * @param disappearanceCount
	 *            The disappearance state count
	 */
	public Scientist(int widthUpDown, int heightUpDown, int widthRightLeft,
			int heightRightLeft, int appearanceCount, int normalCount,
			int disappearanceCount) {
		super(widthUpDown, heightUpDown, widthRightLeft, heightRightLeft,
				RUNNING_SPEED, HEALTH_POINT, appearanceCount, normalCount,
				disappearanceCount);

		startRunning();

		turnToInvincible();
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.Scientist);
	}

	@Override
	protected List<Position> getLineToTargetPosition(Position position) {
		return getCenter().linePosition(position, -1);
	}

	/**
	 * Find the first running path from the start position
	 * 
	 * @param startPosition
	 *            The start position
	 */
	public void findFirstRunningPath(Position startPosition) {
		setCenter(startPosition);
		findRunningPath(5);
	}

	@Override
	public void hitZombie(Zombie zombie, CollisionContext collisionContext) {
		if (!isInvincible()
				&& getObjectState().getLifeState() == LifeState.NORMAL) {
			turnToInvincible();
			loseHealthPoint(zombie.getDamage());
			if (isRunning()) {
				changeRunningPathToOpposite(zombie);
			} else {
				startRunning();
			}
		}
	}

	@Override
	public void hitBarrier(Barrier barrier, CollisionContext collisionContext) {
		stopRunning();
		stayCloseTo(barrier);
		changeRunningPathToOpposite(barrier);
	}

	@Override
	public void hitScientist(Scientist scientist,
			CollisionContext collisionContext) {
		if (isRunning()) {
			changeRunningPathToOpposite(scientist);
		}
	}

	@Override
	public void hitBoulder(Boulder boulder, CollisionContext collisionContext) {
		if (!isInvincible()
				&& !boulder.isExpirableObjectType(ExpirableObjectType.EtherealBoulder)) {
			startDisappear();
		}
	}
	
	@Override
	public void cleanup(CleanupContext cleanupContext) {
		cleanupContext.trackDeadScientistCount();
	}

	@Override
	public void hitGoo(Goo goo, CollisionContext collisionContext) {
		if (!isInvincible()) {
			this.deceleration = Goo.DECELERATION;
		}
	}
}