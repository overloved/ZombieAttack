package com.jhu.oose16.zombieattack.view.component;

import java.util.HashMap;
import java.util.Map;

import android.graphics.PointF;

import com.jhu.oose16.zombieattack.listener.ExpirableObjectListener;
import com.jhu.oose16.zombieattack.model.entity.Entity;
import com.jhu.oose16.zombieattack.model.objectstate.ExpirableObjectState;

public class DoubleViewIcon extends EnumViewComponent<DoubleViewIcon.State> {

	public static enum State {
		TURN_ON, TURN_OFF
	}

	public DoubleViewIcon(Integer turnOnID, Integer turnOffID, PointF center,
			Entity entity) {
		super(createKeyMap(turnOnID, turnOffID), State.TURN_ON, center);
		entity.addObjectListener(new ExpirableObjectListener() {

			@Override
			public void remove() {
				turnOff();
			}

			@Override
			public void update(int centerX, int centerY, ExpirableObjectState objectState) {
			}
		});
	}

	private void turnOff() {
		setCurrentImageKey(State.TURN_OFF);
	}

	private static Map<State, Integer> createKeyMap(Integer turnOnID,
			Integer turnOffID) {
		Map<State, Integer> map = new HashMap<State, Integer>();
		map.put(State.TURN_ON, turnOnID);
		map.put(State.TURN_OFF, turnOffID);
		return map;
	}

}
