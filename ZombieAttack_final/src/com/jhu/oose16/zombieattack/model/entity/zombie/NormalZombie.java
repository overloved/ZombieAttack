package com.jhu.oose16.zombieattack.model.entity.zombie;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;

/**
 * Zombie can moving randomly and when it hits the barriers, it can choose
 * another randomly moving path.
 * 
 * @author Tom Prats: tprats108@gmail.com<br/>
 *         Jinqiu Deng: deng.jinqiu@gmail.com<br/>
 *         Yao Huang: yao.engineering@gmail.com<br/>
 *         Lavanya Sivakumar: lavany92@gmail.com
 */
public class NormalZombie extends Zombie {

	private static final int WIDTH_UP_DOWN = 33;
	private static final int HEIGHT_UP_DOWN = 58;
	private static final int WIDTH_LEFT_RIGHT = 22;
	private static final int HEIGHT_LEFT_RIGHT = 56;
	private static final int DESTRUCTION_PEROID = 10;
	private static final int RUNNING_SPEED = 3;
	private static final int HEALTH_POINT = 1;
	private static final int ZOMBIE_DAMAGE = 1;
	private static final int ZOMBIE_SCORE = 1;

	public NormalZombie() {
		super(WIDTH_UP_DOWN, HEIGHT_UP_DOWN, WIDTH_LEFT_RIGHT,
				HEIGHT_LEFT_RIGHT, RUNNING_SPEED, HEALTH_POINT, ZOMBIE_DAMAGE,
				-1, -1, DESTRUCTION_PEROID);
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.NormalZombie);
	}

	@Override
	public int score() {
		return ZOMBIE_SCORE;
	}
}