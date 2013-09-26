package com.jhu.oose16.zombieattack.model.attached;

/** Present a two dimensional vector.
 * 
 *  @author Tom Prats: tprats108@gmail.com<br/>
 *  Jinqiu Deng: deng.jinqiu@gmail.com<br/>
 *  Yao Huang: yao.engineering@gmail.com<br/>
 *  Lavanya Sivakumar: lavany92@gmail.com*/
public class Vector {
	private float x;
	private float y;
	
	/**
	 * The constructor of class.
	 */
	public Vector() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * The override constructor.
	 * @param x
	 * @param y
	 */
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/** Create a vector from start to end.<br/>
	 * 
	 * @param start The start position of the vector
	 * @param end The end position of the vector*/
	public Vector(Position start, Position end) {
		this.x = end.getX() - start.getX();
		this.y = end.getY() - start.getY();
	}
	
	/** Get normalized the value to change into unit vector.*/
	public void normalize() {
		float mag = magnitude();
		x = x / mag;
		y = y / mag;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float magnitude()
	{
		return (float) Math.sqrt(x*x + y*y);
	}
	
	/** Addition for two vectors
	 *  @param vector*/
	public void plus(Vector vector) {
		this.x = this.x + vector.getX();
		this.y = this.y + vector.getY();
	}
	
	public void setXAndY(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/** Multiply a on the x and y coordinate
	 *  @param d*/
	public void multiply(float d) {
		x = x * d;
		y = y * d;
	}
}