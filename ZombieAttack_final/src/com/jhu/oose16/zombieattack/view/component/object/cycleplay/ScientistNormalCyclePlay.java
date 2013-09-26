package com.jhu.oose16.zombieattack.view.component.object.cycleplay;

import java.util.Arrays;

import com.jhu.oose16.zombieattack.model.objectstate.ExpirableObjectState;
import com.jhu.oose16.zombieattack.view.activities.R;

public class ScientistNormalCyclePlay extends CyclePlay {

	public ScientistNormalCyclePlay(ExpirableObjectState firstObjectState) {
		super(firstObjectState);

		disappearance_left = Arrays.asList(
				R.drawable.scientist_standard_death_left_1,
				R.drawable.scientist_standard_death_left_2,
				R.drawable.scientist_standard_death_left_3,
				R.drawable.scientist_standard_death_left_4,
				R.drawable.scientist_standard_death_left_5,
				R.drawable.scientist_standard_death_left_6,
				R.drawable.scientist_standard_death_left_7,
				R.drawable.scientist_standard_death_left_8,
				R.drawable.scientist_standard_death_left_9,
				R.drawable.scientist_standard_death_left_10,
				R.drawable.scientist_standard_death_left_11,
				R.drawable.scientist_standard_death_left_12);
		disappearance_right = Arrays.asList(
				R.drawable.scientist_standard_death_right_1,
				R.drawable.scientist_standard_death_right_2,
				R.drawable.scientist_standard_death_right_3,
				R.drawable.scientist_standard_death_right_4,
				R.drawable.scientist_standard_death_right_5,
				R.drawable.scientist_standard_death_right_6,
				R.drawable.scientist_standard_death_right_7,
				R.drawable.scientist_standard_death_right_8,
				R.drawable.scientist_standard_death_right_9,
				R.drawable.scientist_standard_death_right_10,
				R.drawable.scientist_standard_death_right_11,
				R.drawable.scientist_standard_death_right_12);
		disappearance_up = Arrays.asList(
				R.drawable.scientist_standard_death_up_1,
				R.drawable.scientist_standard_death_up_2,
				R.drawable.scientist_standard_death_up_3,
				R.drawable.scientist_standard_death_up_4,
				R.drawable.scientist_standard_death_up_5,
				R.drawable.scientist_standard_death_up_6,
				R.drawable.scientist_standard_death_up_7,
				R.drawable.scientist_standard_death_up_8,
				R.drawable.scientist_standard_death_up_9,
				R.drawable.scientist_standard_death_up_10,
				R.drawable.scientist_standard_death_up_11,
				R.drawable.scientist_standard_death_up_12);
		disappearance_down = Arrays.asList(
				R.drawable.scientist_standard_death_down_1,
				R.drawable.scientist_standard_death_down_2,
				R.drawable.scientist_standard_death_down_3,
				R.drawable.scientist_standard_death_down_4,
				R.drawable.scientist_standard_death_down_5,
				R.drawable.scientist_standard_death_down_6,
				R.drawable.scientist_standard_death_down_7,
				R.drawable.scientist_standard_death_down_8,
				R.drawable.scientist_standard_death_down_9,
				R.drawable.scientist_standard_death_down_10,
				R.drawable.scientist_standard_death_down_11,
				R.drawable.scientist_standard_death_down_12);

		running_left = Arrays.asList(R.drawable.scientist_standard_run_left_1,
				R.drawable.scientist_standard_run_left_2,
				R.drawable.scientist_standard_run_left_3,
				R.drawable.scientist_standard_run_left_4,
				R.drawable.scientist_standard_run_left_5,
				R.drawable.scientist_standard_run_left_6,
				R.drawable.scientist_standard_run_left_7,
				R.drawable.scientist_standard_run_left_8);
		running_right = Arrays.asList(
				R.drawable.scientist_standard_run_right_1,
				R.drawable.scientist_standard_run_right_2,
				R.drawable.scientist_standard_run_right_3,
				R.drawable.scientist_standard_run_right_4,
				R.drawable.scientist_standard_run_right_5,
				R.drawable.scientist_standard_run_right_6,
				R.drawable.scientist_standard_run_right_7,
				R.drawable.scientist_standard_run_right_8);
		running_up = Arrays.asList(R.drawable.scientist_standard_run_up_1,
				R.drawable.scientist_standard_run_up_2,
				R.drawable.scientist_standard_run_up_3,
				R.drawable.scientist_standard_run_up_4,
				R.drawable.scientist_standard_run_up_5,
				R.drawable.scientist_standard_run_up_6,
				R.drawable.scientist_standard_run_up_7,
				R.drawable.scientist_standard_run_up_8);
		running_down = Arrays.asList(R.drawable.scientist_standard_run_down_1,
				R.drawable.scientist_standard_run_down_2,
				R.drawable.scientist_standard_run_down_3,
				R.drawable.scientist_standard_run_down_4,
				R.drawable.scientist_standard_run_down_5,
				R.drawable.scientist_standard_run_down_6,
				R.drawable.scientist_standard_run_down_7,
				R.drawable.scientist_standard_run_down_8);

		running_invincible_left = Arrays.asList(
				R.drawable.scientist_standard_invincible_left_1,
				R.drawable.scientist_standard_invincible_left_2,
				R.drawable.scientist_standard_invincible_left_3,
				R.drawable.scientist_standard_invincible_left_4,
				R.drawable.scientist_standard_invincible_left_5,
				R.drawable.scientist_standard_invincible_left_6,
				R.drawable.scientist_standard_invincible_left_7,
				R.drawable.scientist_standard_invincible_left_8);
		running_invincible_right = Arrays.asList(
				R.drawable.scientist_standard_invincible_right_1,
				R.drawable.scientist_standard_invincible_right_2,
				R.drawable.scientist_standard_invincible_right_3,
				R.drawable.scientist_standard_invincible_right_4,
				R.drawable.scientist_standard_invincible_right_5,
				R.drawable.scientist_standard_invincible_right_6,
				R.drawable.scientist_standard_invincible_right_7,
				R.drawable.scientist_standard_invincible_right_8);
		running_invincible_up = Arrays.asList(
				R.drawable.scientist_standard_invincible_up_1,
				R.drawable.scientist_standard_invincible_up_2,
				R.drawable.scientist_standard_invincible_up_3,
				R.drawable.scientist_standard_invincible_up_4,
				R.drawable.scientist_standard_invincible_up_5,
				R.drawable.scientist_standard_invincible_up_6,
				R.drawable.scientist_standard_invincible_up_7,
				R.drawable.scientist_standard_invincible_up_8);
		running_invincible_down = Arrays.asList(
				R.drawable.scientist_standard_invincible_down_1,
				R.drawable.scientist_standard_invincible_down_2,
				R.drawable.scientist_standard_invincible_down_3,
				R.drawable.scientist_standard_invincible_down_4,
				R.drawable.scientist_standard_invincible_down_5,
				R.drawable.scientist_standard_invincible_down_6,
				R.drawable.scientist_standard_invincible_down_7,
				R.drawable.scientist_standard_invincible_down_8);

		standing_left = Arrays.asList(R.drawable.scientist_standard_run_left_1);
		standing_right = Arrays
				.asList(R.drawable.scientist_standard_run_right_1);
		standing_up = Arrays.asList(R.drawable.scientist_standard_run_up_1);
		standing_down = Arrays.asList(R.drawable.scientist_standard_run_down_1);

		standing_invincible_left = Arrays
				.asList(R.drawable.scientist_standard_invincible_left_1);
		standing_invincible_right = Arrays
				.asList(R.drawable.scientist_standard_invincible_right_1);
		standing_invincible_up = Arrays
				.asList(R.drawable.scientist_standard_invincible_up_1);
		standing_invincible_down = Arrays
				.asList(R.drawable.scientist_standard_invincible_down_1);

		initializeBitmap();
	}
}
