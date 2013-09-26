package com.jhu.oose16.zombieattack.view.component;

import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.jhu.oose16.zombieattack.view.attached.BitmapManager;
import com.jhu.oose16.zombieattack.view.attached.ModelViewScreenTrans;

public abstract class EnumViewComponent<K> extends ViewComponent {
	protected Map<K, Integer> imageIDs;

	private K currentImageKey;

	public EnumViewComponent(Map<K, Integer> imageIDs,
			K firstImageKey, PointF center) {
		super(center);
		this.currentImageKey = firstImageKey;

		this.imageIDs = imageIDs;
	}

	protected K getCurrentImageKey() {
		return this.currentImageKey;
	}

	protected void setCurrentImageKey(K key) {
		this.currentImageKey = key;
	}

	@Override
	protected Bitmap getCurrentBitmap() {
		return BitmapManager.getBitmap(imageIDs.get(currentImageKey), frameTransformation);
	}
	
	@Override
	protected void setTransformation() {
		this.frameTransformation = ModelViewScreenTrans.getViewToScreenTransformation();
	}

}
