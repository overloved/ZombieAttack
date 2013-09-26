package com.jhu.oose16.zombieattack.view.component.object.cycleplay;

import java.util.Arrays;

import com.jhu.oose16.zombieattack.model.objectstate.ExpirableObjectState;
import com.jhu.oose16.zombieattack.view.activities.R;

public class BoulderEtherealCyclePlay extends CyclePlay {

	public BoulderEtherealCyclePlay(ExpirableObjectState firstObjectState) {
		super(firstObjectState);

		appearance = Arrays.asList(R.drawable.boulder_ethereal_appearance_1,
				R.drawable.boulder_ethereal_appearance_2,
				R.drawable.boulder_ethereal_appearance_3,
				R.drawable.boulder_ethereal_appearance_4,
				R.drawable.boulder_ethereal_appearance_5,
				R.drawable.boulder_ethereal_appearance_6,
				R.drawable.boulder_ethereal_appearance_7,
				R.drawable.boulder_ethereal_appearance_8,
				R.drawable.boulder_ethereal_appearance_9,
				R.drawable.boulder_ethereal_appearance_10);

		disappearance_left = Arrays.asList(
				R.drawable.boulder_ethereal_destruction_1,
				R.drawable.boulder_ethereal_destruction_2,
				R.drawable.boulder_ethereal_destruction_3,
				R.drawable.boulder_ethereal_destruction_4,
				R.drawable.boulder_ethereal_destruction_5,
				R.drawable.boulder_ethereal_destruction_6,
				R.drawable.boulder_ethereal_destruction_7,
				R.drawable.boulder_ethereal_destruction_8,
				R.drawable.boulder_ethereal_destruction_9,
				R.drawable.boulder_ethereal_destruction_10);

		disappearance_up = Arrays.asList(
				R.drawable.boulder_ethereal_destruction_1,
				R.drawable.boulder_ethereal_destruction_2,
				R.drawable.boulder_ethereal_destruction_3,
				R.drawable.boulder_ethereal_destruction_4,
				R.drawable.boulder_ethereal_destruction_5,
				R.drawable.boulder_ethereal_destruction_6,
				R.drawable.boulder_ethereal_destruction_7,
				R.drawable.boulder_ethereal_destruction_8,
				R.drawable.boulder_ethereal_destruction_9,
				R.drawable.boulder_ethereal_destruction_10);

		disappearance_right = Arrays.asList(
				R.drawable.boulder_ethereal_destruction_1,
				R.drawable.boulder_ethereal_destruction_2,
				R.drawable.boulder_ethereal_destruction_3,
				R.drawable.boulder_ethereal_destruction_4,
				R.drawable.boulder_ethereal_destruction_5,
				R.drawable.boulder_ethereal_destruction_6,
				R.drawable.boulder_ethereal_destruction_7,
				R.drawable.boulder_ethereal_destruction_8,
				R.drawable.boulder_ethereal_destruction_9,
				R.drawable.boulder_ethereal_destruction_10);

		disappearance_down = Arrays.asList(
				R.drawable.boulder_ethereal_destruction_1,
				R.drawable.boulder_ethereal_destruction_2,
				R.drawable.boulder_ethereal_destruction_3,
				R.drawable.boulder_ethereal_destruction_4,
				R.drawable.boulder_ethereal_destruction_5,
				R.drawable.boulder_ethereal_destruction_6,
				R.drawable.boulder_ethereal_destruction_7,
				R.drawable.boulder_ethereal_destruction_8,
				R.drawable.boulder_ethereal_destruction_9,
				R.drawable.boulder_ethereal_destruction_10);

		running_left = Arrays.asList(R.drawable.boulder_ethereal_roll_left_1,
				R.drawable.boulder_ethereal_roll_left_2,
				R.drawable.boulder_ethereal_roll_left_3,
				R.drawable.boulder_ethereal_roll_left_4,
				R.drawable.boulder_ethereal_roll_left_5,
				R.drawable.boulder_ethereal_roll_left_6,
				R.drawable.boulder_ethereal_roll_left_7,
				R.drawable.boulder_ethereal_roll_left_8);
		running_right = Arrays.asList(R.drawable.boulder_ethereal_roll_right_1,
				R.drawable.boulder_ethereal_roll_right_2,
				R.drawable.boulder_ethereal_roll_right_3,
				R.drawable.boulder_ethereal_roll_right_4,
				R.drawable.boulder_ethereal_roll_right_5,
				R.drawable.boulder_ethereal_roll_right_6,
				R.drawable.boulder_ethereal_roll_right_7,
				R.drawable.boulder_ethereal_roll_right_8);
		running_up = Arrays.asList(R.drawable.boulder_ethereal_roll_up_1,
				R.drawable.boulder_ethereal_roll_up_2,
				R.drawable.boulder_ethereal_roll_up_3,
				R.drawable.boulder_ethereal_roll_up_4,
				R.drawable.boulder_ethereal_roll_up_5,
				R.drawable.boulder_ethereal_roll_up_6,
				R.drawable.boulder_ethereal_roll_up_7,
				R.drawable.boulder_ethereal_roll_up_8);
		running_down = Arrays.asList(R.drawable.boulder_ethereal_roll_down_1,
				R.drawable.boulder_ethereal_roll_down_2,
				R.drawable.boulder_ethereal_roll_down_3,
				R.drawable.boulder_ethereal_roll_down_4,
				R.drawable.boulder_ethereal_roll_down_5,
				R.drawable.boulder_ethereal_roll_down_6,
				R.drawable.boulder_ethereal_roll_down_7,
				R.drawable.boulder_ethereal_roll_down_8);

		initializeBitmap();
	}
}
