package com.jhu.oose16.zombieattack.model.attached;

import java.util.ArrayList;

import com.jhu.oose16.zombieattack.model.GameModel;

/**
 * This class represents a given (x,y) coordinate on the playing board.
 */
public class Position {
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/** This value stores the x position */
	private int x;

	/** This value stores the y position */
	private int y;

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * Find the line from this position to the given position with the given
	 * length
	 * 
	 * @param position
	 *            Find the line from this position to input position.
	 * @param length
	 *            The number of element in the return list, if length = -1, the
	 *            line will hit the screen edge.
	 * 
	 * @return Every position on the line.
	 */
	public ArrayList<Position> linePosition(Position position, int length) {
		ArrayList<Position> result = new ArrayList<Position>();

		if (length == -1) {
			length = GameModel.getGameFrameWidth();
		}

		// direction of the line
		Vector vector = new Vector(this, position);
		vector.normalize();
		if (Math.abs(vector.getX()) > 0.5) {
			float rate = Math.abs(1 / vector.getX());
			vector.setX(vector.getX() * rate);
			vector.setY(vector.getY() * rate);
			for (int i = 0; i < length; i++) {
				result.add(new Position((int) (this.x + i * vector.getX()),
						(int) (this.y + i * vector.getY())));
			}
		} else {
			float rate = Math.abs(1 / vector.getY());
			vector.setX(vector.getX() * rate);
			vector.setY(vector.getY() * rate);
			for (int i = 0; i < length; i++) {
				result.add(new Position((int) (this.x + i * vector.getX()),
						(int) (this.y + i * vector.getY())));
			}
		}
		return result;
	}

	/**
	 * Find the line lead to the given position
	 * 
	 * @param position
	 *            The given position
	 * @return The positions on the line.
	 */
	public ArrayList<Position> linePosition(Position position) {
		int length;
		if (Math.abs(getX() - position.getX()) > Math.abs(getY()
				- position.getY())) {
			length = Math.abs(getX() - position.getX());
		} else {
			length = Math.abs(getY() - position.getY());
		}
		return linePosition(position, length);
	}

	/**
	 * whether this position is above the line formed by position1 and position2
	 * 
	 * @param position1
	 *            the start position of the line
	 * @param position2
	 *            the end position of the line
	 * @return If the position is above the line return true, else return false
	 */
	public boolean aboveLine(Position position1, Position position2) {
		float temp = (this.x - position2.x) * (position1.y - position2.y)
				- (this.y - position2.y) * (position1.x - position2.x);
		if (temp < 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getX() == ((Position) obj).getX()
				&& this.getY() == ((Position) obj).getY()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Plus a vector onto the current position.
	 * 
	 * @param vector
	 *            The given vector.
	 */
	public void plus(Vector vector) {
		this.x = (int) (this.x + vector.getX());
		this.y = (int) (this.y + vector.getY());
	}

	/**
	 * The distance from this position to the given position
	 * 
	 * @param position
	 *            The given position
	 * @return the distance to the given position
	 */
	public float distance(Position position) {
		int x = getX() - position.getX();
		int y = getY() - position.getY();
		return (float) Math.sqrt(x * x + y * y);
	}
}
