package com.jhu.oose16.zombieattack.view.activities;

import android.content.Intent;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;

import com.jhu.oose16.zombieattack.view.component.Button;
import com.jhu.oose16.zombieattack.view.component.Texture;

public class PauseMenu extends Menu<PauseMenu.ViewComponentTypes> {

	public static enum ViewComponentTypes {
		PAUSE_MENU_BACKGROUND, RETURN_BUTTON, RETURN_MAIN_MENU_BUTTON, RETRY_BUTTON
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addViewComponent(ViewComponentTypes.PAUSE_MENU_BACKGROUND, new Texture(
				R.drawable.pause_menu_background, new PointF(427, 240)));

		addViewComponent(ViewComponentTypes.RETURN_BUTTON, new Button(
				R.drawable.pause_menu_return_button,
				R.drawable.pause_menu_return_button_down, new PointF(427, 172)));

		addViewComponent(ViewComponentTypes.RETRY_BUTTON, new Button(
				R.drawable.pause_menu_retry, R.drawable.pause_menu_retry_down,
				new PointF(427, 272)));

		addViewComponent(ViewComponentTypes.RETURN_MAIN_MENU_BUTTON,
				new Button(R.drawable.pause_menu_return_main_menu_button,
						R.drawable.pause_menu_return_main_menu_button_down,
						new PointF(427, 372)));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);

		PointF touchPoint = new PointF(event.getX(), event.getY());

		if (viewComponentManager
				.getViewComponent(ViewComponentTypes.RETURN_BUTTON)
				.getViewRect().isInside(touchPoint)) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				((Button) viewComponentManager
						.getViewComponent(ViewComponentTypes.RETURN_BUTTON))
						.buttonDown();
				if (MainMenu.music_sound_on) {
					MainMenu.touchSong.start();
				}
				
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				sendBroadcast(new Intent("resumeGame"));
				if (MainMenu.music_sound_on) {
					GamePlay.backGround_music.start();
				}
				Intent intent = new Intent();
				intent.setClass(PauseMenu.this, GamePlay.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);

				finish();
			}
		}

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
				intent.setClass(PauseMenu.this, GamePlay.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				if(MainMenu.music_sound_on) {
					//GamePlay.backGround_music.reset();
					GamePlay.backGround_music = MediaPlayer.create(PauseMenu.this, R.raw.scare_song);
					GamePlay.backGround_music.start();
					GamePlay.backGround_music.setVolume(100, 100);
				}

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
				if (MainMenu.music_sound_on) {
					MainMenu.touchSong.start();
				}
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				sendBroadcast(new Intent("finishGamePlay"));

				Intent intent = new Intent();
				intent.setClass(PauseMenu.this, MainMenu.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);

				finish();
			}
		}

		if (event.getAction() == MotionEvent.ACTION_UP) {
			((Button) viewComponentManager
					.getViewComponent(ViewComponentTypes.RETURN_BUTTON))
					.buttonUp();

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
