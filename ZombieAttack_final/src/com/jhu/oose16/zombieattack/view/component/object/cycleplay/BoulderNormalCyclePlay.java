package com.jhu.oose16.zombieattack.view.component.object.cycleplay;

import java.util.Arrays;

import com.jhu.oose16.zombieattack.model.objectstate.ExpirableObjectState;
import com.jhu.oose16.zombieattack.view.activities.R;

public class BoulderNormalCyclePlay extends CyclePlay {

	public BoulderNormalCyclePlay(ExpirableObjectState firstObjectState) {
		super(firstObjectState);

		appearance = Arrays.asList(R.drawable.boulder_standard_appearance_1,
				R.drawable.boulder_standard_appearance_2,
				R.drawable.boulder_standard_appearance_3,
				R.drawable.boulder_standard_appearance_4,
				R.drawable.boulder_standard_appearance_5,
				R.drawable.boulder_standard_appearance_6,
				R.drawable.boulder_standard_appearance_7,
				R.drawable.boulder_standard_appearance_8);

		disappearance_left = Arrays.asList(
				R.drawable.boulder_standard_destruction_1,
				R.drawable.boulder_standard_destruction_2,
				R.drawable.boulder_standard_destruction_3,
				R.drawable.boulder_standard_destruction_4,
				R.drawable.boulder_standard_destruction_5,
				R.drawable.boulder_standard_destruction_6,
				R.drawable.boulder_standard_destruction_7,
				R.drawable.boulder_standard_destruction_8,
				R.drawable.boulder_standard_destruction_9,
				R.drawable.boulder_standard_destruction_10);

		disappearance_up = Arrays.asList(
				R.drawable.boulder_standard_destruction_1,
				R.drawable.boulder_standard_destruction_2,
				R.drawable.boulder_standard_destruction_3,
				R.drawable.boulder_standard_destruction_4,
				R.drawable.boulder_standard_destruction_5,
				R.drawable.boulder_standard_destruction_6,
				R.drawable.boulder_standard_destruction_7,
				R.drawable.boulder_standard_destruction_8,
				R.drawable.boulder_standard_destruction_9,
				R.drawable.boulder_standard_destruction_10);

		disappearance_right = Arrays.asList(
				R.drawable.boulder_standard_destruction_1,
				R.drawable.boulder_standard_destruction_2,
				R.drawable.boulder_standard_destruction_3,
				R.drawable.boulder_standard_destruction_4,
				R.drawable.boulder_standard_destruction_5,
				R.drawable.boulder_standard_destruction_6,
				R.drawable.boulder_standard_destruction_7,
				R.drawable.boulder_standard_destruction_8,
				R.drawable.boulder_standard_destruction_9,
				R.drawable.boulder_standard_destruction_10);

		disappearance_down = Arrays.asList(
				R.drawable.boulder_standard_destruction_1,
				R.drawable.boulder_standard_destruction_2,
				R.drawable.boulder_standard_destruction_3,
				R.drawable.boulder_standard_destruction_4,
				R.drawable.boulder_standard_destruction_5,
				R.drawable.boulder_standard_destruction_6,
				R.drawable.boulder_standard_destruction_7,
				R.drawable.boulder_standard_destruction_8,
				R.drawable.boulder_standard_destruction_9,
				R.drawable.boulder_standard_destruction_10);

		running_left = Arrays.asList(R.drawable.boulder_standard_roll_left_1,
				R.drawable.boulder_standard_roll_left_2,
				R.drawable.boulder_standard_roll_left_3,
				R.drawable.boulder_standard_roll_left_4);
		running_right = Arrays.asList(R.drawable.boulder_standard_roll_right_1,
				R.drawable.boulder_standard_roll_right_2,
				R.drawable.boulder_standard_roll_right_3,
				R.drawable.boulder_standard_roll_right_4);
		running_up = Arrays.asList(R.drawable.boulder_standard_roll_up_1,
				R.drawable.boulder_standard_roll_up_2,
				R.drawable.boulder_standard_roll_up_3,
				R.drawable.boulder_standard_roll_up_4);
		running_down = Arrays.asList(R.drawable.boulder_standard_roll_down_1,
				R.drawable.boulder_standard_roll_down_2,
				R.drawable.boulder_standard_roll_down_3,
				R.drawable.boulder_standard_roll_down_4);

		initializeBitmap();
	}
}
