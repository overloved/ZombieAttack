package com.jhu.oose16.zombieattack.view.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.jhu.oose16.zombieattack.listener.ModelListener;
import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.GameModel;
import com.jhu.oose16.zombieattack.model.GameModelState;
import com.jhu.oose16.zombieattack.model.attached.Accelerate;
import com.jhu.oose16.zombieattack.model.attached.Position;
import com.jhu.oose16.zombieattack.model.entity.Entity;
import com.jhu.oose16.zombieattack.view.attached.ModelViewScreenTrans;
import com.jhu.oose16.zombieattack.view.component.Button;
import com.jhu.oose16.zombieattack.view.component.DoubleViewButton;
import com.jhu.oose16.zombieattack.view.component.DoubleViewIcon;
import com.jhu.oose16.zombieattack.view.component.NumberCounter;
import com.jhu.oose16.zombieattack.view.component.ProcessingBar;
import com.jhu.oose16.zombieattack.view.component.Texture;
import com.jhu.oose16.zombieattack.view.component.object.ViewExpirableObjectManager;

/**
 * The game play screen for the game
 */
public class GamePlay extends Menu<GamePlay.ViewComponentTypes> {

	/** The game model */
	GameModel gameModel;

	private NumberCounter zombieNumCounter;
	private NumberCounter scoreCounter;

	public static MediaPlayer backGround_music;

	/** Motion sensor */
	private SensorManager sensorManager;

	private SensorEventListenerImpl sensorEventListenerImpl;

	/**
	 * get the broadcast from the pause menu, go to the main menu, resume or
	 * restart
	 */
	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals("finishGamePlay")) {
				GamePlay.this.finish();
			} else if (intent.getAction().equals("resumeGame")) {
				gameModel.resumeGame();
			} else if (intent.getAction().equals("retryGame")) {
				startNewGame();
			} else if (intent.getAction().equals("moveToNextLevel")) {
				startNextLevel();
			}
		}
	};

	/** The view component in this game play screen */
	public static enum ViewComponentTypes {
		GAME_PLAY_BACKGROUND, GAME_PLAY_PAUSE_BUTTON, GAME_PLAY_MUSIC_BUTTON, GAME_PLAY_PROCESSING_BAR, GAME_PLAY_BOULDER_1, GAME_PLAY_BOULDER_2, GAME_PLAY_BOULDER_3, GAME_PLAY_SCIENTIST_1, GAME_PLAY_SCIENTIST_2, GAME_PLAY_SCIENTIST_3
	};

	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		gameModel.pauseGame();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// register this class as a listener for the orientation and
		// accelerometer sensors
		sensorEventListenerImpl = new SensorEventListenerImpl();
		sensorManager.registerListener(sensorEventListenerImpl,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);

		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("finishGamePlay");
		intentFilter.addAction("resumeGame");
		intentFilter.addAction("retryGame");
		intentFilter.addAction("moveToNextLevel");
		registerReceiver(broadcastReceiver, intentFilter);
		
		super.onResume();
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		backGround_music = MediaPlayer.create(GamePlay.this, R.raw.scare_song);
		backGround_music.setLooping(true);		
		if(MainMenu.music_sound_on == false) {
			backGround_music.setVolume(0, 0);
		}
//		else {
//			backGround_music.setVolume(100, 100);
//		}
		backGround_music.start();
		
		// add buttons
		addViewComponent(ViewComponentTypes.GAME_PLAY_BACKGROUND, new Texture(
				R.drawable.game_play_background, new PointF(427, 240)));

		addViewComponent(ViewComponentTypes.GAME_PLAY_PAUSE_BUTTON, new Button(
				R.drawable.game_play_pause, R.drawable.game_play_pause_down,
				new PointF(26, 30)));

		addViewComponent(
				ViewComponentTypes.GAME_PLAY_MUSIC_BUTTON,
				new DoubleViewButton(R.drawable.game_play_music_on,
						R.drawable.game_play_music_on_down,
						R.drawable.game_play_music_off,
						R.drawable.game_play_music_off_down, new PointF(26, 84)));

		// add processing bar
		addViewComponent(ViewComponentTypes.GAME_PLAY_PROCESSING_BAR,
				new ProcessingBar(24, 480, new PointF(854, 480),
						R.drawable.game_play_processing_bar));

		// add zombie counter
		zombieNumCounter = new NumberCounter(new PointF(815, 60));
		getViewComponentManager().getViewComponentsWithoutKey().addAll(
				zombieNumCounter.getViewComponents());

		// add score counter
		scoreCounter = new NumberCounter(new PointF(815, 10));
		getViewComponentManager().getViewComponentsWithoutKey().addAll(
				scoreCounter.getViewComponents());

		// start the game
		startNewGame();

		// Motion sensor
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		// keep screen on
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	}

	@Override
	protected void onStop() {
		gameModel.endGame();

		unregisterReceiver(broadcastReceiver);
		sensorManager.unregisterListener(sensorEventListenerImpl);

		super.onStop();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);

		PointF touchPoint = new PointF(event.getX(), event.getY());

		PointF touchPointInModel = ModelViewScreenTrans
				.getScreenToModelTransformation().transform(touchPoint);
		if (0 < touchPointInModel.x
				&& touchPointInModel.x < GameModel.getGameFrameWidth()
				&& 0 < touchPointInModel.y
				&& touchPointInModel.y < GameModel.getGameFrameHeight()) {
			gameModel.addNewBoulder(new Position((int) touchPointInModel.x,
					(int) touchPointInModel.y));
		}

		if (viewComponentManager
				.getViewComponent(ViewComponentTypes.GAME_PLAY_PAUSE_BUTTON)
				.getViewRect().isInside(touchPoint)) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				((Button) viewComponentManager
						.getViewComponent(ViewComponentTypes.GAME_PLAY_PAUSE_BUTTON))
						.buttonDown();
				if (MainMenu.music_sound_on) {
					MainMenu.touchSong.start();
				}
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				backGround_music.pause();
				Intent intent = new Intent();
				intent.setClass(GamePlay.this, PauseMenu.class);
				startActivity(intent);
				gameModel.pauseGame();
			}
		}

		if (viewComponentManager
				.getViewComponent(ViewComponentTypes.GAME_PLAY_MUSIC_BUTTON)
				.getViewRect().isInside(touchPoint)) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				((DoubleViewButton) viewComponentManager
						.getViewComponent(ViewComponentTypes.GAME_PLAY_MUSIC_BUTTON))
						.buttonDown();
				if (MainMenu.music_sound_on) {
					MainMenu.touchSong.start();
				}
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				((DoubleViewButton) viewComponentManager
						.getViewComponent(ViewComponentTypes.GAME_PLAY_MUSIC_BUTTON))
						.changeButton();
				MainMenu.music_sound_on = !MainMenu.music_sound_on;
				if(!MainMenu.music_sound_on) {
					backGround_music.pause();
				}
				else {
					backGround_music = MediaPlayer.create(GamePlay.this, R.raw.scare_song);
					backGround_music.setVolume(100, 100);
					backGround_music.start();
				}
			}
		}

		if (event.getAction() == MotionEvent.ACTION_UP) {
			((Button) viewComponentManager
					.getViewComponent(ViewComponentTypes.GAME_PLAY_PAUSE_BUTTON))
					.buttonUp();

			((DoubleViewButton) viewComponentManager
					.getViewComponent(ViewComponentTypes.GAME_PLAY_MUSIC_BUTTON))
					.buttonUp();
		}
		viewComponentManager.invalidate();
		return true;
	}

	@Override
	protected void initializeViewComponentManager() {
		viewComponentManager = new ViewExpirableObjectManager<GamePlay.ViewComponentTypes>(
				this);
	}

	private void startNewGame() {
		Log.d("game play screen", "Start a new game");
		gameModel = new GameModel();
		Log.d("Score", gameModel.getScore() + "");
		startNextLevel();
	}

	private void startNextLevel() {
		Log.d("game play screen", "Start a new Level");

		gameModel.starNextLevel();
		gameModel.pauseGame();
		((ViewExpirableObjectManager<ViewComponentTypes>) viewComponentManager)
				.initializeViewEntityManager(gameModel
						.getExpirableObjectManager());
		gameModel.addModelListener(new ModelListener() {
			@Override
			public void update(GameModelState gameModelState) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						((ProcessingBar) viewComponentManager
								.getViewComponent(ViewComponentTypes.GAME_PLAY_PROCESSING_BAR))
								.setPercentage(gameModel
										.getExpirableObjectManager()
										.getNumLeftZombie()
										/ (float) gameModel.getLevel()
												.getZombieNumberUpperBound());
						zombieNumCounter.updateNum(gameModel.getLevel()
								.getZombieNeedToKill()
								- gameModel.getExpirableObjectManager()
										.getNumDeadZombie());
						scoreCounter.updateNum(gameModel.getScore());
						viewComponentManager.invalidate();
					}
				});
				if (gameModelState == GameModelState.LOSE) {
					backGround_music.pause();
					Intent intent = new Intent();
					intent.setClass(GamePlay.this, LoseMenu.class);
					startActivity(intent);
					gameModel.endGame();
				} else if (gameModelState == GameModelState.WIN_ONE_LEVEL) {
					backGround_music.pause();
					Intent intent = new Intent();
					intent.setClass(GamePlay.this, WinMenu.class);
					startActivity(intent);
					gameModel.endGame();
				}
			}
		});

		// boulder icon
		List<Entity> boulderLeft = gameModel.getExpirableObjectManager()
				.getBouldersLeft();
		List<ViewComponentTypes> viewComponentTypes = new ArrayList<GamePlay.ViewComponentTypes>();
		viewComponentTypes.add(ViewComponentTypes.GAME_PLAY_BOULDER_1);
		viewComponentTypes.add(ViewComponentTypes.GAME_PLAY_BOULDER_2);
		viewComponentTypes.add(ViewComponentTypes.GAME_PLAY_BOULDER_3);

		for (int i = 0; i < 3; i++) {
			int turnOnID = R.drawable.icon_standard_boulder;
			int turnOffID = R.drawable.icon_boulder_used;
			if (boulderLeft.get(i).isExpirableObjectType(
					ExpirableObjectType.EtherealBoulder)) {
				turnOnID = R.drawable.icon_ethereal_boulder;
			} else if (boulderLeft.get(i).isExpirableObjectType(
					ExpirableObjectType.FireBoulder)) {
				turnOnID = R.drawable.icon_fire_boulder;
			} else if (boulderLeft.get(i).isExpirableObjectType(
					ExpirableObjectType.IceBoulder)) {
				turnOnID = R.drawable.icon_ice_boulder;
			} else if (boulderLeft.get(i).isExpirableObjectType(
					ExpirableObjectType.HoverBoulder)) {
				turnOnID = R.drawable.icon_hover_boulder;
			} else if (boulderLeft.get(i).isExpirableObjectType(
					ExpirableObjectType.FireBoulder)) {
				turnOnID = R.drawable.icon_fire_boulder;
			}

			removeViewComponent(viewComponentTypes.get(i));

			addViewComponent(viewComponentTypes.get(i), new DoubleViewIcon(
					turnOnID, turnOffID, new PointF(26, 138 + (48 + 6) * i),
					boulderLeft.get(i)));
		}

		// scientist icon
		List<Entity> scientistLeft = gameModel.getExpirableObjectManager()
				.getEntityList(ExpirableObjectType.Scientist);
		viewComponentTypes.clear();
		viewComponentTypes.add(ViewComponentTypes.GAME_PLAY_SCIENTIST_1);
		viewComponentTypes.add(ViewComponentTypes.GAME_PLAY_SCIENTIST_2);
		viewComponentTypes.add(ViewComponentTypes.GAME_PLAY_SCIENTIST_3);

		for (int i = 0; i < 3; i++) {
			int turnOnID = R.drawable.icon_standard_scientist;
			int turnOffID = R.drawable.icon_standard_scientist_dead;
			if (scientistLeft.get(i).isExpirableObjectType(
					ExpirableObjectType.Tesla)) {
				turnOnID = R.drawable.icon_tesla;
				turnOffID = R.drawable.icon_tesla_dead;
			}

			removeViewComponent(viewComponentTypes.get(i));

			addViewComponent(viewComponentTypes.get(i), new DoubleViewIcon(
					turnOnID, turnOffID, new PointF(26, 306 + (60 + 6) * i),
					scientistLeft.get(i)));
		}

		gameModel.resumeGame();
	}

	private class SensorEventListenerImpl implements SensorEventListener {

		/** How to rate from the gravity to the accelerate */
		private static final float RATE = 2;

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				gameModel.changeAccelerate(new Accelerate(event.values[1]
						* RATE, event.values[0] * RATE));
			}
		}
	}
	
	@Override
	public void onAttachedToWindow() {
		
		super.onAttachedToWindow();
		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);           
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			if (MainMenu.music_sound_on) {
				backGround_music.pause();
			}
		}
		
//		if (keyCode == KeyEvent.KEYCODE_HOME) {
//			if (MainMenu.music_sound_on) {
//				backGround_music.pause();
//			}
//		}
		return super.onKeyDown(keyCode, event);
	}	
	
	
}
