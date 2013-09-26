package com.jhu.oose16.zombieattack.model.attached;

/**
 * Accelerate is present by a two dimensional vector, which is used by the
 * boulder.
 */
public class Accelerate extends Vector {
	public Accelerate(float x, float y) {
		super(x, y);
	}

	/**
	 * The Constructor of class.
	 * 
	 * @param start
	 *            position
	 * @param end
	 *            position
	 */
	public Accelerate(Position start, Position end) {
		super(start, end);
	}
}
