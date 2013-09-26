package com.jhu.oose16.zombieattack.view.attached;

import android.graphics.PointF;
import android.util.Log;

import com.jhu.oose16.zombieattack.model.GameModel;

public class ModelViewScreenTrans {
	private static final int VIEW_FRAME_WIDTH = 854;
	private static final int VIEW_FRAME_HEIGHT = 480;

	private static final int GAME_FRAME_WIDTH = GameModel.getGameFrameWidth();
	private static final int GAME_FRAME_HEIGHT = GameModel.getGameFrameHeight();

	private static final int GAME_IN_VIEW_WIDTH = 760;
	private static final int GAME_IN_VIEW_HEIGHT = GAME_FRAME_HEIGHT;
	private static final PointF GAME_IN_VIEW_SHIFT = new PointF(60, 0);

	private static FrameTransformation modelToScreenTransformation;
	private static FrameTransformation viewToScreenTransformation;
	private static FrameTransformation screenToModelTransformation;
	private static FrameTransformation screenToViewTransformation;

	private static boolean initalized = false;

	public static void initailize(int screenWidth, int screenHeight) {
		if (!initalized) {
			Log.d("model view screen transform", "screen size: " + screenWidth + " "
					+ screenHeight);
			viewToScreenTransformation = new FrameTransformation(
					VIEW_FRAME_WIDTH, VIEW_FRAME_HEIGHT, screenWidth,
					screenHeight, new PointF(0, 0));

			modelToScreenTransformation = new FrameTransformation(
					GAME_FRAME_WIDTH, GAME_FRAME_HEIGHT, GAME_IN_VIEW_WIDTH,
					GAME_IN_VIEW_HEIGHT, GAME_IN_VIEW_SHIFT);

			modelToScreenTransformation = modelToScreenTransformation
					.addFrameTransformation(viewToScreenTransformation);

			screenToModelTransformation = modelToScreenTransformation
					.getBackwardTransformation();

			screenToViewTransformation = viewToScreenTransformation
					.getBackwardTransformation();
			initalized = true;
		}
	}

	public static FrameTransformation getModelToScreenTransformation() {
		return modelToScreenTransformation;
	}

	public static FrameTransformation getScreenToModelTransformation() {
		return screenToModelTransformation;
	}

	public static FrameTransformation getViewToScreenTransformation() {
		return viewToScreenTransformation;
	}

	public static FrameTransformation getScreenToViewTransformation() {
		return screenToViewTransformation;
	}

}
