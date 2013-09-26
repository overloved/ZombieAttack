package com.jhu.oose16.zombieattack.view.attached;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class BitmapManager {

	private static Resources resources;

	// Use 1/8th of the available memory for this memory cache.
	private static int cacheSize;

	private static LruCache<Integer, Bitmap> bitmapCache;

	public static void setResources(Resources resources) {
		if (BitmapManager.resources == null) {
			BitmapManager.resources = resources;
			int maxMemory = (int) (Runtime.getRuntime().maxMemory());
			cacheSize = maxMemory / 8;
			// cacheSize = 10;
			bitmapCache = new LruCache<Integer, Bitmap>(cacheSize) {
				@Override
				protected int sizeOf(Integer key, Bitmap value) {
					return value.getHeight() * value.getWidth() * 4;
				}
			};
		}
	}

	public static Bitmap getBitmap(int imageID,
			FrameTransformation frameTransformation) {
		Bitmap bitmap = bitmapCache.get(imageID);
		if (bitmap == null) {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inScaled = false;
			bitmap = BitmapFactory.decodeResource(resources, imageID, options);

			bitmap = Bitmap.createScaledBitmap(bitmap,
					(int) frameTransformation.transform(bitmap.getWidth()),
					(int) frameTransformation.transform(bitmap.getHeight()),
					false);

			bitmapCache.put(imageID, bitmap);
		}

		return bitmap;
	}
}
