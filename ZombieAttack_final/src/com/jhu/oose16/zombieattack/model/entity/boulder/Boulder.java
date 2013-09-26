package com.jhu.oose16.zombieattack.model.entity.boulder;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.GameModel;
import com.jhu.oose16.zombieattack.model.attached.Accelerate;
import com.jhu.oose16.zombieattack.model.attached.CollisionContext;
import com.jhu.oose16.zombieattack.model.attached.Position;
import com.jhu.oose16.zombieattack.model.attached.Speed;
import com.jhu.oose16.zombieattack.model.attached.Vector;
import com.jhu.oose16.zombieattack.model.entity.Circle;
import com.jhu.oose16.zombieattack.model.entity.Entity;
import com.jhu.oose16.zombieattack.model.entity.materials.Barrier;
import com.jhu.oose16.zombieattack.model.entity.materials.Goo;
import com.jhu.oose16.zombieattack.model.entity.scientist.Scientist;
import com.jhu.oose16.zombieattack.model.entity.zombie.Zombie;
import com.jhu.oose16.zombieattack.model.objectstate.DirectionState;
import com.jhu.oose16.zombieattack.model.objectstate.LifeState;

/**
 * This class implements game boulders.<br\>
 * The boulder is controlled by the accelerate which is decided by the motion of
 * the screen. <br\>
 * When a boulder is placed on the screen, the initial speed is (0,0) and the
 * accelerate is also (0,0). Lets say, at first the position is p0, speed is v0
 * = (0,0), accelerate is a0 = (0,0). <br\>
 * In the next step, it will have a new accelerate a1, which is based on the
 * incline of the screen. So the new speed will be v1 = v0 + a1 and the new
 * position will be p1 = p0 + v1. In general, v_i+1 = v_i + a_i+1 and p_i+1 =
 * p_i + v_i+1.<br\>
 * The boulder will rebound when it hits the edge of the screen. <br\>
 * The boulder will break when it hits the barriers. <br\>
 * The boulder will slow down when it is in the goos.
 */
public abstract class Boulder extends Circle {

	/** The limit of the speed */
	private final static float SPEED_LIMIT = 20;
	/**
	 * The infection of the boulder, after the boulder get the accelerate, the
	 * speed will multiplied by INFECTION
	 */
	private final static float INFECTION = 0.9f;
	
	private final static float REBOUND_FACTOR = 1.3f;
	/** The first hit damage. */
	private final static int BOULDER_HIT_DAMAGE = 1;
	/** The speed of boulder */
	private Vector speed;
	/** The accelerate of boulder */
	private Accelerate accelerate;
	/** How long the boulder will last */
	protected static final int NORMAL_COUNT = 15 * 1000 / GameModel
			.getUpdateFrequency();
//	protected static final int NORMAL_COUNT = 10;

	public Boulder(int radius, int appearanceCount, int disappearanceCount) {
		super(new Position(0, 0), radius, appearanceCount, NORMAL_COUNT,
				disappearanceCount);
		speed = new Speed(0, 0);
		accelerate = new Accelerate(0, 0);
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.Boulder);
	}

	public void setAccelerate(Accelerate accelerate) {
		this.accelerate = accelerate;
	}

	/** Moves the boulder based on the motions of the accelerometer. */
	@Override
	public void update() {
		if (expirableObjectState.getLifeState() == LifeState.NORMAL) {
			speed.plus(accelerate);
			speed.multiply(INFECTION);
			if (speed.magnitude() > SPEED_LIMIT) {
				speed.multiply(SPEED_LIMIT / speed.magnitude());
			}
			getCenter().plus(speed);
		}
		super.update();
	}

	@Override
	public void startDisappear() {
		speed = new Vector(0, 0);
		accelerate = new Accelerate(0, 0);
		super.startDisappear();
	}

	public int getDamage() {
		return BOULDER_HIT_DAMAGE;
	}

	private void rebound(Entity entity) {
		// find the boulder is on which side of the barrier.
		int side = entity.onWhichSide(getCenter());
		if (side == 0) {
			setCenterY(entity.getTop() - getRadius());
			speed.setY(-speed.getY());
		} else if (side == 1) {
			setCenterX(entity.getRight() + getRadius());
			speed.setX(-speed.getX());
		} else if (side == 2) {
			setCenterY(entity.getBottom() + getRadius());
			speed.setY(-speed.getY());
		} else if (side == 3) {
			setCenterX(entity.getLeft() - getRadius());
			speed.setX(-speed.getX());
		}
		speed.multiply(REBOUND_FACTOR);
	}

	/** The effect of slowing down */
	public void setDeceleration() {
		speed.multiply(Goo.DECELERATION);
	}

	@Override
	protected void checkDirection() {
		if (expirableObjectState.getLifeState() == LifeState.NORMAL) {
			boolean b1 = speed.getX() - speed.getY() < 0;
			boolean b2 = speed.getX() + speed.getY() > 0;
			if (b1 && b2) {
				expirableObjectState.setDirectionState(DirectionState.DOWN);
			} else if (!b1 && b2) {
				expirableObjectState.setDirectionState(DirectionState.RIGHT);
			} else if (!b1 && !b2) {
				expirableObjectState.setDirectionState(DirectionState.UP);
			} else if (b1 && !b2) {
				expirableObjectState.setDirectionState(DirectionState.LEFT);
			}
		}
	}

	@Override
	public void hitBarrier(Barrier barrier, CollisionContext collisionContext) {
		rebound(barrier);
	}

	@Override
	public void hitGoo(Goo goo, CollisionContext collisionContext) {
		setDeceleration();
	}

	@Override
	public void hitZombie(Zombie zombie, CollisionContext collisionContext) {
		rebound(zombie);
	}

	@Override
	public void hitScientist(Scientist scientist,
			CollisionContext collisionContext) {
		if (!scientist.isInvincible()) {
			rebound(scientist);
		}
	}

	@Override
	public void hitBoulder(Boulder boulder, CollisionContext collisionContext) {
	}

}