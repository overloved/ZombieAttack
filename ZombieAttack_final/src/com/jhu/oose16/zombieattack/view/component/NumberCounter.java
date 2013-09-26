package com.jhu.oose16.zombieattack.view.component;

import java.util.ArrayList;
import java.util.List;

import android.graphics.PointF;

public class NumberCounter {
	private List<Number> numbers;

	private PointF nextCenter;

	public NumberCounter(PointF topRight) {
		nextCenter = new PointF(topRight.x - Number.width / 2, topRight.y
				+ Number.hight / 2);
		numbers = new ArrayList<Number>();
		for (int i = 0; i < 10; i++) {
			Number newNumber = new Number(nextCenter);
			newNumber.changeToBlank();
			numbers.add(newNumber);

			nextCenter.set(nextCenter.x - Number.width, nextCenter.y);
		}
	}

	public void updateNum(int score) {
		for (int i = 0; i < numbers.size(); i++) {
			numbers.get(i).changeToBlank();
		}
		for (int i = 0; i < numbers.size(); i++) {
			if (numbers.size() > i) {
				numbers.get(i).changeNum(score % 10);
				score /= 10;
			}
			if (score <= 0) {
				break;
			}
		}
	}

	public List<Number> getViewComponents() {
		return numbers;
	}
}
