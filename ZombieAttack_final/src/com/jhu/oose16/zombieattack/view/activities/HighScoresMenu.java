package com.jhu.oose16.zombieattack.view.activities;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;

import com.jhu.oose16.zombieattack.view.component.Button;
import com.jhu.oose16.zombieattack.view.component.Texture;

public class HighScoresMenu extends Menu<HighScoresMenu.ViewComponentTypes> {

	MainMenu mainMenu;
	
	public static enum ViewComponentTypes {
		BASIC_BACKGROUND, HIGH_SCORES_MENU_BACKGROUND, RETURN_MAIN_MENU_BUTTON
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mainMenu = new MainMenu();

		addViewComponent(ViewComponentTypes.BASIC_BACKGROUND, new Texture(
				R.drawable.basic_background, new PointF(427, 240)));

		addViewComponent(ViewComponentTypes.HIGH_SCORES_MENU_BACKGROUND,
				new Texture(R.drawable.high_scores_menu_background, new PointF(
						427, 240)));

		addViewComponent(ViewComponentTypes.RETURN_MAIN_MENU_BUTTON,
				new Button(R.drawable.pause_menu_return_main_menu_button,
						R.drawable.pause_menu_return_main_menu_button_down,
						new PointF(427, 442)));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);

		PointF touchPoint = new PointF(event.getX(), event.getY());

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
				Intent intent = new Intent();
				intent.setClass(HighScoresMenu.this, MainMenu.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				finish();
			}
		}

		if (event.getAction() == MotionEvent.ACTION_UP) {
			((Button) viewComponentManager
					.getViewComponent(ViewComponentTypes.RETURN_MAIN_MENU_BUTTON))
					.buttonUp();
		}
		viewComponentManager.invalidate();
		return true;
	}
}
