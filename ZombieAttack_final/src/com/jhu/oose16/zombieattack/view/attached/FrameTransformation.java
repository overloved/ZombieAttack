package com.jhu.oose16.zombieattack.view.attached;

import android.graphics.PointF;

public class FrameTransformation {
	private float scale;
	private PointF translation = new PointF();

	public FrameTransformation(float frame1Width, float frame1Height,
			float frame2Width, float frame2Height, PointF shift) {
		float rateHeight = frame2Height / frame1Height;
		float rateWidth = frame2Width / frame1Width;
		if (rateHeight > rateWidth) {
			scale = rateWidth;
		} else {
			scale = rateHeight;
		}
		translation.x = (frame2Width - frame1Width * scale) / 2 + shift.x;
		translation.y = (frame2Height - frame1Height * scale) / 2 + shift.y;
//		Log.d("Scale: ", scale + "");
//		Log.d("Tran: ", translation.x + " " + translation.y);
	}

	public FrameTransformation(float scale, PointF translation) {
		this.scale = scale;
		this.translation = translation;
	}

	public float getScale() {
		return scale;
	}

	public PointF getTranslation() {
		return translation;
	}

	public ViewRect transform(ViewRect viewRect) {
		return new ViewRect(transform(viewRect.getLeftTop()),
				transform(viewRect.getRightBottom()));
	}

	public PointF transform(PointF point) {
		return new PointF(point.x * scale + translation.x, point.y * scale
				+ translation.y);
	}

	public float transform(float length) {
		return length * scale;
	}

	public FrameTransformation addFrameTransformation(
			FrameTransformation frameTransformation) {
		return new FrameTransformation(scale * frameTransformation.getScale(),
				new PointF(getTranslation().x * frameTransformation.getScale()
						+ frameTransformation.getTranslation().x,
						getTranslation().y * frameTransformation.getScale()
								+ frameTransformation.getTranslation().y));
	}

	public FrameTransformation getBackwardTransformation() {
		return new FrameTransformation(1 / getScale(), new PointF(
				-getTranslation().x / getScale(), -getTranslation().y
						/ getScale()));
	}
}
