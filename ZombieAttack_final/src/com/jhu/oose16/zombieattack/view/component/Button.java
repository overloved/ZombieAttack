package com.jhu.oose16.zombieattack.view.component;

import java.util.HashMap;
import java.util.Map;

import android.graphics.PointF;

public class Button extends EnumViewComponent<Button.State> {

	public static enum State {
		UP, DOWN
	}

	public Button(Integer upID, Integer downID, PointF center) {
		super(createKeyMap(upID, downID), State.UP, center);
	}

	public void buttonUp() {
		setCurrentImageKey(State.UP);
	}

	public void buttonDown() {
		setCurrentImageKey(State.DOWN);
	}

	private static Map<State, Integer> createKeyMap(Integer upID, Integer downID) {
		Map<State, Integer> map = new HashMap<State, Integer>();
		map.put(State.UP, upID);
		map.put(State.DOWN, downID);
		return map;
	}
}
