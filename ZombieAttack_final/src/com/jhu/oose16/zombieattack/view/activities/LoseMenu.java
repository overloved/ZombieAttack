package com.jhu.oose16.zombieattack.view.activities;

import android.content.Intent;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;

import com.jhu.oose16.zombieattack.view.component.Button;
import com.jhu.oose16.zombieattack.view.component.Texture;

public class LoseMenu extends Menu<LoseMenu.ViewComponentTypes> {

	public static enum ViewComponentTypes {
		LOSE_MENU_BACKGROUND, RETURN_MAIN_MENU_BUTTON, RETRY_BUTTON
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addViewComponent(ViewComponentTypes.LOSE_MENU_BACKGROUND, new Texture(
				R.drawable.lose_menu_background, new PointF(427, 240)));

		addViewComponent(ViewComponentTypes.RETRY_BUTTON, new Button(
				R.drawable.lose_menu_retry_button,
				R.drawable.lose_menu_retry_button_down, new PointF(427, 272)));

		addViewComponent(ViewComponentTypes.RETURN_MAIN_MENU_BUTTON,
				new Button(R.drawable.lose_menu_return_main_menu_button,
						R.drawable.lose_menu_return_main_menu_button_down,
						new PointF(427, 372)));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);

		PointF touchPoint = new PointF(event.getX(), event.getY());

		if (viewComponentManager
				.getViewComponent(ViewComponentTypes.RETRY_BUTTON)
				.getViewRect().isInside(touchPoint)) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				((Button) viewComponentManager
						.getViewComponent(ViewComponentTypes.RETRY_BUTTON))
						.buttonDown();
				if (MainMenu.music_sound_on) {
					MainMenu.touchSong.start();
				}
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				sendBroadcast(new Intent("retryGame"));

				Intent intent = new Intent();
				intent.setClass(LoseMenu.this, GamePlay.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				
				GamePlay.backGround_music = MediaPlayer.create(LoseMenu.this, R.raw.scare_song);
				GamePlay.backGround_music.setVolume(100, 100);
				GamePlay.backGround_music.start();
				
				finish();
			}
		}

		if (viewComponentManager
				.getViewComponent(ViewComponentTypes.RETURN_MAIN_MENU_BUTTON)
				.getViewRect().isInside(touchPoint)) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				((Button) viewComponentManager
						.getViewComponent(ViewComponentTypes.RETURN_MAIN_MENU_BUTTON))
						.buttonDown();
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				sendBroadcast(new Intent("finishGamePlay"));

				Intent intent = new Intent();
				intent.setClass(LoseMenu.this, MainMenu.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);

				finish();
			}
		}

		if (event.getAction() == MotionEvent.ACTION_UP) {
			((Button) viewComponentManager
					.getViewComponent(ViewComponentTypes.RETRY_BUTTON))
					.buttonUp();

			((Button) viewComponentManager
					.getViewComponent(ViewComponentTypes.RETURN_MAIN_MENU_BUTTON))
					.buttonUp();
		}
		viewComponentManager.invalidate();
		return true;
	}
}
