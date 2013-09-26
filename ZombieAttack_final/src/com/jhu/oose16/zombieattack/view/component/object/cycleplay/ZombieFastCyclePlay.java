package com.jhu.oose16.zombieattack.view.component.object.cycleplay;

import java.util.Arrays;

import com.jhu.oose16.zombieattack.model.objectstate.ExpirableObjectState;
import com.jhu.oose16.zombieattack.view.activities.R;

public class ZombieFastCyclePlay extends CyclePlay {

	public ZombieFastCyclePlay(ExpirableObjectState firstObjectState) {
		super(firstObjectState);

		disappearance_left = Arrays.asList(
				R.drawable.zombie_fast_death_left_1,
				R.drawable.zombie_fast_death_left_2,
				R.drawable.zombie_fast_death_left_3,
				R.drawable.zombie_fast_death_left_4,
				R.drawable.zombie_fast_death_left_5,
				R.drawable.zombie_fast_death_left_6,
				R.drawable.zombie_fast_death_left_7,
				R.drawable.zombie_fast_death_left_8,
				R.drawable.zombie_fast_death_left_9,
				R.drawable.zombie_fast_death_left_10);
		disappearance_right = Arrays.asList(
				R.drawable.zombie_fast_death_right_1,
				R.drawable.zombie_fast_death_right_2,
				R.drawable.zombie_fast_death_right_3,
				R.drawable.zombie_fast_death_right_4,
				R.drawable.zombie_fast_death_right_5,
				R.drawable.zombie_fast_death_right_6,
				R.drawable.zombie_fast_death_right_7,
				R.drawable.zombie_fast_death_right_8,
				R.drawable.zombie_fast_death_right_9,
				R.drawable.zombie_fast_death_right_10);
		disappearance_up = Arrays.asList(R.drawable.zombie_fast_death_up_1,
				R.drawable.zombie_fast_death_up_2,
				R.drawable.zombie_fast_death_up_3,
				R.drawable.zombie_fast_death_up_4,
				R.drawable.zombie_fast_death_up_5,
				R.drawable.zombie_fast_death_up_6,
				R.drawable.zombie_fast_death_up_7,
				R.drawable.zombie_fast_death_up_8,
				R.drawable.zombie_fast_death_up_9,
				R.drawable.zombie_fast_death_up_10);
		disappearance_down = Arrays.asList(
				R.drawable.zombie_fast_death_down_1,
				R.drawable.zombie_fast_death_down_2,
				R.drawable.zombie_fast_death_down_3,
				R.drawable.zombie_fast_death_down_4,
				R.drawable.zombie_fast_death_down_5,
				R.drawable.zombie_fast_death_down_6,
				R.drawable.zombie_fast_death_down_7,
				R.drawable.zombie_fast_death_down_8,
				R.drawable.zombie_fast_death_down_9,
				R.drawable.zombie_fast_death_down_10);

		running_left = Arrays.asList(R.drawable.zombie_fast_walk_left_1,
				R.drawable.zombie_fast_walk_left_2,
				R.drawable.zombie_fast_walk_left_3,
				R.drawable.zombie_fast_walk_left_4,
				R.drawable.zombie_fast_walk_left_5,
				R.drawable.zombie_fast_walk_left_6,
				R.drawable.zombie_fast_walk_left_7,
				R.drawable.zombie_fast_walk_left_8);
		running_right = Arrays.asList(R.drawable.zombie_fast_walk_right_1,
				R.drawable.zombie_fast_walk_right_2,
				R.drawable.zombie_fast_walk_right_3,
				R.drawable.zombie_fast_walk_right_4,
				R.drawable.zombie_fast_walk_right_5,
				R.drawable.zombie_fast_walk_right_6,
				R.drawable.zombie_fast_walk_right_7,
				R.drawable.zombie_fast_walk_right_8);
		running_up = Arrays.asList(R.drawable.zombie_fast_walk_up_1,
				R.drawable.zombie_fast_walk_up_2,
				R.drawable.zombie_fast_walk_up_3,
				R.drawable.zombie_fast_walk_up_4,
				R.drawable.zombie_fast_walk_up_5,
				R.drawable.zombie_fast_walk_up_6,
				R.drawable.zombie_fast_walk_up_7,
				R.drawable.zombie_fast_walk_up_8);
		running_down = Arrays.asList(R.drawable.zombie_fast_walk_down_1,
				R.drawable.zombie_fast_walk_down_2,
				R.drawable.zombie_fast_walk_down_3,
				R.drawable.zombie_fast_walk_down_4,
				R.drawable.zombie_fast_walk_down_5,
				R.drawable.zombie_fast_walk_down_6,
				R.drawable.zombie_fast_walk_down_7,
				R.drawable.zombie_fast_walk_down_8);

		running_invincible_left = Arrays.asList(
				R.drawable.zombie_fast_injury_left_1,
				R.drawable.zombie_fast_injury_left_2,
				R.drawable.zombie_fast_injury_left_3,
				R.drawable.zombie_fast_injury_left_4,
				R.drawable.zombie_fast_injury_left_5,
				R.drawable.zombie_fast_injury_left_6);
		running_invincible_right = Arrays.asList(
				R.drawable.zombie_fast_injury_right_1,
				R.drawable.zombie_fast_injury_right_2,
				R.drawable.zombie_fast_injury_right_3,
				R.drawable.zombie_fast_injury_right_4,
				R.drawable.zombie_fast_injury_right_5,
				R.drawable.zombie_fast_injury_right_6);
		running_invincible_up = Arrays.asList(
				R.drawable.zombie_fast_injury_up_1,
				R.drawable.zombie_fast_injury_up_2,
				R.drawable.zombie_fast_injury_up_3,
				R.drawable.zombie_fast_injury_up_4,
				R.drawable.zombie_fast_injury_up_5,
				R.drawable.zombie_fast_injury_up_6);
		running_invincible_down = Arrays.asList(
				R.drawable.zombie_fast_injury_down_1,
				R.drawable.zombie_fast_injury_down_2,
				R.drawable.zombie_fast_injury_down_3,
				R.drawable.zombie_fast_injury_down_4,
				R.drawable.zombie_fast_injury_down_5,
				R.drawable.zombie_fast_injury_down_6);

		standing_left = Arrays.asList(R.drawable.zombie_fast_walk_left_1);
		standing_right = Arrays.asList(R.drawable.zombie_fast_walk_right_1);
		standing_up = Arrays.asList(R.drawable.zombie_fast_walk_up_1);
		standing_down = Arrays.asList(R.drawable.zombie_fast_walk_down_1);

		standing_invincible_left = Arrays
				.asList(R.drawable.zombie_fast_injury_left_1);
		standing_invincible_right = Arrays
				.asList(R.drawable.zombie_fast_injury_right_1);
		standing_invincible_up = Arrays
				.asList(R.drawable.zombie_fast_injury_up_1);
		standing_invincible_down = Arrays
				.asList(R.drawable.zombie_fast_injury_down_1);

		initializeBitmap();
	}
}
