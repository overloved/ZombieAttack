package com.jhu.oose16.zombieattack.view.component;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

import com.jhu.oose16.zombieattack.view.attached.FrameTransformation;
import com.jhu.oose16.zombieattack.view.attached.ViewRect;

public abstract class ViewComponent {
	/** The top left point of the view component */
	protected PointF center;

	/** The transformation to the screen */
	protected FrameTransformation frameTransformation;

	public ViewComponent(PointF center) {
		setTransformation();
		setCenter(center);
	}

	protected abstract void setTransformation();

	protected abstract Bitmap getCurrentBitmap();

	public void setCenter(PointF center) {
		this.center = frameTransformation.transform(center);
	}

	public void draw(Canvas canvas) {
		int left = (int) (center.x - getCurrentBitmap().getWidth() / 2);
		int top = (int) (center.y - getCurrentBitmap().getHeight() / 2);
		canvas.drawBitmap(getCurrentBitmap(), left, top, null);
	}

	public ViewRect getViewRect() {
		int width = getCurrentBitmap().getWidth();
		int height = getCurrentBitmap().getHeight();
		return new ViewRect(center.x - width / 2, center.y - height / 2,
				center.x + width / 2, center.y + height / 2);
	}

}