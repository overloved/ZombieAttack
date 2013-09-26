package com.jhu.oose16.zombieattack.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorialMenu extends Activity {

	private TextView welcomeTextView;
	private TextView pauseInstruction;
	private TextView soundInstruction;
	private TextView boulderInstruction;
	private TextView scientistInstruction;
	private TextView tryBoulderInstruction;
	private TextView playInstruction;
	private TextView startGameInstruction;
	private TextView differentBoulderInstruction;
	private TextView workshopInstruction;

	private ImageView pauseImageView;
	private ImageView soundOnImageView;
	private ImageView normalBoulderImageView;
	private ImageView etherealImageView;
	private ImageView fireBoulderImageView;
	private ImageView scientistImageView;
	private ImageView tesla1ImageView;
	private ImageView tesla2ImageView;
	private ImageView tap1ImageView;
	private ImageView tap2ImageView;
	private ImageView pauseArrowImageView;
	private ImageView soundArrowImageView;
	private ImageView boulderArrowImageView;
	private ImageView scientistArrowImageView;
	private ImageView clickBoulderImageView;
	private ImageView normalZombieImageView;
	private ImageView showNormalBoulderImageView;
	private ImageView playUpImageView;
	private ImageView playDownImageView;

	private ImageView mainMenuUpImageView;
	private ImageView mainMenuDownImageView;
	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstaBundle) {
		super.onCreate(savedInstaBundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.tutorial_level);

		welcomeTextView = (TextView) findViewById(R.id.tutorial_welcome_textview);
		pauseInstruction = (TextView) findViewById(R.id.tutorial_pause_instr);
		soundInstruction = (TextView) findViewById(R.id.tutorial_sound_instr);
		boulderInstruction = (TextView) findViewById(R.id.tutorial_boulder_instr);
		scientistInstruction = (TextView) findViewById(R.id.tutorial_scientist_instr);
		tryBoulderInstruction = (TextView) findViewById(R.id.tutorial_tryBoulderTextView);
		playInstruction = (TextView) findViewById(R.id.tutorial_boulder_play);
		startGameInstruction = (TextView) findViewById(R.id.tutorial_start_game_textView);
		differentBoulderInstruction = (TextView) findViewById(R.id.tutorial_different_boulder);
		workshopInstruction = (TextView) findViewById(R.id.tutorial_workshop);

		pauseImageView = (ImageView) findViewById(R.id.tutorial_pause_image);
		soundOnImageView = (ImageView) findViewById(R.id.tutorial_music_on_image);
		normalBoulderImageView = (ImageView) findViewById(R.id.tutorial_boulder_standard);
		etherealImageView = (ImageView) findViewById(R.id.tutorial_boulder_ethereal);
		fireBoulderImageView = (ImageView) findViewById(R.id.tutorial_boulder_fire);
		scientistImageView = (ImageView) findViewById(R.id.tutorial_normal_scientist);
		tesla1ImageView = (ImageView) findViewById(R.id.tutorial_tesla_1);
		tesla2ImageView = (ImageView) findViewById(R.id.tutorial_tesla_2);
		normalZombieImageView = (ImageView) findViewById(R.id.tutorial_normal_zombie_image);
		showNormalBoulderImageView = (ImageView) findViewById(R.id.tutorial_shown_standard_boulder);

		tap1ImageView = (ImageView) findViewById(R.id.tutorial_tapButton1);
		tap2ImageView = (ImageView) findViewById(R.id.tutorial_tapButton2);
		clickBoulderImageView = (ImageView) findViewById(R.id.tutorial_click_boulder_tapButton);
		pauseArrowImageView = (ImageView) findViewById(R.id.tutorial_pauseArrow);
		soundArrowImageView = (ImageView) findViewById(R.id.tutorial_soundArrow);
		boulderArrowImageView = (ImageView) findViewById(R.id.tutorial_boulderArrow);
		scientistArrowImageView = (ImageView) findViewById(R.id.tutorial_scientistArrow);
		playUpImageView = (ImageView) findViewById(R.id.tutorial_play_up_imageView);
		playDownImageView = (ImageView) findViewById(R.id.tutorial_play_down_imageView);
		mainMenuUpImageView = (ImageView) findViewById(R.id.tutorial_mainMenu_button_up);
		mainMenuDownImageView = (ImageView) findViewById(R.id.tutorial_mainMenu_button_down);

		mainMenuUpImageView.setVisibility(View.VISIBLE);

		welcomeTextView.setText("Welcome to ZOMBIE WORLD!" + "\n\n"
				+ "Let's start with a short tutorial. "
				+ "We will know each feature.");
		welcomeTextView.startAnimation(AnimationUtils.loadAnimation(
				TutorialMenu.this, R.anim.welcome_fade_animation));
		welcomeTextView.setVisibility(View.INVISIBLE);
		pauseInstruction.setText("Pause, " + "Restart, " + "Quit game");
		pauseInstruction.startAnimation(AnimationUtils.loadAnimation(
				TutorialMenu.this, R.anim.explain_fade_animation));
		soundInstruction.setText("Sound on / off");
		soundInstruction.startAnimation(AnimationUtils.loadAnimation(
				TutorialMenu.this, R.anim.explain_fade_animation));
		pauseImageView.startAnimation(AnimationUtils.loadAnimation(
				TutorialMenu.this, R.anim.sound_pause_animation));
		soundOnImageView.startAnimation(AnimationUtils.loadAnimation(
				TutorialMenu.this, R.anim.sound_pause_animation));
		pauseArrowImageView.startAnimation(AnimationUtils.loadAnimation(
				TutorialMenu.this, R.anim.explain_fade_animation));
		soundArrowImageView.startAnimation(AnimationUtils.loadAnimation(
				TutorialMenu.this, R.anim.explain_fade_animation));
		Handler mHandler = new Handler();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				pauseImageView.setVisibility(View.VISIBLE);
				soundOnImageView.setVisibility(View.VISIBLE);
			}
		};
		mHandler.postDelayed(runnable, 8000);

		Animation boulderAnimation = new AlphaAnimation(0, 1);
		boulderAnimation.setDuration(3000);
		boulderAnimation.setStartOffset(12000);
		normalBoulderImageView.startAnimation(boulderAnimation);
		etherealImageView.startAnimation(boulderAnimation);
		fireBoulderImageView.setAnimation(boulderAnimation);
		boulderArrowImageView.setAnimation(boulderAnimation);
		boulderInstruction
				.setText("Show three Boulder types you choose for this level."
						+ "\n\n"
						+ "Different boulder has different effect to zombie. "
						+ "\n\n"
						+ "Default we only have three normal boulder without any effect.");
		boulderInstruction.startAnimation(boulderAnimation);

		Handler boulderHandler = new Handler();
		Runnable boulderRunnable = new Runnable() {

			@Override
			public void run() {
				normalBoulderImageView.setVisibility(View.VISIBLE);
				etherealImageView.setVisibility(View.VISIBLE);
				fireBoulderImageView.setVisibility(View.VISIBLE);
				boulderInstruction.setVisibility(View.VISIBLE);
				boulderArrowImageView.setVisibility(View.VISIBLE);
			}
		};
		boulderHandler.postDelayed(boulderRunnable, 15000);

		Handler scientistHandler = new Handler();
		Runnable scientistRunnable = new Runnable() {

			@Override
			public void run() {
				
				tap1ImageView.setVisibility(View.VISIBLE);
			}
		};
		scientistHandler.postDelayed(scientistRunnable, 18000);

		/*
		 * Add tap button, and animation
		 */

		tap1ImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tap1ImageView.setVisibility(View.INVISIBLE);
				boulderInstruction.setVisibility(View.INVISIBLE);
				boulderArrowImageView.setVisibility(View.INVISIBLE);
				scientistInstruction
						.setText("Every level we have three scientists, either normal or tesla. "
								+ "\n\n"
								+ "Both zombie and boulder can kill them. "
								+ "\n\n"
								+ "Rescue scientist to build powerful weapon for the next level."
								+ "\n\n"
								+ "You'll get nothing if scientist died.");
				Animation scientistInstrAnimation = new AlphaAnimation(0, 1);
				scientistInstrAnimation.setDuration(3000);
				scientistInstruction.startAnimation(scientistInstrAnimation);
				scientistImageView.startAnimation(scientistInstrAnimation);
				tesla1ImageView.startAnimation(scientistInstrAnimation);
				tesla2ImageView.startAnimation(scientistInstrAnimation);
				scientistArrowImageView.startAnimation(scientistInstrAnimation);

				Handler scientistInstrHandler = new Handler();
				Runnable scientistInstrRunnable = new Runnable() {

					@Override
					public void run() {

						scientistInstruction.setVisibility(View.VISIBLE);
						scientistArrowImageView.setVisibility(View.VISIBLE);
						tap2ImageView.setVisibility(View.VISIBLE);
					}
				};
				scientistInstrHandler.postDelayed(scientistInstrRunnable, 3000);
			}
		});

		tap2ImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tap2ImageView.setVisibility(View.INVISIBLE);
				scientistInstruction.setVisibility(View.INVISIBLE);
				scientistArrowImageView.setVisibility(View.INVISIBLE);
				tryBoulderInstruction
						.setText("Now, let's use a boulder to kill a zombie."
								+ "\n\n" + "Watch!");
				Animation tryBoulderShownAnimation = new AlphaAnimation(0, 1);
				tryBoulderShownAnimation.setDuration(3000);
				tryBoulderShownAnimation.setStartOffset(1000);

				Animation tryBoulderHideAnimation = new AlphaAnimation(1, 0);
				tryBoulderHideAnimation.setDuration(3000);
				tryBoulderHideAnimation.setStartOffset(4000);

				AnimationSet tryBoulderAnimationSet = new AnimationSet(false);
				tryBoulderAnimationSet.addAnimation(tryBoulderShownAnimation);
				tryBoulderAnimationSet.addAnimation(tryBoulderHideAnimation);
				tryBoulderInstruction.setAnimation(tryBoulderAnimationSet);

				Handler zombieComingHandler = new Handler();
				Runnable zombieComingRunnable = new Runnable() {

					@Override
					public void run() {
						normalZombieImageView.setVisibility(View.VISIBLE);
						scientistImageView.setVisibility(View.VISIBLE);
						tesla1ImageView.setVisibility(View.VISIBLE);
						tesla2ImageView.setVisibility(View.VISIBLE);
					}
				};
				zombieComingHandler.postDelayed(zombieComingRunnable, 7000);

				Handler clickBoulderHandler = new Handler();
				Runnable clickBoudlerRunnable = new Runnable() {

					@Override
					public void run() {
						clickBoulderImageView.setVisibility(View.VISIBLE);
					}
				};
				clickBoulderHandler.postDelayed(clickBoudlerRunnable, 7500);
			}
		});

		clickBoulderImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clickBoulderImageView.setVisibility(View.INVISIBLE);
				showNormalBoulderImageView.setVisibility(View.VISIBLE);

				playInstruction
						.setText("You control the boulder by tilting, hit the zombie."
								+ "\n\n"
								+ "For specific zombie, you need to hit multiple times to kill it.");
				Animation playBoulderShownAnimation = new AlphaAnimation(0, 1);
				playBoulderShownAnimation.setDuration(3000);
				playBoulderShownAnimation.setStartOffset(500);
				playInstruction.startAnimation(playBoulderShownAnimation);

				Animation playBoulderHideAnimation = new AlphaAnimation(1, 0);
				playBoulderHideAnimation.setDuration(2000);
				playBoulderHideAnimation.setStartOffset(3500);
				playInstruction.startAnimation(playBoulderHideAnimation);
				showNormalBoulderImageView
						.startAnimation(playBoulderHideAnimation);
				normalZombieImageView.startAnimation(playBoulderHideAnimation);

				AnimationSet playBoulderAnimationSet = new AnimationSet(false);
				playBoulderAnimationSet.addAnimation(playBoulderShownAnimation);
				playBoulderAnimationSet.addAnimation(playBoulderHideAnimation);
				playInstruction.setAnimation(playBoulderAnimationSet);
				
				Handler differentBoulderHandler = new Handler();
				Runnable differentBoulderRunnable = new Runnable() {
					
					@Override
					public void run() {
						showNormalBoulderImageView.setVisibility(View.INVISIBLE);
						normalZombieImageView.setVisibility(View.INVISIBLE);
						differentBoulderInstruction.setText("Different boulder has their own skills.\n\n" 
								+ "Ice boulder freezes zombie.\n\n" + "Fire boulder has higher damage that can burn zombie.\n\n"
								+ "Hover boulder will not hit scientist, and can go accross barriers.");
						
						Animation differentBoulderShownAnimation = new AlphaAnimation(0, 1);
						differentBoulderShownAnimation.setDuration(3000);
						differentBoulderShownAnimation.setStartOffset(3000);
						differentBoulderInstruction.startAnimation(differentBoulderShownAnimation);
						
						Animation differentBoulderHoldAnimation = new AlphaAnimation(1, 1);
						differentBoulderHoldAnimation.setDuration(2000);
						differentBoulderHoldAnimation.setStartOffset(6000);
						differentBoulderInstruction.startAnimation(differentBoulderHoldAnimation);
						
						Animation differentBoulderHideAnimation = new AlphaAnimation(1, 0);
						differentBoulderHideAnimation.setDuration(3000);
						differentBoulderHideAnimation.setStartOffset(8000);
						differentBoulderInstruction.startAnimation(differentBoulderHideAnimation);
						differentBoulderInstruction.startAnimation(differentBoulderHideAnimation);
						
						AnimationSet differentBoulderAnimationSet = new AnimationSet(false);
						differentBoulderAnimationSet.addAnimation(differentBoulderShownAnimation);
						differentBoulderAnimationSet.addAnimation(differentBoulderHideAnimation);
						differentBoulderInstruction.setAnimation(differentBoulderAnimationSet);
							
					}
				};
				differentBoulderHandler.postDelayed(differentBoulderRunnable, 3000);
				
				Handler workShopHandler = new Handler();
				Runnable workShopRunnable = new Runnable() {
					
					@Override
					public void run() {
						
						workshopInstruction.setText("After we kill enough zombie, you will pass current level.\n\n" +
								"You will enter into the workshop, and choose three boulders\n\n" +
								"you want to use in the next level.\n\n" +
								"Be careful to choose, or you will loss!");
						
						Animation workshopShownAnimation = new AlphaAnimation(0, 1);
						workshopShownAnimation.setDuration(3000);
						workshopShownAnimation.setStartOffset(8000);
						workshopInstruction.startAnimation(workshopShownAnimation);
						
						Animation workshopHoldAnimation = new AlphaAnimation(1, 1);
						workshopHoldAnimation.setDuration(2000);
						workshopHoldAnimation.setStartOffset(11000);
						workshopInstruction.startAnimation(workshopHoldAnimation);
						
						Animation workshopHideAnimation = new AlphaAnimation(1, 0);
						workshopHideAnimation.setDuration(3000);
						workshopHideAnimation.setStartOffset(13000);
						workshopInstruction.startAnimation(workshopHideAnimation);
						workshopInstruction.startAnimation(workshopHideAnimation);
						
						AnimationSet workshopAnimationSet = new AnimationSet(false);
						workshopAnimationSet.addAnimation(workshopShownAnimation);
						workshopAnimationSet.addAnimation(workshopHideAnimation);
						workshopInstruction.setAnimation(workshopAnimationSet);
							
					}
				};
				workShopHandler.postDelayed(workShopRunnable, 7000);

				Handler startGameHandler = new Handler();
				Runnable startGameRunnable = new Runnable() {

					@Override
					public void run() {
						showNormalBoulderImageView
								.setVisibility(View.INVISIBLE);
						normalZombieImageView.setVisibility(View.INVISIBLE);
						startGameInstruction.setText("OK..Well Done! " + "\n\n"
								+ "Now we can start to our ZOMBIE adventure!");
						Animation startAnimation = new AlphaAnimation(0, 1);
						startAnimation.setDuration(2000);
						startAnimation.setStartOffset(10000);
						startGameInstruction.startAnimation(startAnimation);
						playUpImageView.startAnimation(startAnimation);
					}
				};
				startGameHandler.postDelayed(startGameRunnable, 13500);

				Handler keepHandler = new Handler();
				Runnable keepRunnable = new Runnable() {

					@Override
					public void run() {
						startGameInstruction.setVisibility(View.VISIBLE);
						playUpImageView.setVisibility(View.VISIBLE);
					}
				};
				keepHandler.postDelayed(keepRunnable, 14500);
			}
		});
		
		playUpImageView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN: {
						playUpImageView.setVisibility(View.INVISIBLE);
						playDownImageView.setVisibility(View.VISIBLE);
						if (MainMenu.music_sound_on) {
							MainMenu.touchSong.start();
						}
						break;
					}
					case MotionEvent.ACTION_UP: {
						Intent intent = new Intent();
						intent.setClass(TutorialMenu.this, GamePlay.class);
						startActivity(intent);
						finish();
					}
				}
				return true;
			}
		});
		
		mainMenuUpImageView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN: {
						mainMenuUpImageView.setVisibility(View.INVISIBLE);
						mainMenuDownImageView.setVisibility(View.VISIBLE);
						if (MainMenu.music_sound_on) {
							MainMenu.touchSong.start();
						}
						break;
					}
					case MotionEvent.ACTION_UP: {
						intent = new Intent();
						intent.setClass(TutorialMenu.this, MainMenu.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
						startActivity(intent);
						finish();
					}
				}
				return true;
			}
		});

	}
}
