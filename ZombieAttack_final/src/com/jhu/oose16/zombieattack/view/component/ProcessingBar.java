package com.jhu.oose16.zombieattack.view.component;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.jhu.oose16.zombieattack.view.attached.BitmapManager;
import com.jhu.oose16.zombieattack.view.attached.ModelViewScreenTrans;

public class ProcessingBar extends ViewComponent {

	private PointF bottomRight;

	private int imageID;

	private float width;
	private float height;

	private float percentage;

	public ProcessingBar(int width, int height, PointF bottomRight, int imageID) {
		super(new PointF(0, 0));
		this.bottomRight = frameTransformation.transform(bottomRight);

		this.width = frameTransformation.transform(width);
		this.height = frameTransformation.transform(height);

		this.imageID = imageID;
	}

	@Override
	protected Bitmap getCurrentBitmap() {
		Bitmap bitmap = BitmapManager.getBitmap(imageID, frameTransformation);
		bitmap = Bitmap.createScaledBitmap(bitmap, (int) width,
				(int) (height * percentage) <= 0 ? 1
						: (int) (height * percentage), false);
		center = new PointF(bottomRight.x - width / 2, bottomRight.y - height
				* percentage / 2);

		return bitmap;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	@Override
	protected void setTransformation() {
		frameTransformation = ModelViewScreenTrans
				.getViewToScreenTransformation();
	}
}
