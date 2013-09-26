package com.jhu.oose16.zombieattack.model.entity;

import com.jhu.oose16.zombieattack.model.ExpirableObject;
import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.GameModel;
import com.jhu.oose16.zombieattack.model.attached.CollisionContext;
import com.jhu.oose16.zombieattack.model.attached.DetectionContext;
import com.jhu.oose16.zombieattack.model.attached.Position;
import com.jhu.oose16.zombieattack.model.entity.boulder.Boulder;
import com.jhu.oose16.zombieattack.model.entity.materials.Barrier;
import com.jhu.oose16.zombieattack.model.entity.materials.Goo;
import com.jhu.oose16.zombieattack.model.entity.scientist.Scientist;
import com.jhu.oose16.zombieattack.model.entity.zombie.Zombie;
import com.jhu.oose16.zombieattack.model.objectstate.InvincibleState;
import com.jhu.oose16.zombieattack.model.objectstate.LifeState;
import com.jhu.oose16.zombieattack.model.objectstate.RunningState;

/** This class stores information necessary for all entities in the game. */
public abstract class Entity extends ExpirableObject {

	protected static final int INVINCIBLE_COUNT = 2000 / GameModel
			.getUpdateFrequency();

	/** The detection radius */
	protected int detectionRadius;

	public boolean isUnderEffect() {
		return isUnderEffect;
	}

	public void getEffect() {
		isUnderEffect = true;
	}

	public void removeEffect() {
		isUnderEffect = false;
	}

	/** Has effect */
	private boolean isUnderEffect;

	public Entity(int appearanceCount, int normalCount, int disappearanceCount) {
		super(appearanceCount, normalCount, disappearanceCount);

		detectionRadius = 0;
		isUnderEffect = false;
	}

	@Override
	protected void initialObjectType() {
		addExpirableObjectType(ExpirableObjectType.Entity);
	}

	/** Turn to invincible. */
	protected void turnToInvincible() {
		expirableObjectState.setInvincibleState(InvincibleState.INVINCIBLE);
		markUpdateCount();
	}

	/** Turn to normal. */
	protected void turnToNormal() {
		expirableObjectState.setInvincibleState(InvincibleState.NORMAL);
	}

	/**
	 * @return True invincible
	 */
	public boolean isInvincible() {
		return expirableObjectState.getInvincibleState() == InvincibleState.INVINCIBLE;
	}

	/**
	 * @return True Running
	 */
	public boolean isRunning() {
		return expirableObjectState.getRunningState() == RunningState.RUNNING;
	}

	/**
	 * 1. Check the running direction <br/>
	 * 2. Update the state of the object <br/>
	 * 3. Update the listeners
	 */
	@Override
	public void update() {
		// check running direction
		if (isRunning()) {
			checkDirection();
		}
		if (isInvincible() && countToMarkedUpdateCount() > INVINCIBLE_COUNT) {
			expirableObjectState.setInvincibleState(InvincibleState.NORMAL);
		}
		super.update();
	}

	/** Check the four running directions */
	protected abstract void checkDirection();

	/** open the detection region for this entity */
	protected void openDetectionRegion() {
		detectionRadius = GameModel.getGameFrameHeight() / 2;
	}

	/** Get the detection radius */
	public int getDetectionRegion() {
		return detectionRadius;
	}

	/**
	 * Check whether the given entity is collied with the given entity.
	 * 
	 * @param entity
	 *            The given entity
	 * @return True: the given entity in the detection region
	 */
	public boolean checkDetection(Entity entity) {
		return getDetectionRegion() > 0
				&& this.getCenter().distance(entity.getCenter()) < getDetectionRegion();
	}

	/**
	 * Check this current entity collide with the given entity <br\>
	 * Both the current and the given entity could be rectangle or circle.
	 * 
	 * @param entity
	 *            The given entity
	 * @return Whether True: collid
	 */
	public boolean checkCollision(Entity entity) {
		if (this instanceof Circle && entity instanceof Circle) {
			Circle circle1 = (Circle) entity;
			Circle circle2 = (Circle) this;
			int distanceX = Math.abs(circle1.getCenter().getX()
					- circle2.getCenter().getX());
			int distanceY = Math.abs(circle1.getCenter().getY()
					- circle2.getCenter().getY());
			double hyp = Math.hypot(distanceX, distanceY);
			return hyp < circle1.getRadius() + circle2.getRadius();
		} else if (this instanceof Rectangle && entity instanceof Rectangle) {
			Rectangle rectangle1 = (Rectangle) entity;
			Rectangle rectangle2 = (Rectangle) this;
			int A = Math.abs(rectangle1.getRight() + rectangle1.getLeft()
					- rectangle2.getRight() - rectangle2.getLeft());
			int B = rectangle2.getRight() - rectangle2.getLeft()
					+ rectangle1.getRight() - rectangle1.getLeft();
			int C = Math.abs(rectangle1.getBottom() + rectangle1.getTop()
					- rectangle2.getBottom() - rectangle2.getTop());
			int D = rectangle2.getBottom() - rectangle2.getTop()
					+ rectangle1.getBottom() - rectangle1.getTop();
			if (A < B && C < D) {
				return true;
			}
			return false;
		} else {
			Circle circle;
			Rectangle rectangle;
			if (this instanceof Rectangle) {
				rectangle = (Rectangle) this;
				circle = (Circle) entity;
			} else {
				rectangle = (Rectangle) entity;
				circle = (Circle) this;
			}
			int distanceX = Math.abs(circle.getCenter().getX()
					- rectangle.getCenter().getX());
			int distanceY = Math.abs(circle.getCenter().getY()
					- rectangle.getCenter().getY());

			if (distanceX > (rectangle.getWidth() / 2 + circle.getRadius())) {
				return false;
			}
			if (distanceY > (rectangle.getHeight() / 2 + circle.getRadius())) {
				return false;
			}

			if (distanceX < rectangle.getWidth() / 2) {
				return true;
			}
			if (distanceY < rectangle.getHeight() / 2) {
				return true;
			}

			int distanceR = (distanceX - rectangle.getWidth() / 2) ^ 2
					+ (distanceY - rectangle.getHeight() / 2) ^ 2;

			return (distanceR < circle.getRadius() * circle.getRadius());
		}
	}

	/**
	 * Find out the given position is on which side of this rectangle
	 * 
	 * @return top(0), right(1), bottom(2), left(3)
	 */
	public int onWhichSide(Position position) {
		Position leftTop = new Position(getLeft(), getTop());
		Position leftBottom = new Position(getLeft(), getBottom());
		Position rightTop = new Position(getRight(), getTop());
		Position rightBottom = new Position(getRight(), getBottom());

		boolean topRight = position.aboveLine(leftTop, rightBottom);
		boolean topLeft = position.aboveLine(leftBottom, rightTop);

		if (topLeft && topRight) {
			return 0;
		} else if (topRight && !topLeft) {
			return 1;
		} else if (!topLeft && !topRight) {
			return 2;
		} else {
			return 3;
		}
	}



	public abstract int getLeft();

	public abstract int getRight();

	public abstract int getTop();

	public abstract int getBottom();

	public abstract Position getCenter();

	public Position getTopLeft() {
		return new Position(getLeft(), getTop());
	}

	public Position getTopRight() {
		return new Position(getRight(), getTop());
	}

	public Position getBottomRight() {
		return new Position(getRight(), getBottom());
	}

	public Position getBottomLeft() {
		return new Position(getLeft(), getBottom());
	}

	/**
	 * When hit the entity
	 * 
	 * @param entity
	 *            The entity being hit
	 * @param collisionContext
	 *            The context for collision
	 */
	public void applyCollision(Entity entity, CollisionContext collisionContext) {
		if (expirableObjectState.getLifeState() == LifeState.NORMAL) {

			if (entity.isExpirableObjectType(ExpirableObjectType.Barrier)) {
				hitBarrier((Barrier) entity, collisionContext);
			} else if (entity.isExpirableObjectType(ExpirableObjectType.Goo)) {
				hitGoo((Goo) entity, collisionContext);
			} else if (entity.isExpirableObjectType(ExpirableObjectType.Zombie)) {
				hitZombie((Zombie) entity, collisionContext);
			} else if (entity
					.isExpirableObjectType(ExpirableObjectType.Scientist)) {
				hitScientist((Scientist) entity, collisionContext);
			} else if (entity
					.isExpirableObjectType(ExpirableObjectType.Boulder)) {
				hitBoulder((Boulder) entity, collisionContext);
			}
			updateListener();
		}
	}

	/**
	 * When detect entity
	 * 
	 * @param entity
	 *            The entity being detected
	 * @param collisionContext
	 *            The context for detection
	 */
	public void applyDetection(Entity entity, DetectionContext detectionContext) {
		if (expirableObjectState.getLifeState() == LifeState.NORMAL) {
			if (entity.isExpirableObjectType(ExpirableObjectType.Barrier)) {
				detectBarrier((Barrier) entity, detectionContext);
			} else if (entity.isExpirableObjectType(ExpirableObjectType.Goo)) {
				detectGoo((Goo) entity, detectionContext);
			} else if (entity.isExpirableObjectType(ExpirableObjectType.Zombie)) {
				detectZombie((Zombie) entity, detectionContext);
			} else if (entity
					.isExpirableObjectType(ExpirableObjectType.Scientist)) {
				detectScientist((Scientist) entity, detectionContext);
			} else if (entity
					.isExpirableObjectType(ExpirableObjectType.Boulder)) {
				detectBoulder((Boulder) entity, detectionContext);
			}
			updateListener();
		}
	}

	/** React when detect the boulder */
	protected void detectBoulder(Boulder boulder,
			DetectionContext detectionContext) {
	}

	/** React when detect the scientist */
	protected void detectScientist(Scientist scientist,
			DetectionContext detectionContext) {
	}

	/** React when detect the zombie */
	protected void detectZombie(Zombie zombie, DetectionContext detectionContext) {
	}

	/** React when detect the goo */
	protected void detectGoo(Goo goo, DetectionContext detectionContext) {
	}

	/** React when detect the barrier */
	protected void detectBarrier(Barrier barrier,
			DetectionContext detectionContext) {
	}

	/** React when hit the barrier */
	public abstract void hitBarrier(Barrier barrier,
			CollisionContext collisionContext);

	/** React when hit the goo */
	public abstract void hitGoo(Goo goo, CollisionContext collisionContext);

	/** React when hit the zombie */
	public abstract void hitZombie(Zombie zombie,
			CollisionContext collisionContext);

	/** React when hit the scientist */
	public abstract void hitScientist(Scientist scientist,
			CollisionContext collisionContext);

	/** React when hit the boulder */
	public abstract void hitBoulder(Boulder boulder,
			CollisionContext collisionContext);

}
