package com.jhu.oose16.zombieattack.model.entity.materials;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.attached.CollisionContext;
import com.jhu.oose16.zombieattack.model.attached.Position;
import com.jhu.oose16.zombieattack.model.entity.Circle;
import com.jhu.oose16.zombieattack.model.entity.boulder.Boulder;
import com.jhu.oose16.zombieattack.model.entity.scientist.Scientist;
import com.jhu.oose16.zombieattack.model.entity.zombie.Zombie;

/** This class implements the goo feature. It will not change during the game. */
public class Goo extends Circle {
	/** Deceleration */
	public static final float DECELERATION = 0.3f;

	/**
	 * Constructor
	 * 
	 * @param topLeft
	 *            The top left position
	 * @param bottomRight
	 *            The bottom right position
	 */
	public Goo(Position topLeft, Position bottomRight) {
		super(topLeft, bottomRight, -1, -1, -1);
	}

	/**
	 * The override constructor
	 * 
	 * @param centerX
	 *            The x position of the center of goo
	 * @param centerY
	 *            The y position of the center of goo
	 * @param radius
	 *            The radius of goo
	 * @param DECELERATION
	 *            the deceleration to slow up the speed of other object overlap
	 *            with it.
	 */
	public Goo(Position center, int radius) {
		super(center, radius, -1, -1, -1);
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.Goo);
	}

	@Override
	public void update() {
	}

	@Override
	protected void checkDirection() {
	}

	@Override
	public void hitBarrier(Barrier barrier, CollisionContext collisionContext) {
	}

	@Override
	public void hitGoo(Goo goo, CollisionContext collisionContext) {
	}

	@Override
	public void hitZombie(Zombie zombie, CollisionContext collisionContext) {
	}

	@Override
	public void hitScientist(Scientist scientist,
			CollisionContext collisionContext) {
	}

	@Override
	public void hitBoulder(Boulder boulder, CollisionContext collisionContext) {
	}

}