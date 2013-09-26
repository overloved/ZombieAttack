package com.jhu.oose16.zombieattack.model.entity;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.attached.Position;

/** This class implements game boulders. */
public abstract class Circle extends Entity {
	/** The center position of circle */
	private Position center;

	/** The radius of circle */
	private int radius;

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.Circle);
	}

	/**
	 * generate a circle inside a rectangle which has the top left corner as
	 * topLeft and bottom right corner as bottomRight.
	 * 
	 * @param topLeft
	 *            The top left position
	 * @param bottomRight
	 *            The bottom right position
	 * @param appearanceCount
	 *            The appearance state count
	 * @param normalCount
	 *            The normal state count
	 * @param disappearanceCount
	 *            The disappearance state count
	 */
	public Circle(Position topLeft, Position bottomRight, int appearanceCount,
			int normalCount, int disappearanceCount) {
		super(appearanceCount, normalCount, disappearanceCount);
		int width = bottomRight.getX() - topLeft.getX();
		int height = bottomRight.getY() - topLeft.getY();
		int x = (bottomRight.getX() + topLeft.getX()) / 2;
		int y = (bottomRight.getY() + topLeft.getY()) / 2;
		this.center = new Position(x, y);
		if (width > height) {
			this.radius = height / 2;
		} else {
			this.radius = width / 2;
		}
	}

	/**
	 * Constructor
	 * 
	 * @param center
	 *            The center of circle
	 * @param radius
	 *            The radius of circle
	 * @param appearanceCount
	 *            The appearance state count
	 * @param normalCount
	 *            The normal state count
	 * @param disappearanceCount
	 *            The disappearance state count
	 */
	public Circle(Position center, int radius, int appearanceCount,
			int normalCount, int disappearanceCount) {
		super(appearanceCount, normalCount, disappearanceCount);
		this.center = center;
		this.radius = radius;
	}

	@Override
	public Position getCenter() {
		return center;
	}

	@Override
	public int getCenterX() {
		return center.getX();
	}

	@Override
	public int getCenterY() {
		return center.getY();
	}

	public void setCenter(Position center) {
		this.center = center;
	}

	public void setCenterX(int x) {
		center.setX(x);
	}

	public void setCenterY(int y) {
		center.setY(y);
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public int getLeft() {
		return getCenterX() - getRadius();
	}

	@Override
	public int getRight() {
		return getCenterX() + getRadius();
	}

	@Override
	public int getTop() {
		return getCenterY() - getRadius();
	}

	@Override
	public int getBottom() {
		return getCenterY() + getRadius();
	}
}