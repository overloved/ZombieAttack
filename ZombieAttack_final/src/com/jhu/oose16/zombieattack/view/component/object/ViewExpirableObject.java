package com.jhu.oose16.zombieattack.view.component.object;

import android.graphics.PointF;

import com.jhu.oose16.zombieattack.view.attached.ModelViewScreenTrans;
import com.jhu.oose16.zombieattack.view.component.ViewComponent;

public abstract class ViewExpirableObject extends ViewComponent {

	public ViewExpirableObject(PointF center) {
		super(center);
		shouldBeRemoved = false;
	}

	protected boolean shouldBeRemoved;

	public boolean isShouldBeRemoved() {
		return shouldBeRemoved;
	}

	@Override
	protected void setTransformation() {
		frameTransformation = ModelViewScreenTrans
				.getModelToScreenTransformation();
	}

}
