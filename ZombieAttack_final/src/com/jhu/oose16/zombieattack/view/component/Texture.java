package com.jhu.oose16.zombieattack.view.component;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.jhu.oose16.zombieattack.view.attached.BitmapManager;
import com.jhu.oose16.zombieattack.view.attached.ModelViewScreenTrans;

public class Texture extends ViewComponent {

	private int imageID;

	public Texture(Integer imageID, PointF center) {
		super(center);
		this.imageID = imageID;
	}

	@Override
	protected Bitmap getCurrentBitmap() {
		return BitmapManager.getBitmap(imageID, frameTransformation);
	}

	@Override
	protected void setTransformation() {
		frameTransformation = ModelViewScreenTrans.getViewToScreenTransformation();
	}

}
