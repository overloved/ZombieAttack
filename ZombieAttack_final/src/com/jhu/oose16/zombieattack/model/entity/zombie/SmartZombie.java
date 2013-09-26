package com.jhu.oose16.zombieattack.model.entity.zombie;

import java.util.List;

import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.GameModel;
import com.jhu.oose16.zombieattack.model.attached.DetectionContext;
import com.jhu.oose16.zombieattack.model.attached.Position;
import com.jhu.oose16.zombieattack.model.attached.Vector;
import com.jhu.oose16.zombieattack.model.entity.Entity;
import com.jhu.oose16.zombieattack.model.entity.Rectangle;
import com.jhu.oose16.zombieattack.model.entity.boulder.Boulder;

public class SmartZombie extends Zombie {

	private static final int WIDTH_UP_DOWN = 31;
	private static final int HEIGHT_UP_DOWN = 60;
	private static final int WIDTH_LEFT_RIGHT = 23;
	private static final int HEIGHT_LEFT_RIGHT = 58;
	private static final int DESTRUCTION_PEROID = 11;
	private static final int RUNNING_SPEED = 3;
	private static final int HEALTH_POINT = 2;
	private static final int ZOMBIE_DAMAGE = 1;
	private static final int ZOMBIE_SCORE = 5;

	private static final int RUNNING_SPEED_AFTER_DETECTION = 10;

	public SmartZombie() {
		super(WIDTH_UP_DOWN, HEIGHT_UP_DOWN, WIDTH_LEFT_RIGHT,
				HEIGHT_LEFT_RIGHT, RUNNING_SPEED, HEALTH_POINT, ZOMBIE_DAMAGE,
				-1, -1, DESTRUCTION_PEROID);
		openDetectionRegion();
	}

	@Override
	public void initialObjectType() {
		super.initialObjectType();
		addExpirableObjectType(ExpirableObjectType.SmartZombie);
	}

	@Override
	public void update() {
		super.update();
		setSpeed(RUNNING_SPEED);
	}

	@Override
	protected void detectBoulder(Boulder boulder,
			DetectionContext detectionContext) {
		setSpeed(RUNNING_SPEED_AFTER_DETECTION);

		Vector vector = new Vector(boulder.getCenter(), this.getCenter());
		Position end = new Position(getCenterX(), getCenterY());
		end.plus(vector);
		runningPath = getCenter().linePosition(end, HEIGHT_UP_DOWN);

		Position nextPosition = runningPath.get(runningPath.size() - 1);

		List<Entity> barriers = detectionContext.getBarriers();

		for (int i = 0; i < barriers.size(); i++) {
			if (((Rectangle) barriers.get(i)).isInside(nextPosition)) {
				int temp = barriers.get(i).onWhichSide(getCenter());
				if (temp == 0 || temp == 2) {
					if (boulder.getCenterX() > GameModel.getGameFrameWidth() / 2) {
						end = new Position(0, getCenterY());
					} else {
						end = new Position(GameModel.getGameFrameWidth(),
								getCenterY());
					}
				} else {
					if (boulder.getCenterY() > GameModel.getGameFrameHeight() / 2) {
						end = new Position(getCenterX(), 0);
					} else {
						end = new Position(getCenterX(),
								GameModel.getGameFrameHeight());
					}
				}
				runningPath = getCenter().linePosition(end);
				break;
			}
		}

		runningPositionIndex = 0;
	}

	@Override
	public int score() {
		return ZOMBIE_SCORE;
	}

}
