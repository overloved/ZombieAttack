package com.jhu.oose16.zombieattack.model.entity;

import java.util.List;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.attached.Position;

/** This class present a 2D rectangle. */
public abstract class Rectangle extends Entity {

	protected int left;
	protected int right;
	protected int top;
	protected int bottom;

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.Rectangle);
	}

	/**
	 * Constructor
	 * 
	 * @param topLeft
	 *            The top left position.
	 * @param bottomRight
	 *            The bottom right position.
	 * @param appearanceCount
	 *            The appearance state count
	 * @param normalCount
	 *            The normal state count
	 * @param disappearanceCount
	 *            The disappearance state count
	 */
	public Rectangle(Position topLeft, Position bottomRight,
			int appearanceCount, int normalCount, int disappearanceCount) {
		super(appearanceCount, normalCount, disappearanceCount);
		this.left = topLeft.getX();
		this.top = topLeft.getY();
		this.right = bottomRight.getX();
		this.bottom = bottomRight.getY();
	}

	/**
	 * Constructor
	 * 
	 * @param topLeft
	 *            The top left corner of the rectangle
	 * @param width
	 *            The width of the rectangle
	 * @param height
	 *            The height of the rectangle
	 * @param appearanceCount
	 *            The appearance state count
	 * @param normalCount
	 *            The normal state count
	 * @param disappearanceCount
	 *            The disappearance state count
	 */
	public Rectangle(Position topLeft, int width, int height,
			int appearanceCount, int normalCount, int disappearanceCount) {
		super(appearanceCount, normalCount, disappearanceCount);
		this.left = topLeft.getX();
		this.top = topLeft.getY();
		this.right = left + width - 1;
		this.bottom = top + height - 1;
	}

	/**
	 * Get the center
	 * 
	 * @return The center position
	 */
	@Override
	public Position getCenter() {
		return new Position((left + right) / 2, (top + bottom) / 2);
	}

	/**
	 * Set the center
	 * 
	 * @param position
	 *            The given center
	 */
	public void setCenter(Position position) {
		int xShift = getCenter().getX() - position.getX();
		int yShift = getCenter().getY() - position.getY();

		left = left - xShift;
		right = right - xShift;

		top = top - yShift;
		bottom = bottom - yShift;
	}

	/**
	 * Reset the size of the rectangle, do not change the center position
	 * 
	 * @param width
	 *            The new width
	 * @param height
	 *            The new height
	 */
	protected void resetSize(int width, int height) {
		this.left = getCenterX() - width / 2;
		this.top = getCenterY() - height / 2;
		this.right = this.left + width - 1;
		this.bottom = this.top + height - 1;
	}

	/**
	 * Whether the rectangle is on one side of the line
	 * 
	 * @param position1
	 *            The start position of the line
	 * @param position2
	 *            The end position of the line
	 * @return True: on one side
	 */
	public boolean isIntersect(Position position1, Position position2) {
		List<Position> line = position1.linePosition(position2);

		for (int i = 0; i < line.size() - 1; i++) {
			if (isInside(line.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Whether the given position is inside the rectangle
	 * 
	 * @param position
	 *            The given position
	 * @return True: in the rectangle
	 */
	public boolean isInside(Position position) {
		if (position.getX() <= right && position.getX() >= left
				&& position.getY() <= bottom && position.getY() >= top) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	@Override
	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	@Override
	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	@Override
	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public int getWidth() {
		return right - left;
	}

	public int getHeight() {
		return bottom - top;
	}

	@Override
	public int getCenterX() {
		return getCenter().getX();
	}

	@Override
	public int getCenterY() {
		return getCenter().getY();
	}

	public void setCenterX(int x) {
		int xShift = getCenter().getX() - x;

		left = left - xShift;
		right = right - xShift;
	}

	public void setCenterY(int y) {
		int yShift = getCenter().getY() - y;

		top = top - yShift;
		bottom = bottom - yShift;
	}

	@Override
	public Position getTopLeft() {
		return new Position(left, top);
	}

	@Override
	public Position getTopRight() {
		return new Position(right, top);
	}

	@Override
	public Position getBottomLeft() {
		return new Position(left, bottom);
	}

	@Override
	public Position getBottomRight() {
		return new Position(right, bottom);
	}
}
