package com.jhu.oose16.zombieattack.view.component.object;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.jhu.oose16.zombieattack.view.component.object.imageframe.ImageFrame;

public class ViewStaticExpirableObject extends ViewExpirableObject{
	
	private Bitmap bitmap;
	
	public ViewStaticExpirableObject(ImageFrame imageFrame, PointF center) {
		super(center);
		bitmap = imageFrame.getBitmap(frameTransformation);
	}
	
	@Override
	protected Bitmap getCurrentBitmap() {
		return bitmap;
	}

}
