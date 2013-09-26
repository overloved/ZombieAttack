package com.jhu.oose16.zombieattack.model.attached;

/** the speed which is presented by a two dimensional vector */
public class Speed extends Vector {
	public Speed(float x, float y) {
		super(x, y);
	}

	/**
	 * The constructor of class.
	 * 
	 * @param start
	 *            start position.
	 * @param end
	 *            end position.
	 */
	public Speed(Position start, Position end) {
		super(start, end);
		// TODO Auto-generated constructor stub
	}
}
