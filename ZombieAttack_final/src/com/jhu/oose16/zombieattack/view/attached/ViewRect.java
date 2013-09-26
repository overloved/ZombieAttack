package com.jhu.oose16.zombieattack.view.attached;

import android.graphics.PointF;
import android.graphics.RectF;

public class ViewRect extends RectF {

	public ViewRect(PointF leftTop, PointF rightBottom) {
		super(leftTop.x, leftTop.y, rightBottom.x, rightBottom.y);
	}

	public ViewRect(float left, float top, float right, float bottom) {
		super(left, top, right, bottom);
	}

	public PointF getLeftTop() {
		return new PointF(left, top);
	}

	public PointF getRightBottom() {
		return new PointF(right, bottom);
	}

	public boolean isInside(PointF point) {
		return left < point.x && point.x < right && top < point.y
				&& point.y < bottom;
	}
}
