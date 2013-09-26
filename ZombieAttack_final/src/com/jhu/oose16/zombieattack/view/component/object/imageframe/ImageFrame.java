package com.jhu.oose16.zombieattack.view.component.object.imageframe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;

import com.jhu.oose16.zombieattack.view.attached.BitmapManager;
import com.jhu.oose16.zombieattack.view.attached.FrameTransformation;

public abstract class ImageFrame {
	List<Integer> imageList;

	public ImageFrame() {
		imageList = new ArrayList<Integer>();
	}

	public Bitmap getBitmap(FrameTransformation frameTransformation) {
		Random random = new Random();
		return BitmapManager.getBitmap(
				imageList.get(random.nextInt(imageList.size())),
				frameTransformation);
	}
}
