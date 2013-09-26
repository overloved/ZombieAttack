package com.jhu.oose16.zombieattack.view.component.object;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.jhu.oose16.zombieattack.listener.ExpirableObjectListener;
import com.jhu.oose16.zombieattack.model.ExpirableObject;
import com.jhu.oose16.zombieattack.model.objectstate.ExpirableObjectState;
import com.jhu.oose16.zombieattack.view.component.object.cycleplay.CyclePlay;

public class ViewMovingExpirableObject extends ViewExpirableObject {

	private CyclePlay cyclePlay;

	public ViewMovingExpirableObject(ExpirableObject expirableObject,
			CyclePlay cyclePlay, PointF center) {
		super(center);

		this.cyclePlay = cyclePlay;

		expirableObject.addObjectListener(new ExpirableObjectListener() {
			@Override
			public void update(int centerX, int centerY,
					ExpirableObjectState objectState) {
				ViewMovingExpirableObject.this.setCenter(new PointF(centerX,
						centerY));
				ViewMovingExpirableObject.this.cyclePlay
						.playNextImage(objectState);
			}

			@Override
			public void remove() {
				ViewMovingExpirableObject.this.shouldBeRemoved = true;
			}
		});
	}

	@Override
	protected Bitmap getCurrentBitmap() {
		return cyclePlay.getBitmap(frameTransformation);
	}

}
