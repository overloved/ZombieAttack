package com.jhu.oose16.zombieattack.model.entity;

import java.util.List;
import java.util.Random;

import com.jhu.oose16.zombieattack.model.GameModel;
import com.jhu.oose16.zombieattack.model.attached.Position;
import com.jhu.oose16.zombieattack.model.objectstate.DirectionState;
import com.jhu.oose16.zombieattack.model.objectstate.LifeState;
import com.jhu.oose16.zombieattack.model.objectstate.RunningState;

/** Implement the random moving of the zombie and scientist */
public abstract class RandomMovingRectangle extends Rectangle {

	/** The health point of the scientist. */
	private float healthPoint;

	/** The moving region size */
	private final static int MOVING_REGION_SIZE = GameModel
			.getGameFrameHeight() / 2;

	/** The running path */
	protected List<Position> runningPath;

	/** The index of the current position on the running path */
	protected int runningPositionIndex;

	/** The running speed */
	private int runningSpeed;

	/** The deceleration */
	protected double deceleration;

	/** The width when moving up or down */
	private int widthUpDown;

	/** The height when moving up or down */
	private int heightUpDown;

	/** The width when moving right or left */
	private int widthRightLeft;

	/** The height when moving right of left */
	private int heightRightLeft;

	/**
	 * Initialize the width and height and the speed
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
	public RandomMovingRectangle(int widthUpDown, int heightUpDown,
			int widthRightLeft, int heightRightLeft, int runningSpeed,
			int healthPoint, int appearanceCount, int normalCount,
			int disappearanceCount) {
		super(new Position(0, 0), widthUpDown, heightUpDown, appearanceCount,
				normalCount, disappearanceCount);

		this.runningSpeed = runningSpeed;
		this.healthPoint = healthPoint;
		deceleration = 1;

		this.widthRightLeft = widthRightLeft;
		this.heightRightLeft = heightRightLeft;
		this.widthUpDown = widthUpDown;
		this.heightUpDown = heightUpDown;
	}

	/**
	 * Lose some health point based on the damage, if there is no health point,
	 * remove the entity
	 * 
	 * @param Damage
	 *            The damage
	 */
	public void loseHealthPoint(float Damage) {
		if (expirableObjectState.getLifeState() == LifeState.NORMAL) {
			healthPoint -= Damage;
			if (healthPoint <= 0) {
				startDisappear();
			}
		}
	}

	/**
	 * Set the running speed
	 * 
	 * @param speed
	 *            The running speed
	 */
	protected void setSpeed(int speed) {
		this.runningSpeed = speed;
	}

	/**
	 * Get the running speed
	 * 
	 * @return The speed
	 */
	protected int getSpeed() {
		return runningSpeed;
	}

	@Override
	public boolean isRunning() {
		return expirableObjectState.getRunningState() == RunningState.RUNNING;
	}

	/** start running on a new running path */
	public void startRunning() {
		if (expirableObjectState.getLifeState() == LifeState.NORMAL) {
			expirableObjectState.setRunningState(RunningState.RUNNING);
		}
	}

	/** stop running */
	public void stopRunning() {
		expirableObjectState.setRunningState(RunningState.STANDING);
	}

	@Override
	public void update() {
		if (expirableObjectState.getLifeState() != LifeState.DISAPPEARANCE) {
			if (isInvincible() && countToMarkedUpdateCount() > INVINCIBLE_COUNT) {
				turnToNormal();
			}
			if (isRunning()) {
				moveForward();
			}
		}
		super.update();
	}

	/**
	 * move forward
	 * 
	 * @return whether True: did not reach the end of the current running path
	 */
	protected boolean moveForward() {
		runningPositionIndex += getRunningSpeed();
		if (runningPositionIndex >= runningPath.size()) {
			setCenter(runningPath.get(runningPath.size() - 1));
			findRunningPath(5);
			return false;
		} else {
			setCenter(runningPath.get(runningPositionIndex));
			return true;
		}
	}

	/**
	 * deceleration will become to 1, after being used.
	 * 
	 * @return The running speed
	 */
	private int getRunningSpeed() {
		double temp = deceleration;
		deceleration = 1;
		return (int) Math.ceil(temp * runningSpeed);
	}

	/**
	 * Get the running path from the current position to the given position
	 * 
	 * @param position
	 *            The given position
	 * @return The positions on the running path
	 */
	protected abstract List<Position> getLineToTargetPosition(Position position);

	/**
	 * After hit another entity, it running in an opposite side
	 * 
	 * @param entity
	 *            The entity being hit
	 */
	protected void changeRunningPathToOpposite(Entity entity) {
		int side = entity.onWhichSide(getCenter());
		findRunningPath((side + 2) % 4);
	}

	/**
	 * After hit another rectangle, stay close to it
	 * 
	 * @param entity
	 *            The entity being hit
	 */
	protected void stayCloseTo(Entity entity) {
		int side = entity.onWhichSide(getCenter());
		if (side == 0) {
			setCenterY(entity.getTop() - (getHeight() / 2 + 1));
		} else if (side == 1) {
			setCenterX(entity.getRight() + (getWidth() / 2 + 1));
		} else if (side == 2) {
			setCenterY(entity.getBottom() + (getHeight() / 2 + 1));
		} else {
			setCenterX(entity.getLeft() - (getWidth() / 2 + 1));
		}
	}

	/**
	 * Find the new running path starting from the current position.
	 * 
	 * @param side
	 *            The center of move region is the center when side = 5, or (0
	 *            top 1 right 2 bottom 3 left 4 arbitrary 10 top left 11 top
	 *            right 12 down right 13 down left)
	 */
	protected void findRunningPath(int side) {
		// find the random moving region
		int left = getCenterX() - MOVING_REGION_SIZE / 2;
		int top = getCenterY() - MOVING_REGION_SIZE / 2;
		if (side == 0) {
			top += MOVING_REGION_SIZE / 2;
		} else if (side == 1) {
			left -= MOVING_REGION_SIZE / 2;
		} else if (side == 2) {
			top -= MOVING_REGION_SIZE / 2;
		} else if (side == 3) {
			left += MOVING_REGION_SIZE / 2;
		}
		if (side == 10) {
			left += MOVING_REGION_SIZE / 2;
			top += MOVING_REGION_SIZE / 2;
		} else if (side == 11) {
			left -= MOVING_REGION_SIZE / 2;
			top += MOVING_REGION_SIZE / 2;
		} else if (side == 12) {
			left -= MOVING_REGION_SIZE / 2;
			top -= MOVING_REGION_SIZE / 2;
		} else if (side == 13) {
			left += MOVING_REGION_SIZE / 2;
			top -= MOVING_REGION_SIZE / 2;
		}
		// target position
		Random randomGenerator = new Random();
		int TargetX = (int) (randomGenerator.nextDouble() * MOVING_REGION_SIZE + left);
		int TargetY = (int) (randomGenerator.nextDouble() * MOVING_REGION_SIZE + top);

		runningPath = getLineToTargetPosition(new Position(TargetX, TargetY));
		runningPositionIndex = 0;
	}

	@Override
	protected void checkDirection() {
		Position newPoint = runningPath.get(runningPath.size() - 1);
		boolean b1 = newPoint.getX() - newPoint.getY() + getCenterY()
				- getCenterX() < 0;
		boolean b2 = newPoint.getX() + newPoint.getY() - getCenterY()
				- getCenterX() > 0;
		if (b1 && b2) {
			expirableObjectState.setDirectionState(DirectionState.DOWN);
		} else if (!b1 && b2) {
			expirableObjectState.setDirectionState(DirectionState.RIGHT);
		} else if (!b1 && !b2) {
			expirableObjectState.setDirectionState(DirectionState.UP);
		} else if (b1 && !b2) {
			expirableObjectState.setDirectionState(DirectionState.LEFT);
		} else {
			expirableObjectState.setDirectionState(DirectionState.DOWN);
		}
		if (expirableObjectState.getDirectionState() == DirectionState.UP
				|| expirableObjectState.getDirectionState() == DirectionState.DOWN) {
			resetSize(widthUpDown, heightUpDown);
		} else {
			resetSize(widthRightLeft, heightRightLeft);
		}
	}
}