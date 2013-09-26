package com.jhu.oose16.zombieattack.view.factory;

import android.graphics.PointF;

import com.jhu.oose16.zombieattack.model.ExpirableObject;
import com.jhu.oose16.zombieattack.view.component.object.ViewExpirableObject;
import com.jhu.oose16.zombieattack.view.component.object.ViewStaticExpirableObject;
import com.jhu.oose16.zombieattack.view.component.object.imageframe.BarrierLargeHorizontalImageFrame;

public class ViewScreenBoundFactory implements
		ViewExpirableObjectFactory {

	@Override
	public ViewExpirableObject getViewExpirableObject(
			ExpirableObject expirableObject) {

		return new ViewStaticExpirableObject(
				new BarrierLargeHorizontalImageFrame(), new PointF(
						expirableObject.getCenterX(),
						expirableObject.getCenterY()));
	}

}
