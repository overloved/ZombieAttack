package com.jhu.oose16.zombieattack.view.component;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import android.graphics.PointF;

import com.jhu.oose16.zombieattack.view.activities.R;

public class Number extends EnumViewComponent<Number.State> {

	public final static int hight = 40;
	public final static int width = 23;

	public static enum State {
		zero, one, two, three, four, five, six, seven, eight, nine, slash, blank
	}

	public Number(PointF center) {
		super(createKeyMap(), State.zero, center);
	}

	public void changeNum(int num) {
		Iterator<State> iterator = imageIDs.keySet().iterator();
		int count = 0;
		while (iterator.hasNext()) {
			setCurrentImageKey(iterator.next());
			if (count == num) {
				break;
			} else {
				count++;
			}
		}
	}

	public void changeToSlash() {
		setCurrentImageKey(State.slash);
	}

	public void changeToBlank() {
		setCurrentImageKey(State.blank);
	}

	private static Map<State, Integer> createKeyMap() {
		Map<State, Integer> map = new LinkedHashMap<State, Integer>();
		map.put(State.zero, R.drawable.number_0);
		map.put(State.one, R.drawable.number_1);
		map.put(State.two, R.drawable.number_2);
		map.put(State.three, R.drawable.number_3);
		map.put(State.four, R.drawable.number_4);
		map.put(State.five, R.drawable.number_5);
		map.put(State.six, R.drawable.number_6);
		map.put(State.seven, R.drawable.number_7);
		map.put(State.eight, R.drawable.number_8);
		map.put(State.nine, R.drawable.number_9);
		map.put(State.blank, R.drawable.blank);

		return map;
	}

}
